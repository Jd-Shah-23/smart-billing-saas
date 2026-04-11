package com.jaydeep.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gst_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GstDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gst_seq")
    @SequenceGenerator(name = "gst_seq", sequenceName = "gst_seq", allocationSize = 1)
    @Column(name = "gst_id")
    private Long gstId;
    private String gstCode;
    private double cgstPercentage;
    private double sgstPercentage;
}
