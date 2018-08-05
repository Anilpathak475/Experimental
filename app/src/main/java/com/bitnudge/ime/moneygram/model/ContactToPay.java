package com.bitnudge.ime.moneygram.model;

public class ContactToPay {
    private String name;
    private String contactNo;

    public ContactToPay(String name, String contactNo) {
        this.name = name;
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public String getContactNo() {
        return contactNo;
    }
}
