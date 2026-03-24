package com.gv.dpal.payment.service.saga;

import lombok.Getter;

import java.util.Stack;

@Getter
public abstract class AbstractSagaHandler {

    private final Stack<SagaAction> sagaActions;

    protected AbstractSagaHandler(Stack<SagaAction> sagaActions) {
        this.sagaActions = sagaActions;
    }

    void appendAction(SagaAction sagaAction) {
        sagaActions.push(sagaAction);
    }

    void executeCompensations() {
        while (!sagaActions.isEmpty()) {
            SagaAction sagaAction = sagaActions.pop();
            sagaAction.compensate();
        }
    }
}
