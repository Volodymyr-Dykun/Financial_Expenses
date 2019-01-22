package com.financial.services;

import com.financial.menu.CheckExit;


public class CurrencyService {

    public String checkCurrency(String currency) {
        try {
            if (DataFixerService.parseCurrentApiJson(currency) != null) {
                return currency;
            } else {
                System.out.println("This currency does not exist. Try again.");
                new CheckExit();
                return null;
            }

        } catch (NullPointerException e) {
            System.out.println("This currency does not exist. Try again.");
            new CheckExit();
            return null;
        }
    }
}
