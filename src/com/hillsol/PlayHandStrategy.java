package com.hillsol;

public interface PlayHandStrategy {
    public Card playCard(Suit leadingSuit, PlayerHand playerHand, boolean heartsAreBroken);
}
