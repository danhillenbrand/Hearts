package com.hillsol;

public class PlayerStats {
    private final String name;
    private int gamesWon;

    public PlayerStats(String name){
        this.name = name;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
}
