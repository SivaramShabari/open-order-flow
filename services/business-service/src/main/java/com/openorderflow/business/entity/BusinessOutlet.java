package com.openorderflow.business.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BusinessOutlet {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "business_outlet_id", nullable = false, foreignKey = @ForeignKey(name = "business_outlet_business"))
    private Business business;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2", nullable = false)
    private String addressLine2;

    @Column(name = "address_line_3", nullable = true)
    private String addressLine3;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "postal_code", nullable = false)
    private int postalCode;

    @Column(name = "latitude", nullable = false, precision = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 8)
    private BigDecimal longitude;
}
