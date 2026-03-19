package com.gv.dpal.payment.controller;

import com.gv.dpal.payment.dto.CreatePaymentRequest;
import com.gv.dpal.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPayment(@RequestBody CreatePaymentRequest createPaymentRequest){
        paymentService.topUpAccountWallet(createPaymentRequest);
        return "Top-up Successfully finished";
    }
}
