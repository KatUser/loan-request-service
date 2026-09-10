CREATE TABLE IF NOT EXISTS loan_request (
    id SERIAL PRIMARY KEY,
    status VARCHAR,
    terms VARCHAR
);