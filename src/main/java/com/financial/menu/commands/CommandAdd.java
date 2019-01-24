package com.financial.menu.commands;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;
import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.CurrencyService;
import com.financial.services.DateService;
import com.financial.services.JsonService;
import com.financial.services.MapService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CommandAdd extends CommandAbs {

    private JsonService jsonService;
    private MapService mapService;
    private Map<Date, ArrayList<Expense>>  map;

    public CommandAdd() {
        name = "add";
        mapService = new MapService();
        jsonService = new JsonService();
        map = jsonService.readJson();

    }

    public void execute(String[] arr) {
        // check length array (must be 5 or more)
        if (arr.length < 5) {
            System.out.println("You entered the commands incorrectly,  try again!!");
            new CheckExit();
            return;
        }

        Expense expense = new Expense();
        expense.setDate(DateService.dateToDate(arr[1]));
        expense.setPrice(Double.parseDouble(arr[2]));
        expense.setCurrency(CurrencyService.checkCurrency(arr[3].toUpperCase()));

        // add all words after commandAbsList[4] in name
        String name = arr[4];
        for (int i = 5; i < arr.length; i++) {
            name = name + " " + arr[i];
        }
        expense.setName(name);


        mapService.addToMap(expense, map);
        jsonService.writeJson(map);

        new CheckExit();
    }
}
