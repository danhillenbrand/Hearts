package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Rank;

import java.util.HashSet;
import java.util.Set;

public class PassLowest implements PassThreeStrategy {
    @Override
    public Set<Card> passThreeCards(final PlayerHand playerHand) {
        Set<Card> threeCards = new HashSet<>();
        Set<Card> fullHand = new HashSet<>();

        fullHand.addAll(playerHand.getHearts());
        fullHand.addAll(playerHand.getSpades());
        fullHand.addAll(playerHand.getClubs());
        fullHand.addAll(playerHand.getDiamonds());

        for (int rank = Rank.LOWEST_RANK_VALUE; rank <= Rank.HIGHEST_RANK_VALUE; rank++) {
            for (Card card : fullHand) {
                if (rank == card.getRank().getRankValue()) {
                    if (threeCards.add(card)) {
                        playerHand.removeCard(card);
                        if (threeCards.size() == 3)
                            return threeCards;
                    } else {
                        throw new RuntimeException("Duplicate Card Exception");
                    }
                }
            }
        }
        throw new RuntimeException("Execute Programmer; should never get to this code");
    }
}
