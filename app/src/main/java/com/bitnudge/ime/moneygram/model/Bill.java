package com.bitnudge.ime.moneygram.model;

public class Bill {
    private String name;
    private String detail;

    public Bill(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }


}
