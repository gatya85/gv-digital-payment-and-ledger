package com.gv.dpal.payment.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "t_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;
    private String idempotencyKey;
    private UUID fromWalletId;
    private UUID toWalletId;
    private BigDecimal amount;
    private Currency type;
    private PaymentStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
