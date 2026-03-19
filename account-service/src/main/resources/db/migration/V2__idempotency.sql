USE account_service;

CREATE TABLE t_idempotency_record (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      idempotency_key VARCHAR(128) NOT NULL,
                                      http_status INT NOT NULL,
                                      response_body JSON NOT NULL,
                                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      CONSTRAINT uk_idempotency_key UNIQUE (idempotency_key)
);