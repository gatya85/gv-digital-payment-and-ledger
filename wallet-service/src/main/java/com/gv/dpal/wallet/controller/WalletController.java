package com.gv.dpal.wallet.controller;

import com.gv.dpal.wallet.dto.CreateWalletRequest;
import com.gv.dpal.wallet.dto.CreateWalletResponse;
import com.gv.dpal.wallet.dto.TopUpWalletRequest;
import com.gv.dpal.wallet.dto.TopUpWalletResponse;
import com.gv.dpal.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
