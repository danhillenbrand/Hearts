package com.hillsol;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;

import static com.hillsol.Suit.*;

public class PlayerHand {
    final private List<Card> clubs = new ArrayList<>();
    final private List<Card> diamonds = new ArrayList<>();
    final private List<Card> hearts = new ArrayList<>();
    final private List<Card> spades = new ArrayList<>();

    public void receiveCard(final Card dealtCard) {
        switch (dealtCard.getSuit()) {
            // todo: candidate for a pattern here, or maybe just better structure:
            // todo also: Why did I make these ArrayLists instead of a Set or something?
            case CLUBS -> {
                if (clubs.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal the " + dealtCard + ", which has already been dealt.");
                }
                clubs.add(dealtCard);
            }
            case DIAMONDS -> {
                if (diamonds.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal the " + dealtCard + ", which has already been dealt.");
                }
                diamonds.add(dealtCard);
            }
            case HEARTS -> {
                if (hearts.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal " + dealtCard + ", which has already been dealt.");
                }
                hearts.add(dealtCard);
            }
            case SPADES -> {
                if (spades.contains(dealtCard)) {
                    throw new RuntimeException("Attempted to deal " + dealtCard + ", which has already been dealt.");
                }
                spades.add(dealtCard);
            }
            default -> throw new RuntimeException("Unknown suit for card: " + dealtCard);
        }
    }

    public Card getTwoOfClubs() {
        for (Card card : clubs) {
            if (card.getRank() == Rank.TWO)
                return card;
        }
        return null;
    }

    public Card getRandomCard(final boolean canChooseHearts) {
        while (true) {
            switch (Suit.getRandomSuit()) {
                case CLUBS -> {
                    if (clubs.size() > 0)
                        return (clubs.get((ThreadLocalRandom.current().nextInt(0, clubs.size()))));
                }
                case DIAMONDS -> {
                    if (diamonds.size() > 0)
                        return (diamonds.get((ThreadLocalRandom.current().nextInt(0, diamonds.size()))));
                }
                case HEARTS -> {
                    if (!canChooseHearts) {
                        if (clubs.size() != 0 || diamonds.size() != 0 || spades.size() != 0) {
                            break;
                        } else {
                            // todo:  when brainpower returns, check what's going on here:
//                            canChooseHearts = true;
                        }
                    }
                    if (hearts.size() > 0)
                        return (hearts.get((ThreadLocalRandom.current().nextInt(0, hearts.size()))));
                }
                case SPADES -> {
                    if (spades.size() > 0)
                        return (spades.get((ThreadLocalRandom.current().nextInt(0, spades.size()))));
                }
            }
        }
    }

    public List <Suit> getCurrentSuits(){
        List<Suit> currentSuits = new ArrayList<>();
        if (clubs.size() > 0) currentSuits.add(CLUBS);
        if (diamonds.size() > 0) currentSuits.add(DIAMONDS);
        if (hearts.size() > 0) currentSuits.add(HEARTS);
        if (spades.size() > 0) currentSuits.add(SPADES);
        return currentSuits;
    }

    public Card retrieveSpecificCard(final Suit suit, final byte rankValue){
        switch (suit) {
            case CLUBS -> {
                for (Card card : clubs) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
            }
            case DIAMONDS -> {
                for (Card card : diamonds) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
            }
            case HEARTS -> {
                for (Card card : hearts) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
            }
            case SPADES -> {
                for (Card card : spades) {
                    if (card.getRank().getRankValue() == rankValue)
                        return card;
                }
            }
        }
        return null;
    }

    public void removeCard(final Card card) {
        switch (card.getSuit()) {
            case CLUBS -> {
                if (!clubs.remove(card)) throw new RuntimeException(card + " not found in player hand.");
            }
            case DIAMONDS -> {
                if (!diamonds.remove(card)) throw new RuntimeException(card + " not found in player hand.");
            }
            case HEARTS -> {
                if (!hearts.remove(card)) throw new RuntimeException(card + " not found in player hand.");
            }
            case SPADES -> {
                if (!spades.remove(card)) throw new RuntimeException(card + " not found in player hand.");
            }
            default -> throw new RuntimeException("Unknown suit for card: " + card);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : clubs) {
            result.append("\n").append(card);
        }
        for (Card card : diamonds) {
            result.append("\n").append(card);
        }
        for (Card card : hearts) {
            result.append("\n").append(card);
        }
        for (Card card : spades) {
            result.append("\n").append(card);
        }

        return result.toString();
    }

    public boolean hasNoCards(){
        return clubs.isEmpty() && diamonds.isEmpty() && spades.isEmpty() && hearts.isEmpty();
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

    public List<Card> getCardsOfSuit(final Suit suit){
        switch (suit){
            case CLUBS: return clubs;
            case HEARTS: return hearts;
            case SPADES: return spades;
            case DIAMONDS: return diamonds;
            default: {
                throw new RuntimeException("Didn't find Suit requested:  " + suit.getDisplayValue());
            }
        }
    }

    public Optional<Card> getLowestCardOfSuit(final Suit suit){
        Optional <Card> lowestRankingCard = Optional.empty();
        OptionalInt lowestRankValue = getCardsOfSuit(suit).stream()
                .mapToInt(v -> v.getRank().getRankValue())
                .min();
        if (lowestRankValue.isPresent()){
            lowestRankingCard = Optional.of(retrieveSpecificCard(suit, (byte) lowestRankValue.getAsInt()));
        }
        return lowestRankingCard;
    }

    public boolean hasQueenOfSpades() {
        for (Card card : spades) {
            if (card.getRank() == Rank.QUEEN)
                return true;
        }
        return false;
    }
}
