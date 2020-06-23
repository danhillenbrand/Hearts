package com.hillsol;

import java.util.ArrayList;
import java.util.List;

import static com.hillsol.Suit.*;

public class PlayerHand {
    private List<Card> clubs = new ArrayList<>();
    private List<Card> diamonds = new ArrayList<>();
    private List<Card> hearts = new ArrayList<>();
    private List<Card> spades = new ArrayList<>();

    public void receiveCard(Card dealtCard) {
        switch (dealtCard.getSuit()) {
            // todo: candidate for a pattern here, or maybe just better structure:
            // todo also: Why did I make these ArrayLists instead of a Set or something?
            case CLUBS: {
                if (clubs.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal the " + dealtCard + ", which has already been dealt.");
                }
                clubs.add(dealtCard);
                break;
            }
            case DIAMONDS: {
                if (diamonds.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal the " + dealtCard + ", which has already been dealt.");
                }
                diamonds.add(dealtCard);
                break;
            }
            case HEARTS: {
                if (hearts.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal " + dealtCard + ", which has already been dealt.");
                }
                hearts.add(dealtCard);
                break;
            }
            case SPADES: {
                if (spades.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal " + dealtCard + ", which has already been dealt.");
                }
                spades.add(dealtCard);
                break;
            }
            default: {
                throw new RuntimeException("Unknown suit for card: " + dealtCard);
            }
        }
    }

    public Card getTwoOfClubs() {
        for (Card card : clubs) {
            if (card.getRank() == Rank.TWO)
                return card;
        }
        return null;
    }

    // Called when randomly choosing cards to pass
    public Card getRandomCard(boolean canChooseHearts) {
        Card randomCard = null;
        while (randomCard == null) {
            switch (Suit.getRandomSuit()) {
                case CLUBS: {
                    if (clubs.size() > 0)
                        return (clubs.get((int) Math.random() * clubs.size()));
                    break;
                }
                case DIAMONDS: {
                    if (diamonds.size() > 0)
                        return (diamonds.get((int) Math.random() * diamonds.size()));
                    break;
                }
                case HEARTS: {
                    if (!canChooseHearts){
                        if (clubs.size()!=0 || diamonds.size()!=0 || spades.size()!=0){
                            break;
                        } else {
                            canChooseHearts = true;
                        }
                    }
                    if (hearts.size() > 0)
                        return (hearts.get((int) Math.random() * hearts.size()));
                    break;
                }
                case SPADES: {
                    if (spades.size() > 0)
                        return (spades.get((int) Math.random() * spades.size()));
                    break;
                }
            }
        }
        return null;
    }

    public List <Suit> getCurrentSuits(){
        List<Suit> currentSuits = new ArrayList<>();
        if (clubs.size() > 0) currentSuits.add(CLUBS);
        if (diamonds.size() > 0) currentSuits.add(DIAMONDS);
        if (hearts.size() > 0) currentSuits.add(HEARTS);
        if (spades.size() > 0) currentSuits.add(SPADES);
        return currentSuits;
    }

    public Card retrieveSpecificCard(Suit suit, byte rankValue){
        switch (suit) {
            case CLUBS: {
                for (Card card : clubs) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
                break;
            }
            case DIAMONDS: {
                for (Card card : diamonds) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
                break;
            }
            case HEARTS: {
                for (Card card : hearts) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
                break;
            }
            case SPADES: {
                for (Card card : spades) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
                break;
            }
        }
        return null;
    }

    public void removeCard(Card card) {
        switch (card.getSuit()) {
            case CLUBS: {
                if (!clubs.remove(card)) throw new RuntimeException(card + " not found in player hand.");
                break;
            }
            case DIAMONDS: {
                if (!diamonds.remove(card)) throw new RuntimeException(card + " not found in player hand.");
                break;
            }
            case HEARTS: {
                if (!hearts.remove(card)) throw new RuntimeException(card + " not found in player hand.");
                break;
            }
            case SPADES: {
                if (!spades.remove(card)) throw new RuntimeException(card + " not found in player hand.");
                break;
            }
            default: {
                throw new RuntimeException("Unknown suit for card: " + card);
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : clubs) {
            result.append("\n" + card);
        }
        for (Card card : diamonds) {
            result.append("\n" + card);
        }
        for (Card card : hearts) {
            result.append("\n" + card);
        }
        for (Card card : spades) {
            result.append("\n" + card);
        }

        return result.toString();
    }

    public List<Card> getClubs() {
        return clubs;
    }

    public List<Card> getDiamonds() {
        return diamonds;
    }

    public List<Card> getHearts() {
        return hearts;
    }

    public List<Card> getSpades() {
        return spades;
    }
}
