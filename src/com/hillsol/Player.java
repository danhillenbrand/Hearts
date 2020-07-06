package com.hillsol;

import com.hillsol.passthreestrategies.PassThreeStrategy;
import com.hillsol.playhandstrategies.PlayHandStrategy;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final String name;
    private PlayerHand playerHand;
    private Set<Card> takenCards;
    private int currentHandScore;
    private int currentGameScore;
    private PassThreeStrategy passThreeStrategy;
    private PlayHandStrategy playHandStrategy;
    private Set<Card> threePassingCards;
    private int overallFirstPlaceGames;

//    public Player(String name, PassThreeStrategy passThreeStrategy, PlayHandStrategy playHandStrategy) {
    public Player(String name, PassThreeStrategy passThreeStrategy, PlayHandStrategy playHandStrategy) {
        this.name = name;
        this.playerHand = new PlayerHand();
        this.passThreeStrategy = passThreeStrategy;
        this.playHandStrategy = playHandStrategy;
        takenCards = new HashSet<>();
        currentGameScore = 0;
        overallFirstPlaceGames = 0;
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

    public Set<Card> executePassThreeCards() {
        return passThreeStrategy.passThreeCards(getPlayerHand());
    }

    public Card executePlayCard(Suit leadingSuit, boolean heartsAreBroken) {
        return playHandStrategy.playCard(leadingSuit, playerHand, heartsAreBroken);
    }

    public void addHandScore(int score){
        currentHandScore += score;
    }

    public void addWin(){
        overallFirstPlaceGames++;
    }

    public int getOverallFirstPlaceGames() {
        return overallFirstPlaceGames;
    }

    public String getPassThreeStrategyName() {
        return passThreeStrategy.getClass().getSimpleName();
    }

    public String getPlayHandStrategyName() {
        return playHandStrategy.getClass().getSimpleName();
    }

    public void resetHandScore(){
        currentHandScore = 0;
    }

    public String toString(){
        return (name + "; score: " + currentGameScore + "; cards:" + playerHand);
    }

    public void reset() {
        resetHandScore();
        currentHandScore = 0;
        currentGameScore = 0;
    }
}
