package com.gv.dpal.payment.service.topup;

import com.gv.dpal.payment.dto.CreatePaymentRequest;
import com.gv.dpal.payment.model.payment.Payment;
import com.gv.dpal.payment.model.payment.PaymentStatus;
import com.gv.dpal.payment.model.payment.PaymentType;
import com.gv.dpal.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentTopUpHandler {

    private final PaymentRepository paymentRepository;

    public UUID createPayment(CreatePaymentRequest createPaymentRequest){
        Payment payment = paymentRepository.save(
                new Payment(
                        null,
                    "idempotencyKey",
                    UUID.fromString(createPaymentRequest.walletId()),
                    createPaymentRequest.amount(),
                    createPaymentRequest.currency(),
                    PaymentType.C,
                    PaymentStatus.IN_PROGRESS));

        return payment.getPaymentId();
    }

    public boolean cardMoneyHasReserved(){
        //Reserve money on the card
        return true;
    }

    public boolean cardMoneyReservedCompensation(){
        //send a message to free up card money
        return true;
    }

    public UUID createPaymentCompensation(CreatePaymentRequest createPaymentRequest){
        Payment payment = paymentRepository.save(
                new Payment(
                        null,
                        "idempotencyKey",
                        UUID.fromString(createPaymentRequest.walletId()),
                        createPaymentRequest.amount(),
                        createPaymentRequest.currency(),
                        PaymentType.D,
                        PaymentStatus.IN_PROGRESS));

        return payment.getPaymentId();
    }

}
