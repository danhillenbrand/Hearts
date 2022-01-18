package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PassHearts implements PassThreeStrategy {
    /*
     * Select three highest hearts, if present.
     * Then select queen, ace, king of spades, if present.
     * Then clubs or diamonds, if they can be made void.
     * Clubs checked before diamonds since play starts with clubs.
     * Otherwise, select highest cards.
     */
    @Override
    public Set<Card> passThreeCards(final PlayerHand playerHand) {
        var threeCards = new HashSet<Card>();
        var cardsOfASuit = new HashSet<Card>();

        // need separate Set to avoid ConcurrentModificationException when trying to remove cards in loop
        cardsOfASuit.addAll(playerHand.getHearts());

        for (int rank = 13; rank > 0; rank--) {
            for (Card card : cardsOfASuit) {
                if (rank == card.getRank().getRankValue()) {
                    if (threeCards.add(card)) {
                        playerHand.removeCard(card);
                        if (threeCards.size() == 3) return threeCards;
                    } else {
                        throw new RuntimeException("Duplicate Card Exception");
                    }
                }
            }
        }

        // remove the queen of spades next, if present
        cardsOfASuit.clear();
        cardsOfASuit.addAll(playerHand.getSpades());
        Optional<Card> queenOfSpades = cardsOfASuit.stream()
                .filter(card -> card.getRank().getRankValue() == 11)
                .findFirst();
        if (queenOfSpades.isPresent()) {
            if (threeCards.add(queenOfSpades.get())) {
                playerHand.removeCard(queenOfSpades.get());
                if (threeCards.size() == 3) return threeCards;
            } else {
                throw new RuntimeException("Duplicate Card Exception");
            }
        }

        // remove the Ace & King, if present
        // todo: check for the ace first
        for (Card card : cardsOfASuit) {
            if (card.getRank().getRankValue() >= 12) {
                if (threeCards.add(card)) {
                    playerHand.removeCard(card);
                    if (threeCards.size() == 3) return threeCards;
                } else {
                    throw new RuntimeException("Duplicate Card Exception");
                }
            }
        }

        cardsOfASuit.clear();
        cardsOfASuit.addAll(playerHand.getClubs());
        if (cardsOfASuit.size() <= threeCards.size()) {
            for (Card card : cardsOfASuit) {
                if (threeCards.add(card)) {
                    playerHand.removeCard(card);
                    if (threeCards.size() == 3) return threeCards;
                } else {
                    throw new RuntimeException("Duplicate Card Exception");
                }
            }
        }

        cardsOfASuit.clear();
        cardsOfASuit.addAll(playerHand.getDiamonds());
        if (playerHand.getDiamonds().size() <= threeCards.size()) {
            for (Card card : cardsOfASuit) {
                if (threeCards.add(card)) {
                    playerHand.removeCard(card);
                    if (threeCards.size() == 3) return threeCards;
                } else {
                    throw new RuntimeException("Duplicate Card Exception");
                }
            }
        }

        Set<Card> fullHand = new HashSet<>();
        fullHand.addAll(playerHand.getSpades());
        fullHand.addAll(playerHand.getClubs());
        fullHand.addAll(playerHand.getDiamonds());
        fullHand.addAll(playerHand.getHearts());

        for (int rank = 13; rank > 0; rank--) {
            for (Card card : fullHand) {
                if (rank == card.getRank().getRankValue()) {
                    if (threeCards.add(card)) {
                        playerHand.removeCard(card);
                        if (threeCards.size() == 3) return threeCards;
                    } else {
                        throw new RuntimeException("Duplicate Card Exception");
                    }
                }
            }
        }
        throw new RuntimeException("Execute Programmer; should never get to this code");
    }
}
