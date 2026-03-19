package com.gv.dpal.account.model.account;


import com.gv.dpal.account.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "t_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "account_id", length = 36, nullable = false, updatable = false)
    private UUID accountId;
    private String userId;
    private String fullName;
    private String eMail;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private AccountType type;
}
