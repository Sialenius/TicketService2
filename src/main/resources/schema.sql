CREATE TYPE user_role AS ENUM ('CLIENT', 'ADMIN');

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE users
(
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(15) NOT NULL,
    creation_date date,
    user_role USER_ROLE NOT NULL DEFAULT 'CLIENT'
);

CREATE TABLE tickets
(
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    user_id VARCHAR(36)
    ticket_type ticket_type NOT NULL,
    creation_date date,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE, NOT NULL
);

