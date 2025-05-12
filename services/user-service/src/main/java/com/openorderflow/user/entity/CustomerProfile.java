package com.openorderflow.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerProfile {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_profile_user"))
    private User user;

    @Column(name = "preferred_language")
    private String preferredLanguage;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;

    @OneToOne
    @JoinColumn(name = "primary_address_id", foreignKey = @ForeignKey(name = "fk_profile_address"))
    private CustomerAddress primaryAddress;
}
