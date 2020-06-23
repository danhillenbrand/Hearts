package com.hillsol;

import java.util.HashSet;
import java.util.Set;

public class PassThreeHighest implements PassThreeStrategy {
    @Override
    public Set<Card> passThreeCards(PlayerHand playerHand) {
        Set<Card> threeCards = new HashSet<Card>();
        Set<Card> fullHand = new HashSet<>();

        for (Card card : playerHand.getClubs()) {
            fullHand.add(card);
        }
        for (Card card : playerHand.getDiamonds()) {
            fullHand.add(card);
        }
        for (Card card : playerHand.getHearts()) {
            fullHand.add(card);
        }
        for (Card card : playerHand.getSpades()) {
            fullHand.add(card);
        }

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

        return null; // should never get here
    }
}
