package com.gv.dpal.account.controller;

import com.gv.dpal.account.api.ApiResponse;
import com.gv.dpal.account.api.ApiResponseStatus;
import com.gv.dpal.account.dto.account.AccountDetailsResponse;
import com.gv.dpal.account.dto.account.CreateAccountRequest;
import com.gv.dpal.account.dto.account.CreateAccountResponse;
import com.gv.dpal.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<CreateAccountResponse>> createAccount(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestBody CreateAccountRequest createAccountRequest){

        return ResponseEntity.ok(accountService.createAccount(idempotencyKey, createAccountRequest));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountDetailsResponse closeAccount(){
        //TODO need to implement later
        return null;
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<AccountDetailsResponse>> getAccountDetails(@PathVariable String accountId){
        AccountDetailsResponse account = accountService.getAccountDetails(UUID.fromString(accountId));
        return ResponseEntity.ok(
                ApiResponse.<AccountDetailsResponse>builder()
                        .status(ApiResponseStatus.SUCCESS)
                        .message("Account is found")
                        .data(account)
                        .correlationId("-")
                        .build());
    }

    @GetMapping("/{accountId}/validation")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<Boolean>> accountIsValid(@PathVariable String accountId){
        boolean response = accountService.isActive(UUID.fromString(accountId));
        log.info("accountId: {}", accountId);
        return ResponseEntity.ok(
                ApiResponse.<Boolean>builder()
                        .status(ApiResponseStatus.SUCCESS)
                        .message(response ? "Account is valid" : "Account is not valid")
                        .data(response)
                        .correlationId("-")
                        .build());
    }

}
