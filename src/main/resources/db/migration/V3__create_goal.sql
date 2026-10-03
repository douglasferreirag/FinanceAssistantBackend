-- V3__create_goal.sql
CREATE TABLE goal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    month INT NOT NULL CHECK (month BETWEEN 1 AND 12),
    year INT NOT NULL CHECK (year > 0),
    ceiling DOUBLE NOT NULL CHECK (ceiling > 0)
);
