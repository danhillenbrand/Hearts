package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.passthreestrategies.PassThreeStrategy;

import java.util.HashSet;
import java.util.Set;

public class PassSpades implements PassThreeStrategy {
    /*
    *  Select queen, ace, king of spades first, if present.
    * Then clubs or diamonds, if they can be made void.
    * Otherwise, select highest cards
     */
    @Override
    public Set<Card> passThreeCards(PlayerHand playerHand) {
        Set<Card> threeCards = new HashSet<>();
        Set<Card> fullHand = new HashSet<>();
        Set<Card> cardsOfASuit = new HashSet<>();

        // need separate Set to avoid ConcurrentModificationException when trying to remove cards in loop
        cardsOfASuit.addAll(playerHand.getSpades());

        for (Card card : cardsOfASuit){
            if (card.getRank().getRankValue() >= 11){
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
        if (cardsOfASuit.size()<=threeCards.size()){
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
        if (playerHand.getDiamonds().size()<=threeCards.size()){
            for (Card card : cardsOfASuit) {
                if (threeCards.add(card)) {
                    playerHand.removeCard(card);
                    if (threeCards.size() == 3) return threeCards;
                } else {
                    throw new RuntimeException("Duplicate Card Exception");
                }
            }
        }


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
