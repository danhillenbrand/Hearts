package com.hillsol.playhandstrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Suit;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/*
 *  If this is the leading player of the trick, just pick a card for now. Otherwise:
 *
 *  Play the highest card of the suit that isn't higher than the other cards played.
 *  When only higher cards of the suit is available, play the lowest of the those cards.
 *  Otherwise (no cards of suit), play the Ace or King of spades if you don't have the Queen.
 *  Otherwise, play the highest of any other suit: if tie between suits, go with Hearts first,
 *  then Diamonds, then Clubs.
 *
 */
public class PlayCeilingCard implements PlayHandStrategy {

    @Override
    public Card playCard(Set<Card> trick, Suit leadingSuit, PlayerHand playerHand, boolean heartsAreBroken) {

        Card cardToPlay = null;
        if (null == leadingSuit) {
            // this player must lead a suit.  Can't lead hearts unless broken or only gots hearts.
            List<Suit> heldSuits = playerHand.getCurrentSuits();
            boolean onlyGotsHearts = (heldSuits.size() == 1 && heldSuits.get(0) == Suit.HEARTS);
            if (!heartsAreBroken && !onlyGotsHearts) {
                heldSuits.remove(Suit.HEARTS);  // don't care if it wasn't there
            }
            leadingSuit = heldSuits.get((int) (Math.random() * heldSuits.size()));
        }

        List<Card> leadingSuitCards = playerHand.getCardsOfSuit(leadingSuit);
        if (leadingSuitCards == null){
            // don't have the leading suit
        } else {
            // find the highest card of suit to play that is lower than the other cards played
        }


//            throw new RuntimeException("PlayCeilingCard Didn't find a card to play");
//        todo: actually code this method
        PlayHandStrategy playRandomCard = new PlaySemiRandomCard();
        return playRandomCard.playCard(trick, leadingSuit, playerHand, heartsAreBroken);
    }
}