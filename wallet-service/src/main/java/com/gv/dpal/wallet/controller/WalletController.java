package com.gv.dpal.wallet.controller;

import com.gv.dpal.wallet.dto.*;
import com.gv.dpal.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateWalletResponse createWallet(@RequestBody CreateWalletRequest createAccountRequest){
        return walletService.createWallet(createAccountRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public TopUpWalletResponse topUpWallet(@RequestBody TopUpWalletRequest topUpWalletRequest){
        return walletService.topUpWallet(topUpWalletRequest);
    }

    @GetMapping("/{walletId}")
    @ResponseStatus(HttpStatus.OK)
    public GetWalletResponse getWallet(@PathVariable UUID walletId){
        return walletService.getWallet(walletId);
    }

}
