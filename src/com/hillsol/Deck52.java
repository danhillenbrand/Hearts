package com.hillsol;

public final class Deck52 {
    private final Card[] deck = new Card[52];
    private Card twoOfClubs;

    public Deck52() {
        for (int i = 0; i < Suit.values().length; i++) {
            for (int j = 0; j < Rank.values().length; j++) {
                int cardIndex = (i * Rank.values().length) + j;
                deck[cardIndex] = new Card(Rank.values()[j], Suit.values()[i]);
                if (twoOfClubs != null) {
                    if ((Rank.values()[j] == Rank.TWO) && (Suit.values()[i] == Suit.CLUBS)) {
                        twoOfClubs = deck[cardIndex];
                    }
                }
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < deck.length; i++) {
            int j = (int) (Math.random() * deck.length);
            Card card = deck[i];
            deck[i] = deck[j];
            deck[j] = card;
        }
    }

    public Card[] getDeck() {
        return deck;
    }

    public Card getTwoOfClubs() {
        return twoOfClubs;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (Card card : deck
        ) {
            result.append(card + "\n");
        }
        return result.toString();
    }
}
