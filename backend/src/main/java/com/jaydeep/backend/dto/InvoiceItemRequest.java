package com.jaydeep.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemRequest {
    @NotNull
    @Min(1)
    private int quantity;
    @NotNull
    private Long productId;
}
