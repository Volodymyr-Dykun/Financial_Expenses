package com.financial.menu.command;

import com.financial.menu.CheckExit;
import com.financial.menu.Menu;

public class CommandList implements Command{

    public CommandList(String[] command){
        execute(command);
        new CheckExit();
    }

    public void execute(String[] arr) {
        /*
         * command "list" should consist of one word, so we check this and print list expense
         * map must have one or more date
         */

        if (Menu.mapService.map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
        }
        list(arr);
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
            System.out.println("The command \"List\" should contain only one word, please, try again!!");
        }
    }
}
