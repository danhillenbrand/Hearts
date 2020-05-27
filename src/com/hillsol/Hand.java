package com.hillsol;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Hand {



    List<Player> playerList = new LinkedList();
    private boolean areHeartsBroken;




    public Hand(List playerList) {
        this.playerList = playerList;
    }

    public void playHand(Deck52 deck, int passCardsOffset) {
        System.out.println("  Playing a Hand ==================== " + passCardsOffset);
        for (Player player : playerList) {
            player.resetHandScore();
        }
        deck.shuffle();
        deck.shuffle();
        deck.shuffle();
        deal(deck); // todo: rotate dealer.
        if (passCardsOffset != 0) passCards(passCardsOffset); // todo:  clarify condition for a human to read
        areHeartsBroken = false;
        Player leadingPlayer = playTrick(getPlayerWithTwoOfClubs());
        for (int i = 1; i < 13; i++) {
            leadingPlayer = playTrick(leadingPlayer);
        }
        for (Player p:playerList) System.out.println(p.getName()+" : "+p.getCurrentGameScore());
        for (Player player : playerList) {
            if (player.getCurrentHandScore()==26){
                System.out.println(player.getName() + " shot the moon! ====================================");
                for (Player otherPlayer: playerList){
                    if (player!=otherPlayer) otherPlayer.addGameScore(26);
                }
                return;  //  todo: exit loop more noticeably so future dev doesn't miss it
            } else {
                player.addGameScore(player.getCurrentHandScore());
            }
        }
    }

    private void passCards(int passCardsOffset) {  // todo: make passCardsOffset a human-understandable thing (Direction?)

        // Choose cards to pass
        for (Player player : playerList) {
            player.setThreePassingCards(player.executePassThreeCards());
            playerList.indexOf(player);
//            System.out.println(player.getName() + " passes: " + player.getThreePassingCards());
        }

        // Pass cards
        for (Player givingPlayer : playerList) {
            int currentPlayerIndex = playerList.indexOf(givingPlayer);
            int receivingPlayerIndex = currentPlayerIndex + passCardsOffset;
            if (receivingPlayerIndex > 3) receivingPlayerIndex = receivingPlayerIndex - 4;

            Player receivingPlayer = playerList.get(receivingPlayerIndex);
            for (Card card: givingPlayer.getThreePassingCards()){
                receivingPlayer.getPlayerHand().receiveCard(card);
            }
//            System.out.println(receivingPlayer.getName() + " receives: " + givingPlayer.getThreePassingCards());
        }
    }

    private void deal(final Deck52 deck) {
        int playerIndex = 0;    // todo: what's better then this manual index?
        Player player = playerList.get(playerIndex);
        for (Card card : deck.getDeck()) {
            player.getPlayerHand().receiveCard(card);
            playerIndex++;
            if (playerIndex >= playerList.size()) playerIndex = 0; // barf
            player = playerList.get(playerIndex);
        }
    }

    private Player getPlayerWithTwoOfClubs() {
        for (Player player : playerList) {
            if (player.getPlayerHand().getTwoOfClubs() != null) {
                return player;
            }
        }
        return null;
    }

    private Player playTrick(final Player leadingPlayer) {
//        System.out.println("    Playing a Trick ====================");
        Set<Card> trick = new HashSet();
        int playerPosition = playerList.indexOf(leadingPlayer);
        Suit suitLed = null;
        // if player has the two of clubs, lead it (play it)
        // otherwise, if player has card of led suit, play it
        // otherwise, play any card -- includes removing card from playerHand

        Player player = playerList.get(playerPosition);
        Card cardPlayed = player.getPlayerHand().getTwoOfClubs();
        if (cardPlayed == null) cardPlayed = player.getPlayerHand().getRandomCard(areHeartsBroken);

        Rank highestRank = null;
        Player trickTaker = null;

        for (int i = 0; i < 4; i++) {
            if (cardPlayed == null) {
//                cardPlayed = player.getPlayerHand().getRandomCard();
                cardPlayed = player.getPlayerHand().chooseCard(suitLed, areHeartsBroken);
            }
//            System.out.println("          " + player.getName() + ": " + cardPlayed);
            if (suitLed == null) {
                suitLed = cardPlayed.getSuit();
                highestRank = cardPlayed.getRank();
                trickTaker = player;
            }
            trick.add(cardPlayed);
            if (cardPlayed.getSuit() == suitLed) { // todo: make a GreaterThan method for Rank
                if (cardPlayed.getRank().getRankValue() > highestRank.getRankValue()) {
                    highestRank = cardPlayed.getRank();
                    trickTaker = player;
                }
            }
            player.getPlayerHand().removeCard(cardPlayed);
            if (i < 3) {
                playerPosition++;
                if (playerPosition > 3) playerPosition = 0;
                player = playerList.get(playerPosition);
                cardPlayed = null;
            }
        }
        for (Card card : trick) {
            trickTaker.addTakenCard(card);
            if (card.getSuit() == Suit.HEARTS) {
                trickTaker.addHandScore(1);
            } else if (card.getSuit() == Suit.SPADES && card.getRank() == Rank.QUEEN) {
                trickTaker.addHandScore(13);
            }
        }
        return trickTaker;
    }
}
