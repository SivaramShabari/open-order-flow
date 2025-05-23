package com.openorderflow.common.kafka.topics;

public class KafkaTopicsV1 {
    public static final String ORDER_CREATION_REQUESTED_V1 = "order.creation.request.v1";

    public static final String ORDER_ITEMS_VALIDATION_REQUEST_V1 = "order.items.validation.request.v1";
    public static final String INVENTORY_ITEMS_VALIDATED_V1 = "order.inventory.items.validate.v1";
    public static final String INVENTORY_ITEMS_REJECTED_V1 = "order.inventory.items.reject.v1";

    public static final String ORDER_PLACED_V1 = "order.placed.v1";

    public static final String BUSINESS_ORDER_CONFIRMED = "business.order.confirmed.v1";
    public static final String BUSINESS_ORDER_REJECTED = "business.order.rejected.v1";

    public static final String BUSINESS_ORDER_PREPARED = "business.order.prepared.v1";

    public static final String DELIVERY_PARTNER_ASSIGNED = "delivery.partner.assigned.v1";
    public static final String DELIVERY_PARTNER_REACHED_BUSINESS = "delivery.partner.reached.business.v1";

    public static final String DELIVERY_PARTNER_ORDER_PICKED = "delivery.partner.order.picked.v1";
    public static final String DELIVERY_PARTNER_ORDER_DELIVERED = "delivery.partner.order.delivered.v1";
}