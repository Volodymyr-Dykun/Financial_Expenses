package com.financial.menu;

import com.financial.services.JsonService;
import com.financial.services.MapService;

import java.io.IOException;

public class MenuExit {
    public void exit() {
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
