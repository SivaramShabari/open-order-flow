package com.openorderflow.business.entity;

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
    @Column(name = "id")
    private UUID id;

    @Column(name = "phone", nullable = false, length = 13)
    private String phone;

    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private BusinessAdminRoleEnum role;

    @OneToOne
    @JoinColumn (name = "business_id", foreignKey = @ForeignKey(name = "business_user_business"), insertable = false, updatable = false)
    private Business business;

    @OneToOne
    @JoinColumn (name = "business_outlet_id", foreignKey = @ForeignKey(name = "business_user_outlet"), insertable = false, updatable = false)
    private BusinessOutlet businessOutlet;

    public enum  BusinessAdminRoleEnum {
        OWNER,
        BUSINESS_ADMIN,
        OUTLET_ADMIN,
        INVENTORY_MANAGER,
        ORDER_MANAGER,
        MENU_MANAGER,
    }
}
