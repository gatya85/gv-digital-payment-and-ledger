package com.gv.dpal.payment.model.saga;

import com.gv.dpal.payment.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "t_payment_saga")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSaga extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sagaId;
    private String idempotencyKey;

    private TransactionType transactionType;
    private Status status;

    private String sourceCardNumber;
    private String destinationAccountId;
    private String destinationWalletId;

    private BigDecimal amount;
    private Currency currency;

    private RiskStatus riskStatus;
    private LedgerStatus ledgerStatus;
    private ReservationStatus reservationStatus;

    private String failureCode;
    private String failureReason;

    private String compensationId;

    private Instant completedAt;
}
