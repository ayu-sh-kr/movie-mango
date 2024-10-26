CREATE TYPE gender AS ENUM ('male', 'female', 'other');

DROP TABLE IF EXISTS profile CASCADE;
CREATE TABLE profile (
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    age        INT,
    dob        DATE,
    gender     gender,
    street     VARCHAR(255),
    state      VARCHAR(255),
    zip        VARCHAR(8),
    country    VARCHAR(255),
    refer      BIGINT not null unique,
    created_at TIMESTAMP default now(),
    updated_at TIMESTAMP,
    CONSTRAINT fk_account FOREIGN KEY (refer) REFERENCES account (id)
);


DROP TABLE IF EXISTS account CASCADE;
CREATE TABLE account (
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP default now(),
    updated_at TIMESTAMP
);