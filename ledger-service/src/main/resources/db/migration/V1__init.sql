USE ledger_service;

CREATE TABLE t_wallet (
    id VARCHAR(36) PRIMARY KEY,
    wallet_id VARCHAR(36) NOT NULL,
    account_id VARCHAR(36) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    available_balance NUMERIC(19, 2) DEFAULT 0 NOT NULL,
    reserved_balance NUMERIC(19, 2) DEFAULT 0 NOT NULL,
    created_at TIMESTAMP NOT NULL
);