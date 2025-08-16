CREATE UNLOGGED TABLE payments(
    correlation_id VARCHAR(255) NOT NULL PRIMARY KEY,
    amount numeric(19,2) NOT NULL,
    status VARCHAR(32) NOT NULL,
    chosen_processor VARCHAR(32),
    requested_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_payments_status on payments(status);
CREATE INDEX IF NOT EXISTS idx_payments_requested_at ON payments (requested_at);