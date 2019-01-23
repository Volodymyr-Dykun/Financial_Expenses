package com.financial.menu.commands;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;
import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.DateService;
import com.financial.services.JsonService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CommandClear extends CommandAbs {

    public CommandClear () {
        name = "clear";
    }

    private Map<Date, ArrayList<Expense>> map = mapService.map;

    public void execute(String[] arr) {

        // if you have not entered more expenses
        if (map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
            new CheckExit();
        }
        int a = 0;

        Date date = DateService.dateTest(arr[1]);

        // if you have entered at least one expense
        for (Map.Entry<Date, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(date)) {
                a++;
                for (int i = 0; i < entry.getValue().size(); i++) {
                }
                map.remove(date);

                if (map.size()!=0) {
                    System.out.println("Map after remote:");
                    mapService.printMap();
                    new CheckExit();
                } else {
                    System.out.println("Map after deletion is empty");
                    new CheckExit();}
                return;
            }

            if (a == 0) {
                System.out.println(arr[1] + " is absent in map, try clear other date!");
                new CheckExit();
            }
        }

        try {
            JsonService.writeJson(JsonService.LINK,  mapService.map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
