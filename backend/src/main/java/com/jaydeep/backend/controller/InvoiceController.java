package com.jaydeep.backend.controller;

import com.jaydeep.backend.dto.InvoiceRequest;
import com.jaydeep.backend.dto.InvoiceResponse;
import com.jaydeep.backend.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService)
    {
        this.invoiceService=invoiceService;
    }

    @PostMapping("/api/generateInvoice")
    public ResponseEntity<InvoiceResponse> generateInvoice(
            @Valid @RequestBody InvoiceRequest invoiceRequest
            )
    {
        return ResponseEntity.status(CREATED).body(this.invoiceService.generateInvoice(invoiceRequest));
    }


}
