package com.financial.menu.command;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;
import com.financial.menu.Menu;

public class CommandAdd implements Command{

    public CommandAdd(String[] command) {
        execute(command);
        new CheckExit().check();
    }


    public void execute(String[] arr) {
        // check length array (must be 5 or more)
        if (arr.length < 5) {
            System.out.println("You entered the command incorrectly,  try again!!");
            return;
        }

        Expense expense = new Expense();
        expense.setDate(arr[1]);
        expense.setPrice(Double.parseDouble(arr[2]));
        expense.setCurrency(Menu.currencyService.checkCurrency(arr[3].toUpperCase()));

        // add all words after arr[3] in name
        String name = arr[4];
        for (int i = 5; i < arr.length; i++) {
            name = name + " " + arr[i];
        }
        expense.setName(name);
        Menu.mapService.add(expense);
    }
}
