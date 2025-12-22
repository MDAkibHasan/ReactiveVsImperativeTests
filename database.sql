CREATE DATABASE reactive_benchmark;

\c reactive_benchmark;

CREATE TABLE user_activity (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    action VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO user_activity (username, action)
SELECT 
    'user_' || generate_series,
    'LOGIN'
FROM generate_series(1, 10000);
