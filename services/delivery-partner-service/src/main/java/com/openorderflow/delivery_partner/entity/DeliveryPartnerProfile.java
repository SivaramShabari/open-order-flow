package com.openorderflow.delivery_partner.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "delivery_partner_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DeliveryPartnerProfile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID Id;

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
        AVAILABLE,
        DELIVERING,
        OFFLINE
    }
}
