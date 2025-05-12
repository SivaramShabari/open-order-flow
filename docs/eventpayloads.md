# 📦 OpenOrderFlow – Event Payloads (v1)

> Each event below follows the standard envelope:

```json
{
  "eventType": "ORDER_PLACED",
  "eventId": "uuid",
  "traceId": "uuid",
  "timestamp": "ISO8601",
  "sender": "order-service",
  "payload": { ... }
}
```

---

## 1. `order.placed`

```json
{
  "orderId": "uuid",
  "customerId": "uuid",
  "businessId": "uuid",
  "deliveryAddress": {
    "addressId": "uuid",
    "latitude": 12.934567,
    "longitude": 77.626574,
    "fullAddress": "Somewhere nice, Bangalore"
  },
  "payment": {
    "mode": "ONLINE",
    "status": "INITIATED",
    "amount": 399.0,
    "couponCode": "SAVE20",
    "discount": 20.0
  },
  "items": [
    {
      "itemId": "uuid",
      "catalogId": "uuid",
      "inventoryId": "uuid",
      "quantity": 2,
      "unitPrice": 100.0,
      "totalPrice": 200.0
    }
  ],
  "estimatedTimes": {
    "prepTimeMins": 20,
    "deliveryTimeMins": 25,
    "partnerAssignTimeMins": 3
  },
  "customerInstructions": "Leave at the gate"
}
```

---

## 2. `order.payment.initiated`

```json
{
  "orderId": "uuid",
  "customerId": "uuid",
  "amount": 399.0,
  "paymentGateway": "razorpay",
  "transactionId": "txn_abc123",
  "mode": "ONLINE",
  "currency": "INR",
  "initiatedAt": "2025-05-12T10:15:00Z"
}
```

---

## 3. `order.paid`

```json
{
  "orderId": "uuid",
  "transactionId": "txn_abc123",
  "paidAt": "2025-05-12T10:15:35Z",
  "mode": "ONLINE",
  "status": "SUCCESS",
  "amount": 399.0
}
```

---

## 4. `order.confirmed`

```json
{
  "orderId": "uuid",
  "confirmedBy": "business_admin_123",
  "confirmedAt": "2025-05-12T10:17:00Z",
  "status": "ACCEPTED"
}
```

---

## 5. `order.rejected`

```json
{
  "orderId": "uuid",
  "rejectedBy": "business_admin_123",
  "rejectedAt": "2025-05-12T10:17:00Z",
  "reason": "Item unavailable / store overloaded"
}
```

---

## 6. `order.prep.started`

```json
{
  "orderId": "uuid",
  "startedBy": "kitchen_staff_789",
  "startedAt": "2025-05-12T10:18:00Z"
}
```

---

## 7. `order.prep.completed`

```json
{
  "orderId": "uuid",
  "completedBy": "kitchen_staff_789",
  "completedAt": "2025-05-12T10:33:00Z"
}
```

---

## 8. `order.partner.assigned`

```json
{
  "orderId": "uuid",
  "partnerId": "uuid",
  "assignedAt": "2025-05-12T10:20:00Z",
  "etaMins": 15,
  "partnerStatus": "EN_ROUTE"
}
```

---

## 9. `order.picked.up`

```json
{
  "orderId": "uuid",
  "partnerId": "uuid",
  "pickupTime": "2025-05-12T10:35:00Z",
  "location": {
    "lat": 12.9345,
    "lng": 77.6265
  }
}
```

---

## 10. `order.delivered`

```json
{
  "orderId": "uuid",
  "partnerId": "uuid",
  "deliveredAt": "2025-05-12T10:55:00Z",
  "customerId": "uuid",
  "receivedBy": "customer",
  "deliveryFeedback": {
    "issueReported": false,
    "notes": null
  }
}
```

---

> All payloads must be treated as immutable. Downstream services should not modify existing event data. Events may be extended with optional fields or versioned as needed.
