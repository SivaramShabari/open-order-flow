# 🗃️ OpenOrderFlow - Complete Database Schema


Note that there are 4 individual DBs, so there is no join across DBs
---


## 🧩 Business DB

### 📄 business_user_profile

| Column             | Type          |
|--------------------|---------------|
| id                 | uuid          |
| business_id        | uuid          |
| business_outlet_id | uuid          |
| email              | varchar(255)  |
| name               | varchar(255)  |
| phone              | varchar(13)   |
| role               | varchar(255)  |

---

### 📄 business_outlet

| Column             | Type          |
|--------------------|---------------|
| id                 | uuid          |
| is_active          | boolean       |
| latitude           | numeric       |
| longitude          | numeric       |
| postal_code        | integer       |
| business_id        | uuid          |
| address_line_1     | varchar(255)  |
| address_line_2     | varchar(255)  |
| address_line_3     | varchar(255)  |
| city               | varchar(255)  |
| state              | varchar(255)  |
| name               | varchar(255)  |

---

### 📄 order_queue

| Column             | Type                     |
|--------------------|--------------------------|
| id                 | uuid                     |
| created_at         | timestamp with time zone |
| customer_id        | uuid                     |
| order_amount       | numeric                  |
| order_id           | uuid                     |
| updated_at         | timestamp with time zone |
| business_outlet_id | uuid                     |
| status             | varchar(255)             |

---

### 📄 order_queue_item

| Column         | Type          |
|----------------|---------------|
| id             | uuid          |
| base_price     | numeric       |
| quantity       | integer       |
| order_queue_id | uuid          |
| name           | varchar(255)  |

---

### 📄 business

| Column | Type         |
|--------|--------------|
| id     | uuid         |
| name   | varchar(255) |
| type   | varchar(30)  |

---

## 👤 Customer DB

### 📄 customer_profile

| Column             | Type          |
|--------------------|---------------|
| id                 | uuid          |
| is_email_verified  | boolean       |
| primary_address_id | uuid          |
| email              | varchar(255)  |
| name               | varchar(255)  |
| phone              | varchar(10)   |

---

### 📄 customer_address

| Column          | Type          |
|-----------------|---------------|
| id              | uuid          |
| latitude        | numeric       |
| longitude       | numeric       |
| customer_id     | uuid          |
| address_line_1  | varchar(255)  |
| address_line_2  | varchar(255)  |
| address_name    | varchar(255)  |
| timings         | varchar(255)  |
| type            | varchar(255)  |

---

## 📦 Inventory DB

### 📄 inventory_item

| Column           | Type                     |
|------------------|--------------------------|
| expiry_date      | date                     |
| price            | numeric                  |
| quantity         | integer                  |
| updated_at       | timestamp with time zone |
| business_item_id | uuid                     |
| inventory_id     | uuid                     |
| id               | uuid                     |
| arrival_date     | date                     |

---

### 📄 inventory

| Column             | Type                     |
|--------------------|--------------------------|
| updated_at         | timestamp with time zone |
| id                 | uuid                     |
| business_id        | uuid                     |
| business_outlet_id | uuid                     |
| inventory_type     | varchar(255)             |
| location_name      | varchar(255)             |
| name               | varchar(255)             |

---

### 📄 item_catalog

| Column         | Type                     |
|----------------|--------------------------|
| item_catalog_id| uuid                     |
| created_at     | timestamp with time zone |
| is_active      | boolean                  |
| updated_at     | timestamp with time zone |
| category       | varchar(255)             |
| description    | varchar(255)             |
| item_type      | varchar(255)             |
| name           | varchar(255)             |
| unit           | varchar(255)             |

---

### 📄 business_item

| Column                   | Type                     |
|--------------------------|--------------------------|
| business_item_id         | uuid                     |
| base_price               | numeric                  |
| business_id              | uuid                     |
| is_available             | boolean                  |
| updated_at               | timestamp with time zone |
| item_catalog_id          | uuid                     |
| image_url                | varchar(255)             |
| name                     | varchar(255)             |

---

## 🧾 Order DB

### 📄 orders

| Column                           | Type                     |
|----------------------------------|--------------------------|
| coupon_id                        | uuid                     |
| business_id                      | uuid                     |
| business_outlet_latitude         | numeric                  |
| business_outlet_longitude        | numeric                  |
| created_at                       | timestamp with time zone |
| customer_id                      | uuid                     |
| delivery_location_latitude       | numeric                  |
| delivery_location_longitude      | numeric                  |
| delivery_partner_id              | uuid                     |
| latitude                         | numeric                  |
| longitude                        | numeric                  |
| discounted_amount                | numeric                  |
| is_paid                          | boolean                  |
| payment_id                       | uuid                     |
| total_amount                     | numeric                  |
| updated_at                       | timestamp with time zone |
| order_id                         | uuid                     |
| business_outlet_id               | uuid                     |
| business_name                    | varchar(255)             |
| business_outlet_address          | varchar(255)             |
| delivery_partner_name            | varchar(255)             |
| business_outlet_name             | varchar(255)             |
| business_outlet_phone            | varchar(255)             |
| delivery_partner_phone           | varchar(255)             |
| customer_email                   | varchar(255)             |
| customer_name                    | varchar(255)             |
| customer_phone                   | varchar(255)             |
| customer_instructions            | text                     |
| deliver_location_address_line1   | varchar(255)             |
| deliver_location_address_line2   | varchar(255)             |
| deliver_location_address_line3   | varchar(255)             |
| deliver_location_city            | varchar(255)             |
| deliver_location_name            | varchar(255)             |
| deliver_location_state           | varchar(255)             |
| deliver_location_type            | varchar(255)             |
| order_status                     | varchar(255)             |
| delivery_partner_email           | varchar(255)             |

---

### 📄 order_items

| Column                             | Type          |
|------------------------------------|---------------|
| order_item_id                      | uuid          |
| business_item_discounted_price_at_order | numeric   |
| business_item_id                   | uuid          |
| business_item_price_at_order       | numeric       |
| order_item_order_id                | uuid          |
| business_item_type                 | varchar(255)  |
| business_item_description          | varchar(255)  |
| business_item_name                 | varchar(255)  |

---

### 📄 order_event_history

| Column             | Type                     |
|--------------------|--------------------------|
| status_id          | uuid                     |
| order_event_history_id | uuid                 |
| time_stamp         | timestamp with time zone |
| updated_by_id      | uuid                     |
| order_id           | uuid                     |
| updated_by         | varchar(255)             |
| comments           | text                     |
