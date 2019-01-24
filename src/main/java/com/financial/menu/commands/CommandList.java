package com.financial.menu.commands;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;

import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.JsonService;
import com.financial.services.MapService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CommandList extends CommandAbs {

    private Map<Date, ArrayList<Expense>> map;
    private JsonService jsonService;
    private MapService mapService;


    public CommandList() {
        name = "list";
        mapService = new MapService();
        jsonService = new JsonService();
        map = jsonService.readJson();
    }

    public void execute(String[] arr) {
        /*
         * commands "list" should consist of one word, so we check this and print list expense
         * map must have one or more date
         */

        if (map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
        }
        list(arr);

        new CheckExit();
    }


    private void list(String[] arr) {
        if (arr.length == 1) {
            if (map.size() != 0) {
                mapService.printMap(map);
            } else {
                System.out.println("Expense list is empty!");
                System.out.println("Add Expenses first");
            }

        } else {
            System.out.println("The commands \"List\" should contain only one word, please, try again!!");
        }
    }
}
