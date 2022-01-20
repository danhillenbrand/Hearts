package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.exceptions.DuplicateCardException;

import java.util.HashSet;
import java.util.Set;

public class PassRandom implements PassThreeStrategy {
    @Override
    public Set<Card> passThreeCards(final PlayerHand playerHand) {
        var threeCards  = new HashSet<Card>();
        for (int i = 0; i<3;i++) {
            Card card = playerHand.getRandomCard(true);
            if (threeCards.add(card)){
                playerHand.removeCard(card);
            } else {
                throw new DuplicateCardException(card + "\nCards Passed:" + threeCards);
            }
        }
        return threeCards;
    }
}
