package com.gv.dpal.ledger.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "t_ledger")
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ledgerId;
    private UUID paymentId;
    private UUID walletId;
    private String entryType;
    private BigDecimal amount;
    private Currency currency;
    private Instant createdAt;
}
