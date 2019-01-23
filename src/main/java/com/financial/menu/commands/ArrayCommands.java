package com.financial.menu.commands;

import com.financial.menu.CheckExit;
import com.financial.menu.commands.abstractCommands.CommandAbs;

import java.util.ArrayList;
import java.util.List;

public class ArrayCommands {

    private CommandAbs list;
    private CommandAbs total;
    private CommandAbs exit;
    private CommandAbs clear;
    private CommandAbs add;

    public List<CommandAbs> commandAbsList = new ArrayList<>();

   public ArrayCommands() {
        list = new CommandList();
        total = new CommandTotal();
        exit = new CommandExit();
        clear = new CommandClear();
        add = new CommandAdd();
        commandAbsList.add(add);
        commandAbsList.add(clear);
        commandAbsList.add(exit);
        commandAbsList.add(total);
        commandAbsList.add(list);
    }

    public void findCommand (String[] input) {
       int count=0;

        for (int i = 0; i<commandAbsList.size(); i++) {
            if (commandAbsList.get(i).name.equals(input[0])) {
                commandAbsList.get(i).execute(input);
                count++;
            }
        }
        if (count==0) {
            System.out.println("The command not found");
            System.out.println();
            CheckExit.check();
        }
    }
}
