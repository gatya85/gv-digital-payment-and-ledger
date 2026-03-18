package com.gv.dpal.payment.service.transfermoney;

import com.gv.dpal.payment.dto.TransferMoneyDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentHandler extends Handler {

    @Override
    public boolean handle(TransferMoneyDto transferMoneyDto) {
        return false;
    }
}
