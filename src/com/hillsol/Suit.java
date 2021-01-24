package com.hillsol;

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
        int index = (int) (Math.random() * Suit.values().length);
        return Suit.values()[index];
    }
}
