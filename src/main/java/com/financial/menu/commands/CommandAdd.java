package com.financial.menu.commands;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;
import com.financial.menu.Menu;
import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.DateService;
import com.financial.services.JsonService;

import java.io.IOException;
import java.util.Map;

public class CommandAdd extends CommandAbs {



    public CommandAdd() {
        name = "add";
    }

    public void execute(String[] arr) {
        // check length array (must be 5 or more)
        if (arr.length < 5) {
            System.out.println("You entered the commands incorrectly,  try again!!");
            return;
        }

        Expense expense = new Expense();
        expense.setDate(DateService.dateTest(arr[1]));
        expense.setPrice(Double.parseDouble(arr[2]));
        expense.setCurrency(currencyService.checkCurrency(arr[3].toUpperCase()));

        // add all words after commandAbsList[3] in name
        String name = arr[4];
        for (int i = 5; i < arr.length; i++) {
            name = name + " " + arr[i];
        }
        expense.setName(name);
        mapService.add(expense);
        try {
            JsonService.writeJson(JsonService.LINK,  mapService.map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new CheckExit();
    }
}
