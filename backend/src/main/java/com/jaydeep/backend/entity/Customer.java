package com.jaydeep.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    @Column(name = "customer_id")
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPassWord;
    private String customerCity;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;

    private Boolean activeFlag;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.activeFlag = true;
    }

}
