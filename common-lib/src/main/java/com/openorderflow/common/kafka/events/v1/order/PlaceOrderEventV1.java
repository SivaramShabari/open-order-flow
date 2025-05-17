package com.openorderflow.common.kafka.events.v1.order;

import com.openorderflow.common.common.GeoLocation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PlaceOrderEventV1(
        @NotNull UUID customerId,
        @NotNull String userName,
        @NotNull String userPhone,
        @NotNull String userEmail,
        @NotNull Instant placedAt,
        @NotNull @Size(min = 1) List<OrderItem> items,
        @NotNull OrderBusinessOutlet businessOutlet,
        @NotNull OrderCoupon coupon,
        @NotNull OrderPayment payment,
        @NotNull  @Size(max = 200) String deliveryInstruction,
        @NotNull OrderCustomerAddress customerAddress
        ) {

    public record OrderItem(
            @NotNull UUID businessItemId,
            @NotNull String itemName,
            @NotNull String itemDescription,
            @NotNull UUID inventoryId,
            @NotNull String itemCategory,
            @Min(1) Integer quantity,
            @NotNull Float unitPrice,
            @NotNull Float totalPrice,
            @NotNull Float couponDiscountAmount
    ) {
    }

    public record OrderBusinessOutlet(
            @NotNull UUID businessOutletId,
            @NotNull String businessOutletName,
            @NotNull UUID businessId,
            @NotNull GeoLocation geoLocation
    ) {
    }

    public record OrderCoupon(
            @NotNull UUID couponId,
            @NotNull String couponCode,
            @NotNull Integer discountPercentage,
            @NotNull Float totalDiscountAmount,
            @NotNull Float maxDiscountAmount,
            @NotNull Instant appliedOn
    ) {
    }

    public record OrderPayment(
            @NotNull Boolean isPaid,
            @NotNull String paymentMode,
            UUID paymentId,
            String paymentType,
            Instant paidAt
    ) {
    }

    public record OrderCustomerAddress(
            @NotNull UUID customerAddressId,
            @NotNull String locationName,
            @NotNull GeoLocation location,
            @NotNull String addressLine1,
            @NotNull Integer postalCode,
            @NotNull String city,
            @NotNull String state,
            String addressLine2,
            String customerAddressNote
    ){
    }
}