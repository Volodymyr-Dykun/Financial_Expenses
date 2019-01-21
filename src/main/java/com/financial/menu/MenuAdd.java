package com.financial.menu;

import com.financial.expense.Expense;

public class MenuAdd {

    public void menuAdd(String[] arr) {
        // check length array (must be 5 or more)
        if (arr.length < 5) {
            System.out.println("You entered the command incorrectly,  try again!!");
            return;
        }

        Expense expense = new Expense();
        expense.setDate(arr[1]);
        System.out.println(expense.getDate());
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
