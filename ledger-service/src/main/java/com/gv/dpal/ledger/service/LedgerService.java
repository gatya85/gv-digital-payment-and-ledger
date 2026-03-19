package com.gv.dpal.ledger.service;

import com.gv.dpal.ledger.dto.CreateTopUpSagaLedgerRequest;
import com.gv.dpal.ledger.dto.CreateTopUpSagaLedgerResponse;
import com.gv.dpal.ledger.repository.LedgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public CreateTopUpSagaLedgerResponse createTopUpSagaLedger(CreateTopUpSagaLedgerRequest createTopUpSagaLedgerRequest){
        return null;
    }

}
