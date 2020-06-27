package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;

import java.util.HashSet;
import java.util.Set;

public class PassThreeHighest implements PassThreeStrategy {
    @Override
    public Set<Card> passThreeCards(PlayerHand playerHand) {
        Set<Card> threeCards = new HashSet<>();
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

        return null; // should never get here
    }
}
