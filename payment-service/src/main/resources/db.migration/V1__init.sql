USE account_service;

CREATE TABLE t_payment (
    payment_id CHAR(36) PRIMARY KEY,
    idempotency_key VARCHAR(255) NOT NULL,

    from_wallet_id CHAR(36) NOT NULL,
    to_wallet_id CHAR(36) NOT NULL,

    amount DECIMAL(19, 4) NOT NULL,
    currency VARCHAR(3) NOT NULL,

    status VARCHAR(50) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_idempotency_key UNIQUE (idempotency_key),

    CONSTRAINT chk_amount_positive CHECK (amount > 0),

    INDEX idx_from_wallet (from_wallet_id),
    INDEX idx_to_wallet (to_wallet_id),
    INDEX idx_status (status)
);