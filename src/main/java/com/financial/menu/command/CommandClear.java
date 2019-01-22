package com.financial.menu.command;

import com.financial.expense.Expense;
import com.financial.menu.CheckExit;
import com.financial.menu.Menu;

import java.util.ArrayList;
import java.util.Map;

public class CommandClear implements Command {

    public CommandClear(String[] command){
        execute(command);
    }

    private Map<String, ArrayList<Expense>> map = Menu.mapService.map;



    public void execute(String[] arr) {
        clear(arr[1]);
    }

    // clear all expense for this Date

    private void clear(String date) {

        // if you have not entered more expenses
        if (map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
            new CheckExit();
        }
        int a = 0;
        // if you have entered at least one expense
        for (Map.Entry<String, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(date)) {
                a++;
                for (int i = 0; i < entry.getValue().size(); i++) {
                }
                map.remove(date);

                if (map.size()!=0) {
                    System.out.println("Map after remote:");
                    Menu.mapService.printMap();
                } else System.out.println("Map after deletion is empty");
                return;
            }

            if (a == 0) {
                System.out.println(date + " is absent in map, try clear other date!");
                new CheckExit();
            }
        }
    }
}
