package com.bitnudge.ime.moneygram.model;

public class PayToContainer {
    private String name;
    private Card card;

    public PayToContainer(String name) { this(name, "", 0); }
    public PayToContainer(String name, String cardNo, int id) {
        this.card = new Card(cardNo, id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
