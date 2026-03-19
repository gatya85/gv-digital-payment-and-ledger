package com.gv.dpal.account.service;

import com.gv.dpal.account.api.ApiResponse;
import com.gv.dpal.account.api.ApiResponseStatus;
import com.gv.dpal.account.client.WalletClient;
import com.gv.dpal.account.dto.account.AccountDetailsResponse;
import com.gv.dpal.account.dto.account.CreateAccountRequest;
import com.gv.dpal.account.dto.account.CreateAccountResponse;
import com.gv.dpal.account.dto.wallet.CreateWalletRequest;
import com.gv.dpal.account.dto.wallet.CreateWalletResponse;
import com.gv.dpal.account.event.AccountCreatedEvent;
import com.gv.dpal.account.exception.AccountDoesNotExistException;
import com.gv.dpal.account.exception.IdempotencyRecordNotFound;
import com.gv.dpal.account.mapper.AccountMapper;
import com.gv.dpal.account.model.account.Account;
import com.gv.dpal.account.model.account.AccountStatus;
import com.gv.dpal.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final IdempotencyService idempotencyService;
    private final WalletClient walletClient;
    private final KafkaTemplate<String, AccountCreatedEvent> kafkaTemplate;

    public ApiResponse<CreateAccountResponse> createAccount(String idempotencyKey,
                                               CreateAccountRequest createAccountRequest){

        try {
            return idempotencyService.findIdempotencyResponse(idempotencyKey);
        } catch (IdempotencyRecordNotFound e) {
            final Account account = AccountMapper.toAccount(createAccountRequest);
            final Account savedAccount = accountRepository.save(account);

            walletClient.createWallet(
                    new CreateWalletRequest(
                            savedAccount.getAccountId(),
                            Currency.getInstance("GBP")));

            final ApiResponse<CreateAccountResponse> apiResponse = ApiResponse.<CreateAccountResponse>builder()
                    .status(ApiResponseStatus.SUCCESS)
                    .message("Account successfully created")
                    .data(AccountMapper.toCreateAccountResponse(savedAccount))
                    .correlationId("-")
                    .build();

            idempotencyService.saveIdempotencyResponse(idempotencyKey, 200, apiResponse);

            sendOrderCreatedNotification(
                    savedAccount.getAccountId(),
                    createAccountRequest.eMail(),
                    savedAccount);

            return apiResponse;
        }
    }

    private void sendOrderCreatedNotification(UUID accountId, String email, Account account){
        AccountCreatedEvent accountCreatedEvent = new AccountCreatedEvent(
                accountId.toString(),
                email);
        log.info("Start - Sending AccountCreatedEvent {} to Kafka topic to account-created", account);
        kafkaTemplate.send("account-created", accountCreatedEvent);
        log.info("End - Sending AccountCreatedEvent {} to Kafka topic to account-created", account);
    }

    public boolean isActive(UUID accountId){
        return accountRepository.getAccountByAccountIdAndStatus(accountId, AccountStatus.ACTIVE).isPresent();
    }

    public AccountDetailsResponse getAccountDetails(UUID accountId){
        final Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountDoesNotExistException("Account not found: " + accountId));
        return AccountMapper.toAccountDetailsResponse(account);
    }
}
