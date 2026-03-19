USE payment_service;

CREATE TABLE t_payment (
                           payment_id CHAR(36) PRIMARY KEY,

                           idempotency_key VARCHAR(128),

                           wallet_id CHAR(36) NOT NULL,

                           amount DECIMAL(19,4) NOT NULL,
                           currency VARCHAR(3) NOT NULL,
                           type VARCHAR(1) NOT NULL,

                           status VARCHAR(32) NOT NULL,

                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                           CONSTRAINT uk_payment_idempotency UNIQUE (idempotency_key)
);

CREATE TABLE t_payment_saga (
                                saga_id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                idempotency_key VARCHAR(128),

                                transaction_type VARCHAR(32) NOT NULL,
                                status VARCHAR(32) NOT NULL,

                                source_card_number VARCHAR(32),
                                destination_account_id VARCHAR(64),
                                destination_wallet_id VARCHAR(64),

                                amount DECIMAL(19,4) NOT NULL,
                                currency VARCHAR(3) NOT NULL,

                                risk_status VARCHAR(32),
                                ledger_status VARCHAR(32),
                                reservation_status VARCHAR(32),

                                failure_code VARCHAR(64),
                                failure_reason VARCHAR(255),

                                compensation_id VARCHAR(64),

                                completed_at TIMESTAMP NULL,
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);