package com.gv.dpal.payment.service;

import com.gv.dpal.payment.service.transfermoney.AccountHandler;
import com.gv.dpal.payment.service.transfermoney.PaymentHandler;
import com.gv.dpal.payment.service.transfermoney.WalletHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentSagaService {

    private final AccountHandler accountHandler;
    private final PaymentHandler paymentHandler;
    private final WalletHandler walletHandler;

    public void transferMoneyBetweenAccounts(){
        validateSenderAndReceiverAccounts();
        reserveSenderFounds();
        evaluateTransferRisk();
        postLedgerEntries();
        finalizeTransfer();
        sendNotification();

    }

    private void validateSenderAndReceiverAccounts(){

    }

    private void reserveSenderFounds(){

    }

    private void evaluateTransferRisk(){

    }

    private void postLedgerEntries(){

    }

    private void finalizeTransfer(){

    }

    private void sendNotification(){

    }


}
