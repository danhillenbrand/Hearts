package com.hillsol.playhandstrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;
import com.hillsol.Suit;

import java.util.Set;

public interface PlayHandStrategy {
    /*
       This method will not be called when a player has the two of clubs.
       It's already been taken care of.

       You can only lead a heart if hearts are broken, or you only have hearts.
       If a suit has already been determined for this trick, you must follow suit if you can.
       Otherwise, you can play whatever you darn well please, including a heart.
     */
    Card playCard(Set<Card> trick, Suit leadingSuit, PlayerHand playerHand, boolean heartsAreBroken);
}
