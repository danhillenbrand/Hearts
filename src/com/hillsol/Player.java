package com.hillsol;

import java.util.HashSet;
import java.util.Set;

public abstract class Player {
    private final String name;
    private PlayerHand playerHand;
    private Set<Card> takenCards;
    private int currentHandScore;
    private int currentGameScore;
    PassThreeStrategy passThreeStrategy;
    PlayHandStrategy playHandStrategy;
    private Set<Card> threePassingCards;

    public Player(String name) {
        this.name = name;
        this.playerHand = new PlayerHand();
        takenCards = new HashSet<>();
        currentGameScore = 0;
    }

    public String getName() {
        return name;
    }

    public PlayerHand getPlayerHand() {
        return playerHand;
    }

    public Set<Card> getTakenCards() {
        return takenCards;
    }

    public void addTakenCard(Card takenCard) {
        this.takenCards.add(takenCard);
    }

    public void clearTakenCards(){
        takenCards.clear();
    }

    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public void addGameScore(int score){
        currentGameScore += score;
    }

    public int getCurrentHandScore() {
        return currentHandScore;
    }

    public Set<Card> getThreePassingCards() {
        return threePassingCards;
    }

    public void setThreePassingCards(Set<Card> threePassingCards) {
        this.threePassingCards = threePassingCards;
    }




//    public void setPassThreeStrategy(PassThreeStrategy passThreeStrategy) {
//        this.passThreeStrategy = passThreeStrategy;
//    }

    public Set<Card> executePassThreeCards() {
        return passThreeStrategy.passThreeCards(getPlayerHand());
    }

//    public void setPlayHandStrategy(PlayHandStrategy playHandStrategy) {
//        this.playHandStrategy = playHandStrategy;
//    }

    public Card executePlayCard() {
        return playHandStrategy.playCard();
    }

    public void addHandScore(int score){
        currentHandScore += score;
    }

    public void resetHandScore(){
        currentHandScore = 0;
    }

    public String toString(){
        return (name + "; score: " + currentGameScore + "; cards:" + playerHand);
    }
}
