package com.hillsol.playhandstrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Suit;

import java.util.List;
import java.util.Set;

public class PlaySemiRandomCard implements PlayHandStrategy {
    /*
       If you got the two of clubs, it must be played.  No exceptions.
       You can only lead a heart if hearts are broken or you only have hearts.
       If a suit has already been determined for this trick, you must follow suit if you can.
       Otherwise you can play whatever you darn well please, including a heart.
     */
    @Override
    public Card playCard(final Set<Card> trick, final Suit receivedLeadingSuit, final PlayerHand playerHand, final boolean heartsAreBroken) {
        // This method will not be called when a player has the two of clubs.
        // It's already been taken care of.

        Card randomCard = null;
        Suit leadingSuit = receivedLeadingSuit;

        if (null == receivedLeadingSuit) {
            // this player must lead a card.  First, choose a suit to lead with:
            List<Suit> heldSuits = playerHand.getCurrentSuits();
            boolean onlyGotsHearts = (heldSuits.size() == 1 && heldSuits.get(0) == Suit.HEARTS);
            if (heartsAreBroken || onlyGotsHearts) {
                // legal to lead a Heart in this case.
            } else {
                heldSuits.remove(Suit.HEARTS);  // don't care if it wasn't there
            }
            leadingSuit = heldSuits.get((int) (Math.random() * heldSuits.size()));
            return playerHand.getLowestCardOfSuit(leadingSuit).get();
        }

        boolean hasLeadingSuit = true;
        Suit suitToSelectFrom = leadingSuit;
        while (randomCard == null) {
            if (!hasLeadingSuit) {
                List<Suit> heldSuits = playerHand.getCurrentSuits();
                suitToSelectFrom = heldSuits.get((int) (Math.random() * heldSuits.size()));
            }
            switch (suitToSelectFrom) {
                case CLUBS: {
                    if (playerHand.getClubs().size() > 0) {
                        return (playerHand.getClubs().get(
                                (int) (Math.random() * playerHand.getClubs().size())));
                    }
                    hasLeadingSuit = false;
                    break;
                }
                case DIAMONDS: {
                    if (playerHand.getDiamonds().size() > 0) {
                        return (playerHand.getDiamonds().get(
                                (int) (Math.random() * playerHand.getDiamonds().size())));
                    }
                    hasLeadingSuit = false;
                    break;
                }
                case HEARTS: {
                    if (playerHand.getHearts().size() > 0) {
                        return (playerHand.getHearts().get(
                                (int) (Math.random() * playerHand.getHearts().size())));
                    }
                    hasLeadingSuit = false;
                    break;
                }
                case SPADES: {
                    if (playerHand.getSpades().size() > 0) {
                        return (playerHand.getSpades().get(
                                (int) (Math.random() * playerHand.getSpades().size())));
                    }
                    hasLeadingSuit = false;
                    break;
                }
            }
        }
        throw new RuntimeException("Didn't find a card to play");
    }
}