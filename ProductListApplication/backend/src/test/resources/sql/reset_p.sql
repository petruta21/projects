DROP TABLE products IF EXISTS;
CREATE TABLE products
(id SERIAL PRIMARY KEY,
 name TEXT NOT NULL,
 category TEXT   NOT NULL);
