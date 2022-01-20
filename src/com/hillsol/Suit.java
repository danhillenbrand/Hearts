package com.hillsol;

import java.util.concurrent.ThreadLocalRandom;

public enum Suit {

    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private final String displayValue;

    Suit(final String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static Suit getRandomSuit(){
        int index = ThreadLocalRandom.current().nextInt(0, Suit.values().length);
        return Suit.values()[index];
    }
}
