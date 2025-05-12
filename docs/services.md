# 📦 OpenOrderFlow – Service Overview

This document outlines all services in the OpenOrderFlow system, their responsibilities, data ownership, and boundaries. It reflects a clean microservice architecture that supports modular development, clear service ownership, and scalable evolution.

---

## 🌐 Public Entry Point Services

These services are exposed directly to the frontend and external clients. They act as the interaction boundary for end-users, businesses, and delivery partners.

### 1. `UserService`

- Manages user registration, authentication, roles, and address book.
- Owns: `User`, `UserRole`, `CustomerProfile`, `CustomerAddress`, `DeliveryPartnerProfile`.
- Verifies and issues JWT tokens.
- Provides user summaries to other services via internal APIs.
- Manages customer and delivery partner identity, preferences, and profile state.

### 2. `BusinessService`

- Handles the business side of the platform.
- Owns: `Business`, `BusinessType`, `BusinessAdminProfile`, `BusinessItem`.
- Supports business onboarding, profile editing, and menu management.
- Controls item availability and visibility to users.
- Serves public menu feeds for customers based on location.

### 3. `DeliveryPartnerService`

- Manages the delivery partner lifecycle.
- Owns: `DeliveryPartnerProfile`, and manages availability status and historical metadata.
- Handles onboarding, availability toggling, basic partner data.
- May provide location or assignment APIs to internal services.

### 4. `NotificationService` _(optional)_

- Sends email/SMS/push/websocket notifications to users and partners.
- Maintains communication templates, notification rules, and audit logs.
- Can be pluggable or disabled in dev/local setups.

---

## ⚙️ Internal Domain Services

These services are core to order fulfillment and operate primarily using event-driven logic and Kafka. They do not expose public APIs but are called by public services or consume internal events.

### 5. `OrderService`

- Owns the core **order lifecycle state machine**: placed → confirmed → prepared → dispatched → delivered.
- Owns: `Order`, `OrderItem`, `OrderEventHistory`, `OrderStatus`.
- Maintains order state transitions, business validation hooks, and timeout-based cancellations.
- Handles order tracking metadata for customer and delivery apps.

### 6. `InventoryService`

- Manages inventory units and catalog stock levels.
- Owns: `Inventory`, `InventoryItem`, `ItemCatalog`.
- Performs stock checks on order placement.
- Updates item availability and alerts when stock runs low.

### 7. `DeliveryService`

- Handles delivery partner assignment and routing logic.
- Owns internal delivery job queues and state (transient or persistent).
- Implements dispatching algorithm based on proximity, load, availability.
- Supports reassignment, SLA-based retries, and failure handling.

### 8. `PaymentService`

- Responsible for payment orchestration (initiation → success/failure).
- Owns: `PaymentTransaction`, `OrderPaymentMode`, `OrderPaymentStatus`.
- Integrates with payment gateways (e.g., Razorpay/Stripe).
- Handles reconciliation, status updates, and fraud detection (future scope).

---

## 🧠 Future/Optional Services

### 9. `AnalyticsService`

- Central aggregator of platform-wide events.
- Owns read-optimized metrics and aggregates across users, orders, delivery performance.
- Useful for dashboards, alerts, and insights.

### 10. `RatingService`

- Accepts customer reviews and feedback post-order.
- Owns: `Rating`, and optionally `Review` models.
- Supports rating of orders, delivery partners, and businesses.
- Can support moderation and feedback weighting later.

---

## 🗃️ Service-to-Database Ownership

| Service                | Owns DB Schema           |
| ---------------------- | ------------------------ |
| UserService            | `users_db`               |
| BusinessService        | `business_db`            |
| DeliveryPartnerService | `partners_db`            |
| OrderService           | `orders_db`              |
| InventoryService       | `inventory_db`           |
| PaymentService         | `payments_db`            |
| NotificationService    | `notifications_db` (opt) |
| RatingService          | `ratings_db` (future)    |
| AnalyticsService       | `analytics_db` (future)  |

---

This service-oriented structure aligns with real-world architecture used by product companies at scale. It ensures separation of concerns, database ownership, and clear team boundaries for long-term maintainability.
