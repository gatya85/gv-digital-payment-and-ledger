package com.gv.dpal.payment.service;

import com.gv.dpal.payment.dto.CreatePaymentRequest;
import com.gv.dpal.payment.model.saga.ReservationStatus;
import com.gv.dpal.payment.model.saga.RiskStatus;
import com.gv.dpal.payment.model.saga.Status;
import com.gv.dpal.payment.service.topup.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentTopUpSagaHandler paymentSagaHandler;
    private final PaymentTopUpHandler paymentHandler;
    private final PaymentTopUpValidationHandler paymentValidationHandler;
    private final PaymentTopUpRiskHandler paymentRiskHandler;
    private final PaymentTopUpWalletHandler paymentWalletHandler;
    private final PaymentTopUpNotificationHandler paymentNotificationHandler;

    public String topUpAccountWallet(CreatePaymentRequest createPaymentRequest){
        final Long sagaId = paymentSagaHandler.initTopUpPaymentSaga(createPaymentRequest);
        final UUID paymentId = paymentHandler.createPayment(createPaymentRequest);

        if(paymentValidationHandler.isValidTopUp()){
            paymentSagaHandler.changeTopUpPaymentSagaStatus(sagaId, Status.VALIDATED);
        }else {
            paymentSagaHandler.changeTopUpPaymentSagaStatus(sagaId, Status.FAILED);
            paymentHandler.createPaymentCompensation(createPaymentRequest);
            return "Validation Failed";
        }

        paymentSagaHandler.changeTopUpPaymentSagaRiskStatus(sagaId, RiskStatus.PENDING);

        if(paymentRiskHandler.doesPymentSafe()){
            paymentSagaHandler.changeTopUpPaymentSagaRiskStatus(sagaId, RiskStatus.APPROVED);
            paymentSagaHandler.changeTopUpPaymentSagaStatus(sagaId, Status.RISK_APPROVED);
        }else{
            paymentSagaHandler.changeTopUpPaymentSagaRiskStatus(sagaId, RiskStatus.REJECTED);
            paymentSagaHandler.changeTopUpPaymentSagaStatus(sagaId, Status.FAILED);
            paymentHandler.createPaymentCompensation(createPaymentRequest);
            return "Fraud Detected";
        }

        paymentSagaHandler.changeTopUpPaymentSagaReservationStatus(sagaId, ReservationStatus.PENDING);
        if(paymentHandler.cardMoneyHasReserved()){
            paymentSagaHandler.changeTopUpPaymentSagaReservationStatus(sagaId, ReservationStatus.ACTIVE);
        }else{
            paymentSagaHandler.changeTopUpPaymentSagaReservationStatus(sagaId, ReservationStatus.FAILED);
            paymentHandler.createPaymentCompensation(createPaymentRequest);
            paymentHandler.cardMoneyReservedCompensation();
            return "Card reservation failed";
        }

        if(paymentWalletHandler.topUpWallet()){
            paymentSagaHandler.changeTopUpPaymentSagaStatus(sagaId, Status.POSTED);
            paymentNotificationHandler.sendTopUpNotification();
        }else{
            paymentSagaHandler.changeTopUpPaymentSagaStatus(sagaId, Status.FAILED);
            paymentHandler.createPaymentCompensation(createPaymentRequest);
            paymentHandler.cardMoneyReservedCompensation();
        }

        return null;
    }
}
