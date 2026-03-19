package com.gv.dpal.payment.model.saga;

public enum Status {
    INITIATED, VALIDATED, RESERVED, RISK_APPROVED, POSTED, COMPLETED, FAILED;
}
