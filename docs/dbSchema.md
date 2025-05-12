# 🗃️ OpenOrderFlow – Service-Oriented DB Schema

This document outlines database tables grouped by service ownership. All foreign keys between services are removed in favor of application-level validation and event-based consistency.

---

## 👤 UserService

### `User`

- `user_id` (PK): UUID
- `email`: VARCHAR, unique, not null
- `phone`: VARCHAR, unique
- `full_name`: VARCHAR
- `password_hash`: TEXT
- `created_at`: TIMESTAMP
- `updated_at`: TIMESTAMP

### `UserRole`

- `id` (PK): UUID
- `user_id`: UUID _(references `User.user_id` internally)_
- `role`: ENUM [CUSTOMER, BUSINESS_ADMIN, DELIVERY_PARTNER]

### `CustomerProfile`

- `user_id` (PK): UUID _(references `User.user_id`)_
- `preferred_language`: VARCHAR
- `loyalty_points`: INTEGER
- `primary_address_id`: UUID _(references `CustomerAddress.id`)_

### `CustomerAddress`

- `id` (PK): UUID
- `user_id`: UUID
- `address_name`: VARCHAR
- `type`: VARCHAR
- `timings`: VARCHAR
- `address_line_1`: TEXT
- `address_line_2`: TEXT
- `geo_latitude`: DECIMAL
- `geo_longitude`: DECIMAL

### `DeliveryPartnerProfile`

- `user_id` (PK): UUID
- `vehicle_type`: VARCHAR
- `license_number`: VARCHAR
- `availability_status`: ENUM [AVAILABLE, UNAVAILABLE]
- `joined_date`: TIMESTAMP

---

## 🏢 BusinessService

### `Business`

- `business_id` (PK): UUID
- `name`: VARCHAR
- `type_id`: UUID _(references `BusinessType.id`)_
- `location_name`: TEXT
- `status`: ENUM [ACTIVE, SUSPENDED]
- `created_by`: UUID _(refers to `User.user_id`)_
- `created_at`: TIMESTAMP
- `updated_at`: TIMESTAMP

### `BusinessType`

- `id` (PK): UUID
- `name`: VARCHAR
- `industry`: VARCHAR
- `updated_at`: TIMESTAMP

### `BusinessAdminProfile`

- `user_id` (PK): UUID
- `business_id`: UUID
- `admin_level`: ENUM [OWNER, MANAGER]

---

## 📦 InventoryService

### `ItemCatalog`

- `item_catalog_id` (PK): UUID
- `name`: VARCHAR
- `description`: TEXT
- `unit`: VARCHAR
- `item_type`: VARCHAR
- `is_active`: BOOLEAN
- `subcategory`: VARCHAR

### `BusinessItem`

- `business_item_id` (PK): UUID
- `business_id`: UUID
- `item_catalog_id`: UUID
- `base_price`: DECIMAL
- `is_available`: BOOLEAN
- `updated_at`: TIMESTAMP

### `Inventory`

- `inventory_id` (PK): UUID
- `business_id`: UUID
- `location_name`: VARCHAR
- `capacity`: INTEGER
- `type`: VARCHAR
- `updated_at`: TIMESTAMP
- `created_by`: UUID

### `InventoryItem`

- `inventory_item_id` (PK): UUID
- `inventory_id`: UUID
- `item_catalog_id`: UUID
- `arrival_date`: DATE
- `expiry_date`: DATE
- `quantity`: INTEGER
- `price`: DECIMAL
- `updated_at`: TIMESTAMP

---

## 📬 OrderService

### `Order`

- `order_id` (PK): UUID
- `customer_id`: UUID
- `business_id`: UUID
- `delivery_partner_id`: UUID
- `status_id`: UUID _(references `OrderStatus.status_id`)_
- `payment_mode_id`: UUID
- `payment_status_id`: UUID
- `delivery_address_id`: UUID
- `total_amount`: DECIMAL
- `coupon_applied_id`: UUID
- `total_discount`: DECIMAL
- `customer_instructions`: TEXT
- `created_at`: TIMESTAMP
- `updated_at`: TIMESTAMP

### `OrderItem`

- `order_item_id` (PK): UUID
- `order_id`: UUID
- `business_item_id`: UUID
- `quantity`: INTEGER
- `item_price_at_order`: DECIMAL
- `discounted_item_price_at_order`: DECIMAL

### `OrderEventHistory`

- `id` (PK): UUID
- `order_id`: UUID
- `timestamp`: TIMESTAMP
- `updated_by`: UUID
- `status_id`: UUID
- `comments`: TEXT

### `OrderStatus`

- `status_id` (PK): UUID
- `status_name`: VARCHAR

---

## 💸 PaymentService

### `OrderPaymentMode`

- `payment_mode_id` (PK): UUID
- `payment_mode_name`: VARCHAR

### `OrderPaymentStatus`

- `id` (PK): UUID
- `status_name`: VARCHAR

### `Coupon`

- `id` (PK): UUID
- `discount_percent`: DECIMAL
- `max_discount_price`: DECIMAL
- `min_order_amount`: DECIMAL
- `active_from`: DATE
- `valid_till`: DATE
- `enabled_payment_mode_id`: UUID
- `updated_at`: TIMESTAMP

---

## ⭐ RatingService

### `Rating`

- `id` (PK): UUID
- `customer_id`: UUID
- `order_id`: UUID
- `rating`: INTEGER
- `type`: ENUM [ITEM, DELIVERY_PARTNER]
- `timestamp`: TIMESTAMP
