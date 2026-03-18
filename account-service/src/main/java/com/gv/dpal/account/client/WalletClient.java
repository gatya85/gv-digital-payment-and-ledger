package com.gv.dpal.account.client;

import com.gv.dpal.account.dto.wallet.CreateWalletRequest;
import com.gv.dpal.account.dto.wallet.CreateWalletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "http://localhost:8081", accept = "application/json")
public interface WalletClient {

    @PostExchange("api/wallets")
    CreateWalletResponse createWallet(@RequestBody CreateWalletRequest createWalletRequest);
}
