package com.hillsol;

public enum PassDirection {
    LEFT("left",1),
    RIGHT("right",3),
    ACROSS("across",2),
    NONE("none", 0);

    private final String displayValue;
    private final int offset;

    PassDirection(String displayValue, int offset) {
        this.displayValue = displayValue;
        this.offset = offset;
    }
}
