CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO item_catalog (
    item_catalog_id,
    name,
    description,
    unit,
    item_type,
    is_active,
    category,
    created_at,
    updated_at
) VALUES
-- 🍽️ Food Categories
(gen_random_uuid(), 'Ready to Eat Meals', 'Cooked food items ready for consumption', 'plate', 'food', true, 'food', now(), now()),
(gen_random_uuid(), 'South Indian Meals', 'Dosas, idlis, pongal, etc.', 'plate', 'food', true, 'food', now(), now()),
(gen_random_uuid(), 'North Indian Meals', 'Roti, sabzi, dal, etc.', 'plate', 'food', true, 'food', now(), now()),
(gen_random_uuid(), 'Sweets & Desserts', 'Indian sweets and global desserts', 'piece', 'food', true, 'food', now(), now()),
(gen_random_uuid(), 'Beverages', 'Juices, tea, coffee, shakes, etc.', 'ml', 'food', true, 'food', now(), now()),
(gen_random_uuid(), 'Snacks & Starters', 'Appetizers and snackable items', 'plate', 'food', true, 'food', now(), now()),
(gen_random_uuid(), 'Bakery Items', 'Buns, bread, pastries, etc.', 'piece', 'food', true, 'food', now(), now()),
(gen_random_uuid(), 'Combo Meals', 'Packaged multiple item meals', 'box', 'food', true, 'food', now(), now()),

-- 🛒 Grocery Categories
(gen_random_uuid(), 'Fruits & Vegetables', 'Fresh produce from farms', 'kg', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Pulses & Lentils', 'Toor dal, moong dal, etc.', 'kg', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Cooking Oils', 'Sunflower, mustard, coconut oil, etc.', 'litre', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Masalas & Spices', 'Chili, turmeric, garam masala, etc.', 'gram', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Grains & Rice', 'Wheat, rice, flour, etc.', 'kg', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Packaged Food', 'Instant foods, noodles, soup, etc.', 'packet', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Baking Essentials', 'Maida, yeast, baking soda, etc.', 'gram', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Dairy Products', 'Milk, curd, butter, cheese, etc.', 'litre', 'grocery', true, 'grocery', now(), now()),
(gen_random_uuid(), 'Snacks & Namkeen', 'Chips, mixtures, khakra, etc.', 'packet', 'grocery', true, 'grocery', now(), now()),

-- 💻 Electronics Categories
(gen_random_uuid(), 'Mobile Accessories', 'Cables, chargers, cases, etc.', 'piece', 'electronics', true, 'electronics', now(), now()),
(gen_random_uuid(), 'Computer Peripherals', 'Mouse, keyboard, drives, etc.', 'piece', 'electronics', true, 'electronics', now(), now()),
(gen_random_uuid(), 'Smart Devices', 'Wearables, smart bulbs, plugs', 'piece', 'electronics', true, 'electronics', now(), now()),
(gen_random_uuid(), 'Home Appliances', 'Iron box, mixer, air fryer, etc.', 'piece', 'electronics', true, 'electronics', now(), now());
