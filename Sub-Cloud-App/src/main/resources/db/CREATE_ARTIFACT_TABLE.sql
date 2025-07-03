CREATE TABLE artifact (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    created_date DATE NOT NULL,
    expiry_date DATE,
    issued_by VARCHAR(255),
    description TEXT
);
