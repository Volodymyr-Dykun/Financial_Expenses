package com.financial.enumPackage;

public enum CommandEnum {
    CASE_ADD ("addToMap"),
    CASE_LIST ("list"),
    CASE_CLEAR ("clear"),
    CASE_TOTAL ("total"),
    CASE_EXIT ("exit");


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

     CommandEnum (String name) {
         this.name = name;
     }

    public static CommandEnum findCommand(String str) {

        for (CommandEnum value : CommandEnum.values()) {
            if (value.getName().equals(str)) {
                return value;
            }
        }
        return null;
    }
}
