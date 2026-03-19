package com.gv.dpal.account.mapper;

import com.gv.dpal.account.dto.account.*;
import com.gv.dpal.account.model.account.Account;

public class AccountMapper {

    public static AccountStatus toDtoStatus(com.gv.dpal.account.model.account.AccountStatus accountModelStatus){
        if (accountModelStatus == null) return null;

        return switch (accountModelStatus) {
            case ACTIVE -> AccountStatus.ACTIVE;
            case CLOSED -> AccountStatus.CLOSED;
            default -> throw new IllegalArgumentException("Unknown status: " + accountModelStatus);
        };
    }

    public static AccountType toDtoType(com.gv.dpal.account.model.account.AccountType accountModelType){
        if (accountModelType == null) return null;

        return switch (accountModelType) {
            case GOLD -> AccountType.GOLD;
            case SILVER -> AccountType.SILVER;
            case SAVING -> AccountType.SAVING;
            default -> throw new IllegalArgumentException("Unknown type: " + accountModelType);
        };
    }

    public static Account toAccount(CreateAccountRequest request) {
        return new Account(
                null,
                request.userId(),
                request.fullName(),
                request.eMail(),
                request.status(),
                request.type()
        );
    }

    public static CreateAccountResponse toCreateAccountResponse(Account account) {
        return new CreateAccountResponse(
                account.getAccountId(),
                account.getUserId(),
                account.getFullName(),
                account.getEMail(),
                account.getStatus(),
                account.getType(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

    public static AccountDetailsResponse toAccountDetailsResponse(Account account) {
        return new AccountDetailsResponse(
                account.getAccountId(),
                account.getUserId(),
                account.getFullName(),
                account.getEMail(),
                toDtoStatus(account.getStatus()),
                toDtoType(account.getType()),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
