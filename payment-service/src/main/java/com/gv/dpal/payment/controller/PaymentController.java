package com.gv.dpal.payment.controller;

import com.gv.dpal.payment.dto.PaymentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDto createPayment(){


        return null;
    }
}
