package com.financial.menu.commands;

import com.financial.menu.commands.abstractCommands.CommandAbs;
import com.financial.services.JsonService;
import com.financial.services.MapService;

import java.io.IOException;

public class CommandExit extends CommandAbs {

    public CommandExit() {
        name = "exit";
    }

    public void execute(String[] command) {
        try {
            JsonService service = new JsonService();
            service.writeJson(JsonService.LINK, MapService.map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Thank you for using our program.");
        System.exit(0);
    }
}
