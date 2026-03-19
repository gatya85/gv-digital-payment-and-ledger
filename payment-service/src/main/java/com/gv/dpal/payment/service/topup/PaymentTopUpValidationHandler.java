package com.gv.dpal.payment.service.topup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTopUpValidationHandler {

    public boolean isValidTopUp(){
        return true;
    }

}
