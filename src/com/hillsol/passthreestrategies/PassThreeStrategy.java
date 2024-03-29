package com.hillsol.passthreestrategies;

import com.hillsol.Card;
import com.hillsol.PlayerHand;

import java.util.Set;

public interface PassThreeStrategy {
    // todo: the player may want to change the strategy depending on who the cards are being passed to;
    //  and/or depending on the state of the game
    Set<Card> passThreeCards(PlayerHand playerHand);

}
