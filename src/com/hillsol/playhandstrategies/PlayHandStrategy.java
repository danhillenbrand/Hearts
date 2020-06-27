package com.hillsol.playhandstrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Suit;

public interface PlayHandStrategy {
    public Card playCard(Suit leadingSuit, PlayerHand playerHand, boolean heartsAreBroken);
}
