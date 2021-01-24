package com.hillsol;

public enum PassDirection {
    /*
    todo: add  a description/instructions of how to use this
     */
    LEFT("left",1),
    RIGHT("right",3),
    ACROSS("across",2),
    NONE("none", 0);

    private final String displayValue;
    private final int offset;

    PassDirection(final String displayValue, final int offset) {
        this.displayValue = displayValue;
        this.offset = offset;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public int getOffset() {
        return offset;
    }
}
