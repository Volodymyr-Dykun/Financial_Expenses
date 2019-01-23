package com.financial.menu.commands;

import com.financial.menu.CheckExit;
import com.financial.menu.Menu;
import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.DataFixerService;
import com.financial.expense.Expense;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.Map;

public class CommandTotal extends CommandAbs {

    public CommandTotal() {
        name = "total";
    }

    private Map<Date, ArrayList<Expense>> map = mapService.map;

    public void execute(String[] arr) {
        try {
            total(arr[1].toUpperCase());
            new CheckExit();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have not entered the currency, please, try again!! ");
            new CheckExit();
        }
    }

    public double calculateCurrencyEur(Map<Date, ArrayList<Expense>> map) {

        double eur=0.0;

        for (Map.Entry<Date, ArrayList<Expense>> entry : map.entrySet()) {
            for (Expense item : entry.getValue()) {
                Double coef;
                if (!item.getCurrency().equals(DataFixerService.LOCAL_CURRENCY)) {
                    coef = DataFixerService.parseCurrentApiJson(item.getCurrency());
                } else coef = 1.0;

                eur=eur+item.getPrice()/coef;
            }

        }
        return eur;
    }



    // after all convertCurrencyToEur we convert currencyEur to the chosen currency
    private void total(String currency) {
        if (map.size() != 0) {
            try {
                Double coef;
                if (!currency.equals(DataFixerService.LOCAL_CURRENCY)) {
                    coef = DataFixerService.parseCurrentApiJson(currency);
                } else coef = 1.0;

                Double total = calculateCurrencyEur(map) * coef;
                String formattedTotal = new DecimalFormat("#0.00").format(total);
                System.out.println(formattedTotal + " " + currency);
            } catch (NullPointerException e) {
                System.out.println("No currency with this name was found, please, try again!!");
                new CheckExit();
            }
        } else {
            System.out.println("Expense list is empty!");
            System.out.println("Add Expenses first");
            new CheckExit();
        }

    }
}
