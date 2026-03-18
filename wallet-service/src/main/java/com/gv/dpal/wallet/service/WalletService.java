package com.gv.dpal.wallet.service;

import com.gv.dpal.wallet.dto.CreateWalletRequest;
import com.gv.dpal.wallet.dto.CreateWalletResponse;
import com.gv.dpal.wallet.dto.TopUpWalletRequest;
import com.gv.dpal.wallet.dto.TopUpWalletResponse;
import com.gv.dpal.wallet.model.Wallet;
import com.gv.dpal.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public CreateWalletResponse createWallet(CreateWalletRequest createWalletRequest) {
        Wallet wallet = new Wallet(
            null,
                createWalletRequest.accountId(),
                createWalletRequest.currency(),
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
        walletRepository.save(wallet);

        return new CreateWalletResponse(
                wallet.getWalletId(),
                wallet.getAccountId(),
                wallet.getCurrency(),
                wallet.getAvailableBalance(),
                wallet.getReservedBalance(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt());
    }

    public TopUpWalletResponse topUpWallet(TopUpWalletRequest topUpWalletRequest){
        Optional<Wallet> walletOptional = walletRepository.findById(topUpWalletRequest.walletId());
        if(walletOptional.isPresent()){
            Wallet wallet = walletOptional.get();
            BigDecimal availableBalance =  wallet.getAvailableBalance();
            wallet.setAvailableBalance(availableBalance.add(topUpWalletRequest.amount()));
            walletRepository.save(wallet);
            return new TopUpWalletResponse(
                    wallet.getWalletId(),
                    wallet.getAccountId(),
                    wallet.getCurrency(),
                    wallet.getAvailableBalance(),
                    wallet.getReservedBalance(),
                    wallet.getCreatedAt(),
                    wallet.getUpdatedAt());
        }else {
            throw new RuntimeException("Wallet not found!");
        }
    }

}
