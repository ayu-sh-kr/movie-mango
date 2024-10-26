CREATE TYPE gender AS ENUM ('male', 'female', 'other');

DROP TABLE IF EXISTS account CASCADE;
CREATE TABLE account
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP default now(),
    updated_at TIMESTAMP
);

DROP TABLE IF EXISTS profile CASCADE;
CREATE TABLE profile
(
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

CREATE TYPE movie_genre AS ENUM(
    'action', 'adventure', 'comedy', 'drama', 'fantasy',
    'horror', 'mystery', 'romance', 'science fiction', 'thriller',
    'western', 'animation', 'documentary', 'musical', 'crime', 'family',
    'historical', 'war', 'sport', 'biography'
);

DROP TABLE IF EXISTS movie CASCADE;
CREATE TABLE movie(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(1000),
    release     DATE,
    duration    DOUBLE PRECISION,
    genre       movie_genre,
    director    VARCHAR(255),
    rating      DOUBLE PRECISION,
    language    VARCHAR(255),
    origin      VARCHAR(255),
    created_at  TIMESTAMP default now(),
    updated_at  TIMESTAMP
);