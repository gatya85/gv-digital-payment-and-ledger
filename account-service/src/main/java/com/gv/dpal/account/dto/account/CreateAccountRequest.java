package com.gv.dpal.account.dto.account;

import com.gv.dpal.account.model.account.AccountStatus;
import com.gv.dpal.account.model.account.AccountType;

public record CreateAccountRequest (String userId, String fullName, String eMail, AccountStatus status, AccountType type) {
}
