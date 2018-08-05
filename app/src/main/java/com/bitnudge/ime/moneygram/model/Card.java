package com.bitnudge.ime.moneygram.model;

public class Card {
    private String cardNo;
    private int drawableId;

    public Card(String cardNo, int drawableId) {
        this.cardNo = cardNo;
        this.drawableId = drawableId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
