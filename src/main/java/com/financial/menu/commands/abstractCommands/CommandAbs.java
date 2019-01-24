package com.financial.menu.commands.abstractCommands;


import com.financial.services.JsonService;

public abstract class CommandAbs implements Command{

    public String name;


    public CommandAbs() {
        new JsonService();
    }
}
