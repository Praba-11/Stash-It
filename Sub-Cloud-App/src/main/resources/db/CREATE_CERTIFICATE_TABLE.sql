CREATE TABLE certificate (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    issued_on DATE NOT NULL,
    expired_on DATE,
    organization VARCHAR(255),
    description TEXT
);
