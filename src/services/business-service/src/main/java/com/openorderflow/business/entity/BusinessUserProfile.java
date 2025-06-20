package com.openorderflow.business.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "business_user_profile")
public class BusinessUserProfile {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "phone", nullable = false, length = 13)
    private String phone;

    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private BusinessAdminRoleEnum role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    @JsonBackReference
    private Business business;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_outlet_id", nullable = false)
    @JsonBackReference
    private BusinessOutlet businessOutlet;

    public enum BusinessAdminRoleEnum {
        OWNER,
        BUSINESS_ADMIN,
        OUTLET_ADMIN,
        INVENTORY_MANAGER,
        ORDER_MANAGER,
        MENU_MANAGER
    }
}
