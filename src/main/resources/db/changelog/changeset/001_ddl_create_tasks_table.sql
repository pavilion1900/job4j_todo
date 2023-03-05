-- liquibase formatted sql

-- changeset Korolenko Sergey:1
CREATE TABLE tasks
(
    id          SERIAL PRIMARY KEY,
    description TEXT,
    created     TIMESTAMP,
    done        BOOLEAN
);
