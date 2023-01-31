DROP TABLE registration IF EXISTS;
CREATE TABLE registration
(id SERIAL PRIMARY KEY,
 username TEXT NOT NULL,
 mail  TEXT  NOT NULL,
 password TEXT   NOT NULL);

