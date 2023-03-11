-- liquibase formatted sql

-- changeset Korolenko Sergey:1
CREATE TABLE todo_user
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR,
    login    VARCHAR UNIQUE,
    password TEXT
);
