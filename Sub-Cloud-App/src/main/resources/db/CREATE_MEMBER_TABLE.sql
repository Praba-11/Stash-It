CREATE TABLE member (
    id SERIAL PRIMARY KEY,
    roll_no VARCHAR(20) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    full_name VARCHAR(100),
    department VARCHAR(50),
    email_address VARCHAR(100),
    designation VARCHAR(50),
    phone_number BIGINT
);
