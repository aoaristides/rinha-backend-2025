CREATE UNLOGGED TABLE payments(
    correlation_id VARCHAR(255) NOT NULL PRIMARY KEY,
    amount DECIMAL(19,2) NOT NULL,
    processed_by BOOLEAN NOT NULL DEFAULT TRUE,
    requested_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX payments_requested_at_processed_by ON payments (requested_at, processed_by);