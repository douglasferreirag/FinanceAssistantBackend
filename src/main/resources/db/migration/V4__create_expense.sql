-- V4__create_expense.sql
CREATE TABLE expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    cost DOUBLE NOT NULL CHECK (cost > 0),
    expense_date DATE NOT NULL,
    person_id BIGINT NOT NULL,
    goal_id BIGINT,

    CONSTRAINT fk_expense_person FOREIGN KEY (person_id)
        REFERENCES person(id) ON DELETE CASCADE,

    CONSTRAINT fk_expense_goal FOREIGN KEY (goal_id)
        REFERENCES goal(id) ON DELETE SET NULL
);
