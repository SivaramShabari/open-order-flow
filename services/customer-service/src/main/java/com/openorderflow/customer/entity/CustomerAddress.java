package com.openorderflow.customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

    @Entity
    @Table(name = "customer_address")
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public class CustomerAddress {

        @Id
        @GeneratedValue
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_address_customer"))
        @JsonBackReference
        private CustomerProfile profile;

        @Column(name = "address_name")
        private String addressName;

        @Column(name = "type")
        private String type;

        @Column(name = "timings")
        private String timings;

        @Column(name = "address_line_1")
        private String addressLine1;

        @Column(name = "address_line_2")
        private String addressLine2;

        @Column(name = "geo_latitude", precision = 6)
        private BigDecimal geoLatitude;

        @Column(name = "geo_longitude")
        private BigDecimal geoLongitude;

    }
