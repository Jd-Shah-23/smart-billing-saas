package com.jaydeep.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice_item")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invoice_item_seq")
    @SequenceGenerator(name = "invoice_item_seq",sequenceName = "invoice_item_seq",allocationSize = 1)
    @Column(name = "invoice_item_id")
    private Long invoiceItemId;
    private int quantity;
    private double unitPrice;
    private double netAmount;
    private double cgstAmount;
    private double sgstAmount;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
