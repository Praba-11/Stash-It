CREATE TABLE artifact (
    id SERIAL PRIMARY KEY,
    member_id VARCHAR(100) NOT NULL,
    title VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    container_name VARCHAR(100) NOT NULL,
    created_date DATE NOT NULL,
    expiry_date DATE,
    issued_by VARCHAR(255),
    description TEXT
);
