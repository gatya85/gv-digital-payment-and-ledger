package com.gv.dpal.account.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedEvent {

    private String accountId;
    private String email;
}
