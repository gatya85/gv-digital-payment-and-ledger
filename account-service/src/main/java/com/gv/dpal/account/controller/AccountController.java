package com.gv.dpal.account.controller;

import com.gv.dpal.account.dto.account.AccountDetailsDto;
import com.gv.dpal.account.dto.account.CreateAccountRequest;
import com.gv.dpal.account.dto.account.CreateAccountResponse;
import com.gv.dpal.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private  final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return accountService.createAccount(createAccountRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountDetailsDto closeAccount(){
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountDetailsDto getAccountDetails(@RequestParam String accountId){
        return null;
    }

    @GetMapping("/{accountId}/validation")
    @ResponseStatus(HttpStatus.OK)
    public Boolean accountIsValid(@PathVariable String accountId){
        return accountService.isActive(UUID.fromString(accountId));
    }

}
