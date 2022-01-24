package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Rank;
import com.hillsol.exceptions.DuplicateCardException;

import java.util.HashSet;
import java.util.Set;

public class PassLowest implements PassThreeStrategy {
    // todo: randomize which cards are selected.  Currently, if player has all the deuces, the Two of Diamonds is never selected.
    @Override
    public Set<Card> passThreeCards(final PlayerHand playerHand) {
        var threeCards = new HashSet<Card>();
        var fullHand = new HashSet<Card>();

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
                        throw new DuplicateCardException(card + "\nCards Passed:" + threeCards);
                    }
                }
            }
        }
        throw new RuntimeException("Execute Programmer; should never get to this code");
    }
}
