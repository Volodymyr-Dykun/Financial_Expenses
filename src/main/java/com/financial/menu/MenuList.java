package com.financial.menu;

public class MenuList {

    MenuCheck menuCheck = new MenuCheck();

    public void menuList(String[] arr) {
        /*
         * command "list" should consist of one word, so we check this and print list expense
         * map must have one or more date
         */

        if (Menu.mapService.map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
            menuCheck.check();
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
                menuCheck.check();
            }

        } else {
            System.out.println("The command \"List\" should contain only one word, please, try again!!");
            menuCheck.check();
        }
    }
}
