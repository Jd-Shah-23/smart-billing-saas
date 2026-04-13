package com.jaydeep.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {

    private Long invoiceId;
    private double invoiceNetAmount;
    private  double invoiceTotalGst;
    private  double invoiceTotal;
    private  double paidAmount;
    private  double remainingAmount;
    private  String invoiceStatus;
}
