--liquibase formatted sql

--changeset serg:create_notification_task_table
CREATE TABLE notification_task
(
    Id SERIAL PRIMARY KEY,
    chat_id INTEGER,
    text TEXT,
    date TIMESTAMP
)