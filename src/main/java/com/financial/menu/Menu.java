package com.financial.menu;

import com.financial.menu.command.*;
import com.financial.services.CurrencyService;
import com.financial.services.MapService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static CurrencyService currencyService;
    public static MapService mapService;


    private static final String CASE_ADD = "add";
    private static final String CASE_LIST = "list";
    private static final String CASE_CLEAR = "clear";
    private static final String CASE_TOTAL = "total";
    private static final String CASE_EXIT = "exit";

    public Menu() {
        mapService = new MapService();
        currencyService = new CurrencyService();
        printMenuStart();
    }





    public void printMenuStart() {
        System.out.println("Enter the command:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
         /*
         we break the entered string to pull out the command and the date
         arr[0] - its command
         arr[1] - its date (if it is needed)
         arr[2] - its price (if it is needed)
         arr[3] - its currency (if it is needed)
         arr[4] and next - its name (if it is needed)
           */

            String insert = reader.readLine();
            String[] arr = insert.split(" ");


            switch (arr[0]) {
                case CASE_ADD:
                    new CommandAdd(arr);
                    break;
                case CASE_LIST:
                    new CommandList(arr);
                    break;
                case CASE_CLEAR:
                    new CommandClear(arr);
                    break;
                case CASE_TOTAL:
                   new CommandTotal(arr);
                    break;
                case CASE_EXIT:
                    new CommandExit(arr);
                    break;
                default:
                    System.out.println("There is no such command, please, try again!!");
                    printMenuStart();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}