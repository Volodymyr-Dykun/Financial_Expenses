package com.financial.menu;

import com.financial.menu.commands.*;
import com.financial.services.CurrencyService;
import com.financial.services.MapService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static CurrencyService currencyService;
    public static MapService mapService;
    private ArrayCommands commands;

    public Menu() {
        mapService = new MapService();
        currencyService = new CurrencyService();
        commands = new ArrayCommands();
        printMenuStart();
    }

    public void printMenuStart() {
        System.out.println("Enter the commands:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
         /*
         we break the entered string to pull out the commands and the date
         input[0] - its commands
         input[1] - its date (if it is needed)
         input[2] - its price (if it is needed)
         input[3] - its currency (if it is needed)
         input[4] and next - its name (if it is needed)
           */

            String insert = reader.readLine();
            String[] input = insert.split(" ");

            commands.findCommand(input);
//            switch (CommandEnum.findCommand(input[0])) {
//                case CASE_ADD:
//                    commands.add(input);
//                    break;
//                case CASE_LIST:
//                    commands.list(input);
//                    break;
//                case CASE_CLEAR:
//                    commands.clear(input);
//                    break;
//                case CASE_TOTAL:
//                    commands.total(input);
//                    break;
//                case CASE_EXIT:
//                    commands.exit(input);
//                    break;
//            }
//        } catch (NullPointerException e) {
//            System.out.println("There is no such commands, please, try again!!");
//            printMenuStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}