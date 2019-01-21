package com.financial.services;

import com.financial.menu.MenuCheck;


public class CurrencyService {

    private MenuCheck check = new MenuCheck();

    public String checkCurrency(String currency) {
        try {
            if (ApiService.parseCurrentApiJson(currency) != null) {
                return currency;
            } else {
                System.out.println("This currency does not exist. Try again.");
                check.check();
                return null;
            }

        } catch (NullPointerException e) {
            System.out.println("This currency does not exist. Try again.");
            check.check();
            return null;
        }
    }
}
