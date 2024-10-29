CREATE TYPE user_role AS ENUM ('CLIENT', 'ADMIN');

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE users_info
(
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(15) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    user_role USER_ROLE NOT NULL DEFAULT 'CLIENT'
);

CREATE TABLE tickets
(
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    ticket_type ticket_type NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users_info (id) ON DELETE CASCADE
);

