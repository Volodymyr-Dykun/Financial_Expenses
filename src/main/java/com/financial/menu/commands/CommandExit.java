package com.financial.menu.commands;

import com.financial.menu.commands.abstractCommands.CommandAbs;

public class CommandExit extends CommandAbs {

    public CommandExit() {
        name = "exit";
    }

    public void execute(String[] command) {

        System.out.println("Thank you for using our program.");
        System.exit(0);
    }
}
