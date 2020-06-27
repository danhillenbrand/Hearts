package com.hillsol.playhandstrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Suit;

import java.util.List;
import java.util.NoSuchElementException;

public class PlayHighestCardAlways implements PlayHandStrategy {

    @Override
    public Card playCard(Suit leadingSuit, PlayerHand playerHand, boolean heartsAreBroken) {
        // This method will not be called when a player has the two of clubs.
        // It's already been taken care of.

        Card cardToPlay = null;
        if (null == leadingSuit) {
            // this player must lead a suit.  Can't lead hearts unless broken or only gots hearts.
            List<Suit> heldSuits = playerHand.getCurrentSuits();
            boolean onlygotsHearts = (heldSuits.size() == 1 && heldSuits.get(0) == Suit.HEARTS);
            if (!heartsAreBroken && !onlygotsHearts) {
                heldSuits.remove(Suit.HEARTS);  // don't care if it wasn't there
            }
            leadingSuit = heldSuits.get((int) (Math.random() * heldSuits.size()));
        }

        boolean hasLeadingSuit = true;
        Suit suitToSelectFrom = leadingSuit;
        while (cardToPlay == null) {
            if (!hasLeadingSuit) {
                List<Suit> heldSuits = playerHand.getCurrentSuits();
                suitToSelectFrom = heldSuits.get((int) (Math.random() * heldSuits.size()));
            }
            switch (suitToSelectFrom) {
                case CLUBS: {
                    if (playerHand.getClubs().size() > 0) {
                        // todo: add proper comparator to Card; make this work to return Card, not rank
                        byte rankValue = (byte) playerHand.getClubs().stream()
                                .mapToInt(v -> v.getRank().getRankValue())
                                .max().orElseThrow(NoSuchElementException::new);
                        cardToPlay = playerHand.retrieveSpecificCard(Suit.CLUBS, rankValue);
                        if (null == cardToPlay) {
                            throw new RuntimeException("Couldn't find a CLUB to play.  Even after verifying one exists.");
                        } else {
                            return cardToPlay;
                        }
                    }
                    hasLeadingSuit = false;
                    break;
                }
                case DIAMONDS: {
                    if (playerHand.getDiamonds().size() > 0) {
                        byte rankValue = (byte) playerHand.getDiamonds().stream()
                                .mapToInt(v -> v.getRank().getRankValue())
                                .max().orElseThrow(NoSuchElementException::new);
                        cardToPlay = playerHand.retrieveSpecificCard(Suit.DIAMONDS, rankValue);
                        if (null == cardToPlay) {
                            throw new RuntimeException("Couldn't find a DIAMOND to play.  Even after verifying one exists.");
                        } else {
                            return cardToPlay;
                        }
                    }
                    hasLeadingSuit = false;
                    break;
                }
                case HEARTS: {
                    if (playerHand.getHearts().size() > 0) {
                        byte rankValue = (byte) playerHand.getHearts().stream()
                                .mapToInt(v -> v.getRank().getRankValue())
                                .max().orElseThrow(NoSuchElementException::new);
                        cardToPlay = playerHand.retrieveSpecificCard(Suit.HEARTS, rankValue);
                        if (null == cardToPlay) {
                            throw new RuntimeException("Couldn't find a HEART to play.  Even after verifying one exists.");
                        } else {
                            return cardToPlay;
                        }
                    }
                    hasLeadingSuit = false;
                    break;
                }
                case SPADES: {
                    if (playerHand.getSpades().size() > 0) {
                        byte rankValue = (byte) playerHand.getSpades().stream()
                                .mapToInt(v -> v.getRank().getRankValue())
                                .max().orElseThrow(NoSuchElementException::new);
                        cardToPlay = playerHand.retrieveSpecificCard(Suit.SPADES, rankValue);
                        if (null == cardToPlay) {
                            throw new RuntimeException("Couldn't find a SPADE to play.  Even after verifying one exists.");
                        } else {
                            return cardToPlay;
                        }
                    }
                    hasLeadingSuit = false;
                    break;
                }
            }
        }
        throw new RuntimeException("Didn't find a card to play");
    }
}