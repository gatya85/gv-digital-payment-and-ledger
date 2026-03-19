package com.gv.dpal.fraud.controller;

import com.gv.dpal.fraud.api.ApiResponse;
import com.gv.dpal.fraud.api.ApiResponseStatus;
import com.gv.dpal.fraud.service.FraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frauds")
@RequiredArgsConstructor
public class FraudController {

    private final FraudService fraudService;

    @GetMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Boolean>> getAccountDetails(@PathVariable String cardNumber){
        Boolean result = fraudService.isFraud(cardNumber);
        return ResponseEntity.ok(
                ApiResponse.<Boolean>builder()
                        .status(ApiResponseStatus.SUCCESS)
                        .message(result ? "Card number is valid:"+cardNumber : "You could be a fraud victim")
                        .data(result)
                        .correlationId("-")
                        .build());
    }
}
