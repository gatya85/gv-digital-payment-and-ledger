package com.gv.dpal.payment.service.topup;

import com.gv.dpal.payment.dto.CreatePaymentRequest;
import com.gv.dpal.payment.model.saga.*;
import com.gv.dpal.payment.repository.PaymentSagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentTopUpSagaHandler {

    private final PaymentSagaRepository paymentSagaRepository;

    public Long initTopUpPaymentSaga(CreatePaymentRequest createPaymentRequest){
        PaymentSaga paymentSaga = paymentSagaRepository.save(
                new PaymentSaga(
                    null,
                    "IdempotenceKey",
                    TransactionType.TOP_UP,
                    Status.INITIATED,
                    createPaymentRequest.cardNumber(),
                    createPaymentRequest.accountId(),
                    createPaymentRequest.walletId(),
                    createPaymentRequest.amount(),
                    createPaymentRequest.currency(),
                    RiskStatus.PENDING,
                    LedgerStatus.NOT_POSTED,
                    ReservationStatus.NONE,
                    null,
                    null,
                    null,
                    Instant.now())
        );
        return paymentSaga.getSagaId();
    }

    public void changeTopUpPaymentSagaStatus(Long sagaId, Status status){
        Optional<PaymentSaga> paymentSagaOptional = paymentSagaRepository.findById(sagaId);
        if(paymentSagaOptional.isPresent()){
            PaymentSaga paymentSaga = paymentSagaOptional.get();
            paymentSaga.setStatus(status);
            paymentSagaRepository.save(paymentSaga);
        }
    }

    public void changeTopUpPaymentSagaRiskStatus(Long sagaId, RiskStatus riskStatus){
        Optional<PaymentSaga> paymentSagaOptional = paymentSagaRepository.findById(sagaId);
        if(paymentSagaOptional.isPresent()){
            PaymentSaga paymentSaga = paymentSagaOptional.get();
            paymentSaga.setRiskStatus(riskStatus);
            paymentSagaRepository.save(paymentSaga);
        }
    }

    public void changeTopUpPaymentSagaLedgerStatus(Long sagaId, LedgerStatus ledgerStatus){
        Optional<PaymentSaga> paymentSagaOptional = paymentSagaRepository.findById(sagaId);
        if(paymentSagaOptional.isPresent()){
            PaymentSaga paymentSaga = paymentSagaOptional.get();
            paymentSaga.setLedgerStatus(ledgerStatus);
            paymentSagaRepository.save(paymentSaga);
        }
    }

    public void changeTopUpPaymentSagaReservationStatus(Long sagaId, ReservationStatus reservationStatus){
        Optional<PaymentSaga> paymentSagaOptional = paymentSagaRepository.findById(sagaId);
        if(paymentSagaOptional.isPresent()){
            PaymentSaga paymentSaga = paymentSagaOptional.get();
            paymentSaga.setReservationStatus(reservationStatus);
            paymentSagaRepository.save(paymentSaga);
        }
    }
}
