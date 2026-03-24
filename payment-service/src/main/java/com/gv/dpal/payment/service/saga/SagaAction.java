package com.gv.dpal.payment.service.saga;

public interface SagaAction {

    void execute();
    void compensate();
}
