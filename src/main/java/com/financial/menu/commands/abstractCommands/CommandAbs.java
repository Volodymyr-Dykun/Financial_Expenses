package com.financial.menu.commands.abstractCommands;

import com.financial.services.CurrencyService;
import com.financial.services.MapService;


public abstract class CommandAbs implements Command{

    public String name;

    protected static CurrencyService currencyService;
    protected static MapService mapService;

    public CommandAbs() {
        mapService = new MapService();
        currencyService = new CurrencyService();
    }
}
