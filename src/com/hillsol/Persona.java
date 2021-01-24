package com.hillsol;

public enum Persona {
    GENIOUS(1),
    SMARTY(1),
    AVERAGE(1),
    UNSKILLED(1);

    // do Strategy Pattern when fully implemented
    private final int strategy;

    Persona(final int strategy){
        this.strategy = strategy;
    }

}
