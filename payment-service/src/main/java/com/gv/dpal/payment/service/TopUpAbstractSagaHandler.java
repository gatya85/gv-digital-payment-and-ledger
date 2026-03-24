package com.gv.dpal.payment.service;

import com.gv.dpal.payment.service.saga.SagaAction;
import com.gv.dpal.payment.service.saga.AbstractSagaHandler;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class TopUpAbstractSagaHandler extends AbstractSagaHandler {

    public TopUpAbstractSagaHandler(Stack<SagaAction> sagaActions) {
        super(sagaActions);
    }



}
