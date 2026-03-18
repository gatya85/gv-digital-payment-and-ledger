package com.gv.dpal.wallet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "t_wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "wallet_id", length = 36, nullable = false, updatable = false)
    private UUID walletId;
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "account_id", length = 36, nullable = false, updatable = false)
    private UUID accountId;
    private Currency currency;
    private BigDecimal availableBalance;
    private BigDecimal reservedBalance;

}
