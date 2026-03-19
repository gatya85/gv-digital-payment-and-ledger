package com.gv.dpal.fraud.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FraudService {

    public Boolean isFraud(String cardNumber){
        return new Random().nextBoolean();
    }
}
