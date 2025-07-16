CREATE TABLE payments(
    id             VARCHAR(255)             not null primary key,
    correlation_id VARCHAR(255)             not null,
    amount         NUMERIC(19, 2)           not null,
    requested_at   timestamp with time zone not null
);