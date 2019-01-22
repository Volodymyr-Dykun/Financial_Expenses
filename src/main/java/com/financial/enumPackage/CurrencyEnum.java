package com.financial.enumPackage;

public enum CurrencyEnum {
        EUR("EUR"),
        USD("USD");

        private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    CurrencyEnum(String name) {
        this.name=name;
    }
}
