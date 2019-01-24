package com.financial.menu;

import com.financial.menu.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    private ArrayCommands commands;

    public Menu() {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}