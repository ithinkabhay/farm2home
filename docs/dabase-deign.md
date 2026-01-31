# Database-per-Service Design

Farm2Home follows the **database-per-service** principle.
Each microservice owns its own database and data.

---

## Auth Service (auth_db)
Owns authentication and user identity.

### Tables
- users
  - id
  - name
  - mobile
  - role (FARMER, CUSTOMER)
  - password_hash
  - created_at

---

## Farmer Service (farmer_db)
Owns farmer profile information.

### Tables
- farmers
  - id
  - user_id
  - village
  - district
  - state
  - preferred_language
  - verified

---

## Grain Service (grain_db)
Owns grain listings and harvest data.

### Tables
- grains
  - id
  - farmer_id
  - grain_type (WHEAT, RICE, DAL)
  - harvest_date
  - quantity_kg
  - price_per_kg
  - status (ACTIVE, SOLD)

- grain_media
  - id
  - grain_id
  - media_type (IMAGE, VIDEO)
  - s3_url

---

## Order Service (order_db)
Owns order lifecycle.

### Tables
- orders
  - id
  - grain_id
  - farmer_id
  - customer_id
  - quantity_kg
  - total_price
  - status (CREATED, CONFIRMED, DELIVERED)
  - created_at

---

## Payment Service (payment_db)
Owns payment transactions.

### Tables
- payments
  - id
  - order_id
  - stripe_payment_id
  - amount
  - status (SUCCESS, FAILED)
  - created_at

---

## Receipt Service (receipt_db)
Owns generated documents.

### Tables
- receipts
  - id
  - order_id
  - pdf_url
  - generated_at

---

## Notification Service (notification_db)
Owns communication logs.

### Tables
- notifications
  - id
  - recipient_mobile
  - message
  - status
  - sent_at

---

## Data Ownership Rules
- Each service owns its database
- No service accesses another service’s database directly
- Communication happens via REST APIs or Kafka events
