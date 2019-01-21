package com.financial.services;

import com.financial.menu.MenuCheck;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateService {

    private MenuCheck check = new MenuCheck();

    //this method parsed date from String to LocalDate
    public LocalDate checkDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            // if dates format unreal, we become to start menu
            System.out.println("You entered the wrong date, please, try again!!");
            check.check();
            return null;
        }
    }
}
