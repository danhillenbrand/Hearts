package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.exceptions.DuplicateCardException;
import com.hillsol.passthreestrategies.PassThreeStrategy;

import java.util.HashSet;
import java.util.Set;

public class PassSpades implements PassThreeStrategy {
    /*
    * Select queen, ace, king of spades first, if present.
    * Then clubs or diamonds, if they can be made void.
    * Clubs checked first since play starts with clubs.
    * Otherwise, select highest cards
     */
    @Override
    public Set<Card> passThreeCards(final PlayerHand playerHand) {
        var threeCards = new HashSet<Card>();
        var cardsOfASuit = new HashSet<Card>();

        // need separate Set to avoid ConcurrentModificationException when trying to remove cards in loop
        cardsOfASuit.addAll(playerHand.getSpades());

        for (Card card : cardsOfASuit){
            if (card.getRank().getRankValue() >= 11){
                if (threeCards.add(card)) {
                    playerHand.removeCard(card);
                    if (threeCards.size() == 3) return threeCards;
                } else {
                    throw new DuplicateCardException(card + "\nCards Passed:" + threeCards);
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
                    throw new DuplicateCardException(card + "\nCards Passed:" + threeCards);
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
                    throw new DuplicateCardException(card + "\nCards Passed:" + threeCards);
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
                        throw new DuplicateCardException(card + "\nCards Passed:" + threeCards);
                    }
                }
            }
        }
        throw new RuntimeException("Execute Programmer; should never get to this code");
    }
}
