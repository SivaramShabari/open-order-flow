package com.openorderflow.business.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "createdBy", nullable = false)
    private BusinessUserProfile createdBy;

    @OneToOne
    @JoinColumn(name = "updatedBy", nullable = false)
    private BusinessUserProfile updatedBy;
}
