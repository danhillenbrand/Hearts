package com.hillsol;

public enum Rank {
    TWO("2", (byte) 1),
    THREE("3", (byte) 2),
    FOUR("4", (byte) 3),
    FIVE("5", (byte) 4),
    SIX("6", (byte) 5),
    SEVEN("7", (byte) 6),
    EIGHT("8", (byte) 7),
    NINE("9", (byte) 8),
    TEN("10", (byte) 9),
    JACK("Jack", (byte) 10),
    QUEEN("Queen", (byte) 11),
    KING("King", (byte) 12),
    ACE("Ace", (byte) 13);

    private final String displayValue;
    private final byte rankValue;

    Rank(final String displayValue, final byte rank) {
        this.displayValue = displayValue;
        this.rankValue = rank;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public byte getRankValue() {
        return rankValue;
    }
}
