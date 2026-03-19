package com.gv.dpal.payment.model.payment;

import com.gv.dpal.payment.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "t_payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;
    private String idempotencyKey;
    private UUID walletId;
    private BigDecimal amount;
    private Currency currency;
    private PaymentType type;
    private PaymentStatus status;
}
