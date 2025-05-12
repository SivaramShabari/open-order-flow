# 📡 OpenOrderFlow – Event Types v1

This document outlines the initial version (v1) of business events emitted across OpenOrderFlow services. Events are grouped by domain/topic. These are **immutable business facts** and follow a clear naming, structure, and delivery contract.

Each event payload will be wrapped in a standard envelope:

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

## 🧾 order.events

Used to track the entire lifecycle of an order.

- `order.placed`
- `order.payment.initiated`
- `order.paid`
- `order.confirmed`
- `order.rejected`
- `order.prep.started`
- `order.prep.completed`
- `order.partner.assignment.started`
- `order.partner.assigned`
- `order.partner.reached_pickup`
- `order.partner.waiting`
- `order.picked.up`
- `order.dispatched`
- `order.delivered`
- `order.failed`
- `order.cancelled`
- `order.timed.out`
- `order.rated`

---

## 📦 inventory.events

Handles changes in item stock, catalog data, and inventory movements.

- `inventory.created`
- `inventory.updated`
- `inventory.threshold.low`
- `item.catalog.created`
- `item.catalog.updated`
- `item.catalog.deactivated`
- `item.stocked`
- `item.ordered`
- `item.reserved`
- `item.released`
- `item.expired`
- `item.returned`
- `item.lost`
- `item.adjusted`

---

## 🚚 delivery.events

Tracks assignment and behavior of delivery partners.

- `partner.assignment.started`
- `partner.assigned`
- `partner.status.updated` _(e.g., AVAILABLE → DELIVERING)_
- `partner.location.updated`
- `partner.reached.pickup`
- `partner.reached.dropoff`
- `partner.delivery.delayed`
- `partner.delivery.failed`
- `partner.unavailable`
- `partner.communication.failed`

---

## 🔔 notification.events

Used to trigger outbound communication mechanisms.

- `notify.user.order.status`
- `notify.business.order.status`
- `notify.partner.assignment`
- `notify.partner.reminder`
- `notify.delivery.delayed`
- `notify.feedback.requested`
- `notify.generic`

---

## 📍 location.events

For tracking geo and motion data of delivery agents (future scope).

- `location.partner.updated`
- `location.partner.offline`
- `location.geo.inaccuracy.detected`

---

## ⭐ rating.events

Triggers related to customer feedback and partner performance.

- `rating.submitted`
- `rating.revised`
- `rating.reminder.sent`
- `rating.blocked`

---

## 📊 analytics.events _(optional topic, or internal sink)_

System-wide sink for real-time analytical needs. Can be a mirror of key events above with enriched metadata.

- `event.forwarded.analytics`
- `system.health.check`
- `event.failed.processing`
- `sla.violation.detected`

---

## 🧰 Event Naming Conventions

- Use snake_case topic names: `order.events`, `inventory.events`, etc.
- Use dot-separated routing keys if needed in Kafka: `order.placed`, `order.confirmed`, etc.
- Prefer `verb.object` or `entity.status` naming
- All event payloads must be immutable once emitted
- Events may be versioned via `eventType` or `payload.version`

---

> This list evolves with the platform. Each service must publish a schema contract per event it produces or consumes.
