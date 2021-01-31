package com.hillsol;

import com.hillsol.passthreestrategies.PassThreeStrategy;
import com.hillsol.playhandstrategies.PlayHandStrategy;

import java.util.Set;

public class Player {
    private final String name;
    private final PlayerHand playerHand;
    private int currentHandScore;
    private int currentGameScore;
    private final PassThreeStrategy passThreeStrategy;
    private final PlayHandStrategy playHandStrategy;
    private Set<Card> threePassingCards;
    private int overallFirstPlaceGames;
    private int shootTheMoonCount;

    public Player(final String name, final PassThreeStrategy passThreeStrategy, final PlayHandStrategy playHandStrategy) {
        this.name = name;
        this.playerHand = new PlayerHand();
        this.passThreeStrategy = passThreeStrategy;
        this.playHandStrategy = playHandStrategy;
        currentGameScore = 0;
        overallFirstPlaceGames = 0;
    }

    public String getName() {
        return name;
    }

    public PlayerHand getPlayerHand() {
        return playerHand;
    }

    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public void addGameScore(final int score){
        currentGameScore += score;
    }

    public int getCurrentHandScore() {
        return currentHandScore;
    }

    public Set<Card> getThreePassingCards() {
        return threePassingCards;
    }

    public void setThreePassingCards(final Set<Card> threePassingCards) {
        this.threePassingCards = threePassingCards;
    }

    public Set<Card> executePassThreeCards() {
        return passThreeStrategy.passThreeCards(getPlayerHand());
    }

    public Card executePlayCard(final Set<Card> trick, final Suit leadingSuit, final boolean heartsAreBroken) {
        return playHandStrategy.playCard(trick, leadingSuit, playerHand, heartsAreBroken);
    }

    public void addHandScore(final int score){
        currentHandScore += score;
    }

    public void incrementWinCount(){
        overallFirstPlaceGames++;
    }

    public void incrementShootTheMoonCount(){ shootTheMoonCount++; }

    public int getOverallFirstPlaceGames() {
        return overallFirstPlaceGames;
    }

    public int getShootTheMoonCount() { return shootTheMoonCount;}

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
