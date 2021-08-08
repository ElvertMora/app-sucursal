CREATE TABLE branch
(
    id        SERIAL PRIMARY KEY,
    address CHARACTER VARYING(255) NOT NULL,
    latitude   NUMERIC                NOT NULL,
    longitude  NUMERIC                NOT NULL
);
