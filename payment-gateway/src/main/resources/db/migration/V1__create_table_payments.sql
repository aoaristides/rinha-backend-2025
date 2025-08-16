CREATE UNLOGGED TABLE payments(
    id BIGSERIAL PRIMARY KEY,
    correlation_id VARCHAR(255) NOT NULL UNIQUE,
    amount NUMERIC(19,2) NOT NULL,
    status VARCHAR(32) NOT NULL,
    chosen_processor VARCHAR(32),
    fee_applied NUMERIC(19,2),
    net_amount NUMERIC(19,2),
    requested_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE UNLOGGED TABLE processor_attempts (
    id BIGSERIAL PRIMARY KEY,
    payment_id BIGINT NOT NULL REFERENCES payments(id) ON DELETE CASCADE,
    processor VARCHAR(32) NOT NULL,
    success BOOLEAN NOT NULL,
    http_status INTEGER,
    latency_ms BIGINT,
    error TEXT,
    created_at timestamptz NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_payments_status on payments(status);
CREATE INDEX IF NOT EXISTS idx_payments_requested_at ON payments (requested_at);
CREATE INDEX IF NOT EXISTS idx_attempts_payment on processor_attempts(payment_id);