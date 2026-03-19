package com.gv.dpal.ledger.controller;

import com.gv.dpal.ledger.api.ApiResponse;
import com.gv.dpal.ledger.dto.CreateTopUpSagaLedgerRequest;
import com.gv.dpal.ledger.dto.CreateTopUpSagaLedgerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LedgerController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<CreateTopUpSagaLedgerResponse>> createTopUpSagaLedger(@RequestBody CreateTopUpSagaLedgerRequest createTopUpSagaLedgerRequest){

        return null;
    }

}
