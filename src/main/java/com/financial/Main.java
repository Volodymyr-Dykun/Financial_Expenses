package com.financial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    private static final String CASE_ADD = "add";
    private static final String CASE_LIST = "list";
    private static final String CASE_CLEAR = "clear";
    private static final String CASE_Total = "total";
    private static final String CASE_EXIT = "exit";


    private Map<LocalDate, ArrayList<Expense>> map;
    private Double currencyEur;

    public Main() {
        map = new TreeMap<>();
        currencyEur = 0.0;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menuStart();
    }

    private void menuStart() {
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
                    menuAdd(arr);
                    checkMenu();
                    break;
                case CASE_LIST:
                    menuList(arr);
                    checkMenu();
                    break;
                case CASE_CLEAR:
                    menuClear(arr);
                    checkMenu();
                    break;
                case CASE_Total:
                    menuTotal(arr);
                    checkMenu();
                    break;
                case CASE_EXIT:
                    menuExit();
                    break;
                default:
                    System.out.println("There is no such command, please, try again!!");
                    menuStart();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method parsed date from String to LocalDate
    private LocalDate checkDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            // if dates format unreal, we become to start menu
            System.out.println("You entered the wrong date, please, try again!!");
            menuStart();
            return null;
        }
    }

    private String checkCurrency(String currency) {
        try {
            if (ApiUtils.parseCurrentApiJson(currency) != null) {
                return currency;
            } else {
                System.out.println("This currency does not exist. Try again.");
                checkMenu();
                return null;
            }

        } catch (NullPointerException e) {
            System.out.println("This currency does not exist. Try again.");
            menuStart();
            return null;
        }
    }

    private void menuAdd(String[] arr) {
        // check length array (must be 5 or more)
        if (arr.length < 5) {
            System.out.println("You entered the command incorrectly,  try again!!");
            return;
        }

        Expense expense = new Expense();
        expense.setDate(checkDate(arr[1]));
        expense.setPrice(Double.parseDouble(arr[2]));
        expense.setCurrency(checkCurrency(arr[3].toUpperCase()));

        // add all words after arr[3] in name
        String name = arr[4];
        for (int i = 5; i < arr.length; i++) {
            name = name + " " + arr[i];
        }
        expense.setName(name);
        add(expense);
    }

    private void menuList(String[] arr) {
        /*
        * command "list" should consist of one word, so we check this and print list expense
        * map must have one or more date
        */
        if (map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
            checkMenu();
        }
        list(arr);

    }

    private void menuClear(String[] arr) {
        clear(arr[1]);
    }

    private void menuTotal(String[] arr) {
        try {
            total(arr[1].toUpperCase());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have not entered the currency, please, try again!! ");
            menuStart();
        }
    }

    private void menuExit() {
        System.out.println("Thank you for using our program.");
        System.exit(0);
    }

    // The method checks if you want to leave or stay
    private void checkMenu() {
        System.out.println("Want to continue?");
        System.out.println("YES - enter \"Y\"");
        System.out.println("NO - enter \"N\"");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*
        * if you want leave - call menuExit
        * if you want stay - call menuStart
        */
        try {
            String insert = reader.readLine();
            switch (insert.toUpperCase()) {
                case "Y":
                    menuStart();
                    break;
                case "N":
                    menuExit();
                    break;
                default:
                    System.out.println("You have entered the wrong answer, try again.");
                    checkMenu();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method checks for the date in the array and edd expense
    public void add(Expense expense) {
        /*
          * if this date is present - we add to this date new expense
          * if this date is absent - we add new date with expense
         */

        for (Map.Entry<LocalDate, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(expense.getDate())) {
                map.get(expense.getDate()).add(expense);
                currencyEur = currencyEur + ApiUtils.convertCurrencyToEur(expense.getPrice(), expense.getCurrency());
                printMap();
                return;
            }
        }
        ArrayList<Expense> list = new ArrayList<>();
        list.add(expense);
        map.put(expense.getDate(), list);
        currencyEur = currencyEur + ApiUtils.convertCurrencyToEur(expense.getPrice(), expense.getCurrency());
        printMap();

    }

    private void list(String[] arr) {
        if (arr.length == 1) {
            if (map.size() != 0) {
                printMap();
            } else {
                System.out.println("Expense list is empty!");
                System.out.println("Add Expenses first");
                checkMenu();
            }

        } else {
            System.out.println("The command \"List\" should contain only one word, please, try again!!");
            menuStart();
        }
    }

    private void printMap() {

        for (Map.Entry<LocalDate, ArrayList<Expense>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            printList(entry.getValue());

        }
    }

    private void printList(ArrayList<Expense> list) {
        for (Expense item : list) {
            System.out.println(item.getName() + " " + item.getPrice() + " " + item.getCurrency());
        }
        System.out.println();
    }

    // clear all expense for this Date
    private void clear(String date) {
        // if you have not entered more expenses
        if (map.size() == 0) {
            System.out.println("You don't have any more expenses!");
            System.out.println();
            checkMenu();
        }
        int a = 0;
        // if you have entered at least one expense
        for (Map.Entry<LocalDate, ArrayList<Expense>> entry : map.entrySet()) {
            if (entry.getKey().equals(checkDate(date))) {
                a++;
                for (int i = 0; i < entry.getValue().size(); i++) {
                    currencyEur = currencyEur - ApiUtils.convertCurrencyToEur(entry.getValue().get(i).getPrice(), entry.getValue().get(i).getCurrency());
                }
                map.remove(checkDate(date));

                if (map.size()!=0) {
                    System.out.println("Map after remote:");
                    printMap();
                } else System.out.println("Map after deletion is empty");
                return;
            }

            if (a == 0) {
                System.out.println(date + " is absent in map, try clear other date!");
                checkMenu();
            }
        }
    }

    // after all convertCurrencyToEur we convert currencyEur to the chosen currency
    private void total(String currency) {
        if (map.size() != 0) {
            try {
                Double coef;
                if (!currency.equals(ApiUtils.LOCAL_CURRENCY)) {
                    coef = ApiUtils.parseCurrentApiJson(currency);
                } else coef = 1.0;

                Double total = currencyEur * coef;
                String formattedTotal = new DecimalFormat("#0.00").format(total);
                System.out.println(formattedTotal + " " + currency);
            } catch (NullPointerException e) {
                System.out.println("No currency with this name was found, please, try again!!");
                menuStart();
            }
        } else {
            System.out.println("Expense list is empty!");
            System.out.println("Add Expenses first");
            checkMenu();
        }

    }
}
