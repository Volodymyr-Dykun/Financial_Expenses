package com.financial.menu;

import com.financial.menu.commands.CommandExit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckExit {

    public CheckExit(){
        check();
    }


    // The method checks if you want to leave or stay
    public static void check() {
        System.out.println("Want to continue?");
        System.out.println("YES - enter \"Y\"");
        System.out.println("NO - enter \"N\"");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*
         * if you want leave - call menuExit
         * if you want stay - call printMenuStart
         */
        try {
            String insert = reader.readLine();
            switch (insert.toUpperCase()) {
                case "Y":
                    new Menu();
                    break;
                case "N":
                   new CommandExit();
                    break;
                default:
                    System.out.println("You have entered the wrong answer, try again.");
                    check();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
