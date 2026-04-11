package com.jaydeep.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
    @Column(name = "payment_id")
    private Long paymentId;

    private double paymentAmount;

    private String paymentMode;

    private String paymentStatus;

    private String transactionId;

    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @PrePersist
    public void prePersist() {
        this.paymentDate = LocalDateTime.now();
    }
}
