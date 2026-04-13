package com.jaydeep.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.LongFunction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invoice_seq")
    @SequenceGenerator(name = "invoice_seq",sequenceName = "invoice_seq",allocationSize = 1)
    @Column(name = "invoice_id")
    private Long invoiceId;
    private double invoiceNetAmount;
    private  double invoiceTotalGst;
    private  double invoiceTotal;
    private  double paidAmount;
    private  double remainingAmount;
    private  String invoiceStatus;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> invoiceItems;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    public Invoice(double invoiceNetAmount,double invoiceTotalGst,double invoiceTotal,double paidAmount,double remainingAmount,String invoiceStatus)
    {
        this.invoiceNetAmount=invoiceNetAmount;
        this.invoiceTotalGst=invoiceTotalGst;
        this.invoiceTotal=invoiceTotal;
        this.paidAmount=paidAmount;
        this.remainingAmount=remainingAmount;
        this.invoiceStatus=invoiceStatus;
    }

}
