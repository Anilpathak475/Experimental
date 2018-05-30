package com.bitnudge.ime.demo.modle;

public class Card {
    private String cardNo;
    private int id;

    public Card(String cardNo, int id) {
        this.cardNo = cardNo;
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public int getId() {
        return id;
    }
}
