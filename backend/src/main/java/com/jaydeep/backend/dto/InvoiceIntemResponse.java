package com.jaydeep.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceIntemResponse {
    private Long invoiceItemId;
    private int quantity;
    private double unitPrice;
    private double netAmount;
    private double cgstAmount;
    private double sgstAmount;
    private double totalAmount;
}
