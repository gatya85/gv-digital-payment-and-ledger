package com.gv.dpal.payment.service.transfermoney;

import com.gv.dpal.payment.dto.TransferMoneyDto;

public abstract class Handler {

    private Handler next;

    public Handler setNexthandler(Handler next){
        this.next = next;
        return next;
    }

    public abstract boolean handle(TransferMoneyDto transferMoneyDto);

    protected boolean handleNext(TransferMoneyDto transferMoneyDto){
        if (next == null){
            return true;
        }
        return next.handle(transferMoneyDto);
    }
}
