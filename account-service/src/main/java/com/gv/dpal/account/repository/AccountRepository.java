package com.gv.dpal.account.repository;

import com.gv.dpal.account.model.Account;
import com.gv.dpal.account.model.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> getAccountByAccountIdAndStatus(UUID accountId, AccountStatus status);
}
