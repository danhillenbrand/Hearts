package com.hillsol.playhandstrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Rank;
import com.hillsol.Suit;

import java.util.*;

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
    public Card playCard(final Set<Card> trick, final Suit receivedLeadingSuit, final PlayerHand playerHand, final boolean heartsAreBroken) {
        Suit leadingSuit = receivedLeadingSuit;
        Card cardToPlay = null;

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

        List<Card> leadingSuitCards = playerHand.getCardsOfSuit(leadingSuit);
        // When you don't have leading suit:
        if (leadingSuitCards.isEmpty()) {
            // if you don't have the Queen of Spades, slough the Ace or King of Spades, if present
            if (!hasQueenOfSpades(playerHand)) {
                cardToPlay = getAceOrKingOfSpades(playerHand);
                if (cardToPlay != null) {
                    return cardToPlay;
                } else {
                    return getHighestCardInHand(playerHand);
                }
            } else {
                return getHighestCardInHand(playerHand);
            }
        } else {    // DO have leading suit

            // find the highest card of suit to play that is lower than the other cards played:
            //  - find the rank of the highest card played in the trick so far;
            Suit finalLeadingSuit = leadingSuit;
            OptionalInt optionalHighestLeadingSuitRankValue = trick.stream()
                    .filter(s -> s.getSuit().equals(finalLeadingSuit))
                    .mapToInt(v -> v.getRank().getRankValue())
                    .max();
            //  - find the highest ranking card in hand of the suit ... that is below the trick rank;
            final byte highestTrickRankValue = (byte) optionalHighestLeadingSuitRankValue.getAsInt();
            OptionalInt ceilingCardRankValue = playerHand.getCardsOfSuit(leadingSuit).stream()
                    .filter(c -> c.getRank().getRankValue() < highestTrickRankValue)
                    .mapToInt(v -> v.getRank().getRankValue())
                    .max();
            if (ceilingCardRankValue.isPresent()) {
                return playerHand.retrieveSpecificCard(leadingSuit, (byte) ceilingCardRankValue.getAsInt());
            } else {  //  Don't have a card in the leading suit that ranks lower than the other cards in the trick
                int lowestCardRankValue = playerHand.getCardsOfSuit(leadingSuit).stream()
                        .mapToInt(v -> v.getRank().getRankValue())
                        .min().orElseThrow(NoSuchElementException::new);
                return playerHand.retrieveSpecificCard(leadingSuit, (byte) lowestCardRankValue);
            }
        }
    }

    public boolean hasQueenOfSpades(final PlayerHand playerHand) {
        for (Card card : playerHand.getSpades()) {
            if (card.getRank() == Rank.QUEEN)
                return true;
        }
        return false;
    }

    public Card getAceOrKingOfSpades(final PlayerHand playerHand) {
        for (Card card : playerHand.getSpades()) {
            // the order they're found won't matter
            if (card.getRank() == Rank.ACE)
                return card;
            if (card.getRank() == Rank.KING)
                return card;
        }
        return null;
    }

    /*
     *
     * Return the highest ranking card in the player's hand.
     * If multiple card have the highest rank, choose in this order:
     * Hearts, Diamonds, Clubs, Spades
     *
     */
    // todo: shouldn't select a Spade unless all other suits are empty.
    // later todo: but only if the Queen of Spades hasn't been played yet.
    public Card getHighestCardInHand(final PlayerHand playerHand) {
        List<Card> highestCardsOfHeldSuits = new ArrayList<>();
        byte highestRankValue = 0;
        if (playerHand.getHearts().size() > 0) {
            byte rankValue = (byte) playerHand.getHearts().stream()
                    .mapToInt(v -> v.getRank().getRankValue())
                    .max().orElseThrow(NoSuchElementException::new);
            highestCardsOfHeldSuits.add(playerHand.retrieveSpecificCard(Suit.HEARTS, rankValue));
            highestRankValue = rankValue;
        }
        if (playerHand.getDiamonds().size() > 0) {
            byte rankValue = (byte) playerHand.getDiamonds().stream()
                    .mapToInt(v -> v.getRank().getRankValue())
                    .max().orElseThrow(NoSuchElementException::new);
            highestCardsOfHeldSuits.add(playerHand.retrieveSpecificCard(Suit.DIAMONDS, rankValue));
            if (rankValue > highestRankValue) highestRankValue = rankValue;
        }
        if (playerHand.getClubs().size() > 0) {
            byte rankValue = (byte) playerHand.getClubs().stream()
                    .mapToInt(v -> v.getRank().getRankValue())
                    .max().orElseThrow(NoSuchElementException::new);
            highestCardsOfHeldSuits.add(playerHand.retrieveSpecificCard(Suit.CLUBS, rankValue));
            if (rankValue > highestRankValue) highestRankValue = rankValue;
        }
        if (playerHand.getSpades().size() > 0) {
            byte rankValue = (byte) playerHand.getSpades().stream()
                    .mapToInt(v -> v.getRank().getRankValue())
                    .max().orElseThrow(NoSuchElementException::new);
            highestCardsOfHeldSuits.add(playerHand.retrieveSpecificCard(Suit.SPADES, rankValue));
            if (rankValue > highestRankValue) highestRankValue = rankValue;
        }
        // return the first card that matches the highest rank
        final byte finalHighestRankValue = highestRankValue;
        return highestCardsOfHeldSuits.stream()
                .filter(c -> c.getRank().getRankValue() == finalHighestRankValue)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}