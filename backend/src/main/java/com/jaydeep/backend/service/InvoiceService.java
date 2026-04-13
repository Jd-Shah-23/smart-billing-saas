package com.jaydeep.backend.service;

import com.jaydeep.backend.dto.InvoiceItemRequest;
import com.jaydeep.backend.dto.InvoiceRequest;
import com.jaydeep.backend.dto.InvoiceResponse;
import com.jaydeep.backend.entity.*;
import com.jaydeep.backend.exception.ParentNotFoundException;
import com.jaydeep.backend.repository.CustomerRepository;
import com.jaydeep.backend.repository.GstDetailsRepository;
import com.jaydeep.backend.repository.InvoiceRepository;
import com.jaydeep.backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public  InvoiceService(InvoiceRepository invoiceRepository,CustomerRepository customerRepository,ProductRepository productRepository)
    {
        this.invoiceRepository=invoiceRepository;
        this.customerRepository=customerRepository;
        this.productRepository=productRepository;
    }

    @Transactional
    public InvoiceResponse generateInvoice(InvoiceRequest invoiceRequest)
    {
        Invoice invoice=new Invoice();

        List<InvoiceItem> invoiceItems=new ArrayList<>();
        Optional<Customer> optional=this.customerRepository.findById(invoiceRequest.getCustomerId());
        double invoiceNetAmount=0;
        double invoiceTotalGst=0;
        double invoiceTotal=0;

        if(!optional.isPresent())
        {
            throw new ParentNotFoundException("CustomerID","Customer not found with given customer Id :  " + invoiceRequest.getCustomerId());
        }

        Customer customer=optional.get();
        invoice.setCustomer(customer);
        for(InvoiceItemRequest invoiceItemRequests : invoiceRequest.getInvoiceItems())
        {
            Optional<Product> optionalProduct=this.productRepository.findById(invoiceItemRequests.getProductId());
            if(!optionalProduct.isPresent())
            {
                throw new ParentNotFoundException("Product Id","Product not found for giver product id : "+ invoiceItemRequests.getProductId());
            }
            InvoiceItem invoiceItem=new InvoiceItem();
            Product product=optionalProduct.get();
            GstDetails gstDetails=product.getGstDetails();

            double netAmount=product.getProductPrice()*invoiceItemRequests.getQuantity();
            double cgstAmount=netAmount*gstDetails.getCgstPercentage()/100;
            double sgstAmount=netAmount*gstDetails.getSgstPercentage()/100;
            double totalAmount=netAmount+cgstAmount+sgstAmount;

            invoiceItem.setInvoice(invoice);
            invoiceItem.setProduct(product);
            invoiceItem.setQuantity(invoiceItemRequests.getQuantity());
            invoiceItem.setUnitPrice(product.getProductPrice());
            invoiceItem.setNetAmount(netAmount);
            invoiceItem.setCgstAmount(cgstAmount);
            invoiceItem.setSgstAmount(sgstAmount);
            invoiceItem.setTotalAmount(totalAmount);

            invoiceNetAmount+=netAmount;
            invoiceTotalGst+=cgstAmount+sgstAmount;
            invoiceTotal+=totalAmount;

            invoiceItems.add(invoiceItem);

        }
        invoice.setInvoiceNetAmount(invoiceNetAmount);
        invoice.setInvoiceTotalGst(invoiceTotalGst);
        invoice.setInvoiceTotal(invoiceTotal);
        invoice.setPaidAmount(0);
        invoice.setRemainingAmount(invoiceTotal);
        invoice.setInvoiceStatus("PENDING");
        invoice.setInvoiceItems(invoiceItems);


        return mapToResponse(this.invoiceRepository.save(invoice));
    }

    public InvoiceResponse mapToResponse(Invoice invoice)
    {
        return new InvoiceResponse(invoice.getInvoiceId(), invoice.getInvoiceNetAmount(),invoice.getInvoiceTotalGst(),invoice.getInvoiceTotal(),invoice.getPaidAmount(),invoice.getRemainingAmount(),invoice.getInvoiceStatus() );
    }


}
