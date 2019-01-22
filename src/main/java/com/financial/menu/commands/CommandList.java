package com.financial.menu.commands;

import com.financial.menu.CheckExit;
import com.financial.menu.Menu;
import com.financial.menu.commands.abstractCommands.CommandAbs;

public class CommandList extends CommandAbs {

    public CommandList() {
        name = "list";
    }

    public void execute(String[] arr) {
        /*
         * commands "list" should consist of one word, so we check this and print list expense
         * map must have one or more date
         */

        if (Menu.mapService.map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
        }
        list(arr);

        new CheckExit();
    }


    private void list(String[] arr) {
        if (arr.length == 1) {
            if (Menu.mapService.map.size() != 0) {
                Menu.mapService.printMap();
            } else {
                System.out.println("Expense list is empty!");
                System.out.println("Add Expenses first");
            }

        } else {
            System.out.println("The commands \"List\" should contain only one word, please, try again!!");
        }
    }
}
