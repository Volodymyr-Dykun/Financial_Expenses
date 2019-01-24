package com.financial.menu.commands;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;
import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.DateService;
import com.financial.services.JsonService;
import com.financial.services.MapService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CommandClear extends CommandAbs {

    private Map<Date, ArrayList<Expense>> map;
    private JsonService jsonService;
    private MapService mapService;

    public CommandClear () {
        name = "clear";
        mapService = new MapService();
        jsonService = new JsonService();
        map = jsonService.readJson();
    }

    public void execute(String[] arr) {

        // if you have not entered more expenses
        if (map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
            new CheckExit();
        }

        int a = 0;

        Date date = DateService.dateToDate(arr[1]);

        // if you have entered at least one expense
        for (Map.Entry<Date, ArrayList<Expense>> entry : map.entrySet()) {
            System.out.println(date);
            System.out.println(entry.getKey());
            if (entry.getKey().equals(date)) {
                a++;
                for (int i = 0; i < entry.getValue().size(); i++) {
                }
                map.remove(date);

                if (map.size()!=0) {
                    System.out.println("Map after remote:");
                    mapService.printMap(map);

                    jsonService.writeJson(map);

                    new CheckExit();
                } else {
                    System.out.println("Map after deletion is empty");

                    jsonService.writeJson(map);

                    new CheckExit();
                }
                return;
            }

            if (a == 0) {
                System.out.println(arr[1] + " is absent in map, try clear other date!");
                new CheckExit();
            }
        }
    }

}
