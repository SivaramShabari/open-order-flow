package com.openorderflow.user.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "delivery_partner_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DeliveryPartnerProfile {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_partner_profile"))
    private User user;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "license_number")
    private String licenseNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "joined_date")
    private Instant joinedDate;

    public enum AvailabilityStatus {
        AVAILABLE, UNAVAILABLE
    }
}
