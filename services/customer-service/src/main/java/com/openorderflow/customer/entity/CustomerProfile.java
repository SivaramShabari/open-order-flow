package com.openorderflow.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_profile")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerProfile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "primary_phone_number", nullable = false, unique = true, length = 10)
    private String primaryPhoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @OneToOne
    @JoinColumn(name="primary_address_id", nullable = false, foreignKey =  @ForeignKey(name = "fk_primary_address_customer"))
    private CustomerAddress primaryAddress;

    @OneToMany(mappedBy = "profile",  cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<CustomerAddress> addresses;
}