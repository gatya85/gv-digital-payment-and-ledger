package com.gv.dpal.account.service;

import com.gv.dpal.account.client.WalletClient;
import com.gv.dpal.account.dto.account.CreateAccountRequest;
import com.gv.dpal.account.dto.account.CreateAccountResponse;
import com.gv.dpal.account.model.Account;
import com.gv.dpal.account.model.AccountStatus;
import com.gv.dpal.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final WalletClient walletClient;

    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest){
        Account account = new Account(
                null,
                createAccountRequest.userId(),
                createAccountRequest.fullName(),
                createAccountRequest.eMail(),
                createAccountRequest.status(),
                createAccountRequest.type());
        accountRepository.save(account);

        return new CreateAccountResponse(
                account.getAccountId(),
                account.getUserId(),
                account.getFullName(),
                account.getEMail(),
                account.getStatus(),
                account.getType(),
                account.getCreatedAt(),
                account.getCreatedAt());
    }

    public boolean isActive(UUID uuid){
        return accountRepository.getAccountByAccountIdAndStatus(uuid, AccountStatus.ACTIVE).isPresent();
    }
}
