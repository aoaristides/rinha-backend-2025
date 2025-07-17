CREATE UNLOGGED TABLE payments(
    id             VARCHAR(255)             not null,
    correlation_id VARCHAR(255)             not null primary key,
    amount         DECIMAL                  not null,
    requested_at   timestamp with time zone not null,
    processed_at_default BOOLEAN NOT NULL DEFAULT true
);

CREATE INDEX payments_requested_at_processed_default ON payments (requested_at, processed_at_default);