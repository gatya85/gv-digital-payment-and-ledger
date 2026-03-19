package com.gv.dpal.payment.service.topup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTopUpWalletHandler {

    public boolean topUpWallet(){
        return true;
    }
}
