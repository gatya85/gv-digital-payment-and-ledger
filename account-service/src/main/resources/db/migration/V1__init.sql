USE account_service;

CREATE TABLE t_account (
                           account_id VARCHAR(36) NOT NULL,
                           user_id VARCHAR(255),
                           full_name VARCHAR(255),
                           e_mail VARCHAR(255),
                           status VARCHAR(50),
                           type VARCHAR(50),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           CONSTRAINT pk_t_account PRIMARY KEY (account_id)
);