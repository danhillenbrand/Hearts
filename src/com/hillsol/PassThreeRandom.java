package com.hillsol;

import java.util.HashSet;
import java.util.Set;

public class PassThreeRandom implements  PassThreeStrategy{
    @Override
    public Set<Card> passThreeCards(PlayerHand playerHand) {
        Set<Card> threeCards  = new HashSet<Card>();
        for (int i = 0; i<3;i++) {
            Card chosenCard = playerHand.getRandomCard(true);
            if (threeCards.add(chosenCard)){
                playerHand.removeCard(chosenCard);
            } else {
                throw new RuntimeException("Duplicate Card Exception");
            }
        }
        return threeCards;
    }
}