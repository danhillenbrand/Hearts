package com.hillsol;

import java.util.LinkedList;
import java.util.List;

public class Game {
    /*
    Create 4 players;
    Designate the order of players for playing the game.
    play hands until a player exceeds 100 points:
    - shuffle cards
    - deal cards
    - cycle through: pass left, pass right, pass across, no passing; for every loop
        - advance PassCycle
    - play thirteen rounds of tricks, leading with 2 of clubs, then taker of previous trick
    - sum scores; don't forget that shoot-the-moon thing

     */
    List<Player> playerList = new LinkedList();

    public Game(final String ... names){
        if (names.length != 4) throw new RuntimeException("Game must have exactly 4 players.");
        // The order of players in varargs is the order in which they play the game.
        for (String name: names) playerList.add(new Player(name));
        Deck52 deck = new Deck52();
        System.out.println("Playing a Game ====================");
        while (didSomeoneLose()==false){
            // todo: remove when things are working
//            playerList.get(0).addScore(20);
            // todo: pass cards (left, right, you know the drill)
            Hand hand = new Hand(playerList);
            hand.playHand(deck);

        }
        System.out.println("\n === Game over ===");
    }

    public Player playGame(){
        printPlayers();
        return new Player("Winner");
    }

    public void printPlayers() {
        System.out.println("Players:");
        for (Player player: playerList){
            System.out.println(player);
        }
    }

    private boolean didSomeoneLose(){
        boolean result = false;
        for(Player player: playerList){
            if (player.getCurrentGameScore()>=100) result = true;
        }
        return result;
    }
}
