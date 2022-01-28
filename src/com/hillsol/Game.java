package com.hillsol;

import java.util.*;

public class Game {
    /*
    Create 4 players;
    Designate the order of players for playing the game.
    play hands until a player breaks 100 points:
    - shuffle cards
    - deal cards
    - cycle for every hand: pass left, pass right, pass across, no passing;
    - play thirteen hands of tricks, leading with 2 of clubs, then taker of previous trick
    - sum scores; don't forget that shoot-the-moon thing

     */
    List<Player> playerList = new LinkedList<>();

    public Game(final Player... players) {
        if (players.length != 4) throw new RuntimeException("Game must have exactly 4 players.");
        // The order of players in varargs is the order in which they play the game.
        Collections.addAll(playerList, players);
    }

    public void playGame() {
        Deck52 deck = new Deck52();
        // set up pass-three-cards instructions: left, right, across, none
        PassDirection[] passDirections = PassDirection.values();
        int directionIndex = 0;

        while (didSomeoneLose() == false) {
            Hand hand = new Hand(playerList);
            int passCardsOffset = passDirections[directionIndex].getOffset();
            hand.playHand(deck, passCardsOffset);
            directionIndex++;
            if (directionIndex > 3) directionIndex = 0;
        }
    }

    public void printPlayers() {
        System.out.println("Players:");
        for (Player player : playerList) {
            System.out.println(player);
        }
    }

    public List<Player> getPlayers(){
        return playerList;
    }
    public Set<Player> getWinningPlayers() {
        // Can be more than one player with same low (winning) score
        Set<Player> winners = new HashSet<>();
        if (didSomeoneLose() == false) return winners;
        // todo:  replace this with stream, min reduction, Collect Players
        int lowScore = Integer.MAX_VALUE;
        for (Player player : playerList) {
            if (player.getCurrentGameScore() < lowScore) {
                lowScore = player.getCurrentGameScore();
            }
        }
        for (Player player : playerList) {
            if (player.getCurrentGameScore() == lowScore) {
                winners.add(player);
            }
        }
        return winners;
    }

    private boolean didSomeoneLose() {
        boolean result = false;
        for (Player player : playerList) {
            if (player.getCurrentGameScore() >= 100) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void reset() {
        for (Player player : playerList) {
            player.reset();
        }
    }
}
