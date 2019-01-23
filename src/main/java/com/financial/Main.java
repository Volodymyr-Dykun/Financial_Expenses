package com.financial;

import com.financial.menu.Menu;
import com.financial.services.DateService;

public class Main {

    public static void main(String[] args) {
//       new Menu();
        DateService.dateTest("2015-10-11");
        System.out.println(DateService.dateTest("2015-10-11").getTime()==1444510800000l);

    }
}
