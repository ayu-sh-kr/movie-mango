CREATE TYPE IF NOT EXISTS gender AS ENUM ('male', 'female', 'other');

CREATE TABLE IF NOT EXISTS account
(
    id         SERIAL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP default now(),
    updated_at TIMESTAMP
);


CREATE TABLE IF NOT EXISTS profile
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

CREATE TYPE IF NOT EXISTS movie_genre AS ENUM(
    'action', 'adventure', 'comedy', 'drama', 'fantasy',
    'horror', 'mystery', 'romance', 'science fiction', 'thriller',
    'western', 'animation', 'documentary', 'musical', 'crime', 'family',
    'historical', 'war', 'sport', 'biography'
);

CREATE TABLE IF NOT EXISTS movie
(
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

CREATE TABLE IF NOT EXISTS cast_profile
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    age        INT,
    dob        DATE,
    gender     gender,
    country    VARCHAR(255),
    created_at TIMESTAMP default now(),
    updated_at TIMESTAMP
);

CREATE TYPE IF NOT EXISTS movie_role AS ENUM(
    'lead actor', 'supporting actor', 'director', 'producer',
    'cinematographer', 'editor', 'composer'
);

CREATE TABLE IF NOT EXISTS movie_cast
(
    id         SERIAL PRIMARY KEY,
    cast_id    BIGINT not null,
    movie_id   BIGINT not null,
    role       movie_role not null,
    created_at TIMESTAMP default now(),
    updated_at TIMESTAMP,
    CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES movie(id),
    CONSTRAINT fk_cast FOREIGN KEY (cast_id) REFERENCES cast_profile(id)
)