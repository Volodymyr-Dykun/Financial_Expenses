package com.financial.menu.command;

import com.financial.services.JsonService;
import com.financial.services.MapService;

import java.io.IOException;

public class CommandExit implements Command{

    public CommandExit(String[] command){
        execute(command);
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
