package com.hillsol.playhandstrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Suit;

import java.util.Set;

public interface PlayHandStrategy {
    public Card playCard(Set<Card> trick, Suit leadingSuit, PlayerHand playerHand, boolean heartsAreBroken);
}
