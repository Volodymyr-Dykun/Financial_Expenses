package com.financial.menu.commands;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;
import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.CurrencyService;
import com.financial.services.DataFixerService;
import com.financial.services.JsonService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CommandTotal extends CommandAbs {

    private Map<Date, ArrayList<Expense>> map;
    CurrencyService currencyService;
    JsonService jsonService;

    public CommandTotal() {
        currencyService = new CurrencyService();
        jsonService = new JsonService();
        map = jsonService.readJson();
        name = "total";

    }

    public void execute(String[] arr) {
        try {
            total(arr[1].toUpperCase());
            new CheckExit();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have not entered the currency, please, try again!! ");
            new CheckExit();
        }
    }

    // after all convertCurrencyToEur we convert currencyEur to the chosen currency
    private void total(String currency) {
        if (map.size() != 0) {
            try {
                Double coef;
                if (!currency.equals(DataFixerService.LOCAL_CURRENCY)) {
                    coef = DataFixerService.parseCurrentApiJson(currency);
                } else coef = 1.0;

                Double total = currencyService.calculateCurrencyEur() * coef;
                String formattedTotal = new DecimalFormat("#0.00").format(total);
                System.out.println(formattedTotal + " " + currency);

            } catch (NullPointerException e) {
                System.out.println("No currency with this name was found, please, try again!!");
            }
        } else {
            System.out.println("Expense list is empty!");
            System.out.println("Add Expenses first");
        }

    }
}
