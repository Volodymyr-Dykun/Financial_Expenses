package com.financial.menu;

import com.financial.services.CurrencyService;
import com.financial.services.DateService;
import com.financial.services.MapService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    private static final String CASE_ADD = "add";
    private static final String CASE_LIST = "list";
    private static final String CASE_CLEAR = "clear";
    private static final String CASE_TOTAL = "total";
    private static final String CASE_EXIT = "exit";

    public static CurrencyService currencyService = new CurrencyService();
    public static DateService dateService = new DateService();
    public static MapService mapService = new MapService();


    public void printMenuStart() {
        System.out.println("Enter the command:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MenuCheck check = new MenuCheck();
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
                 MenuAdd menuAdd = new MenuAdd();
                    menuAdd.menuAdd(arr);
                    check.check();
                    break;
                case CASE_LIST:
                    MenuList menuList = new MenuList();
                    menuList.menuList(arr);
                    check.check();
                    break;
                case CASE_CLEAR:
                    MenuClear menuClear = new MenuClear();
                    menuClear.menuClear(arr);
                    check.check();
                    break;
                case CASE_TOTAL:
                    MenuTotal menuTotal = new MenuTotal();
                    menuTotal.menuTotal(arr);
                    check.check();
                    break;
                case CASE_EXIT:
                    MenuExit menuExit = new MenuExit();
                    menuExit.exit();
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