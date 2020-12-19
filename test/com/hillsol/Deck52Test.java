package com.hillsol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Deck52Test {

    @Test
    public void createDeckTest() {
        Deck52 deck = new Deck52();
        System.out.println(deck);
        String result = "2 of Hearts"
                + "\n3 of Hearts"
                + "\n4 of Hearts"
                + "\n5 of Hearts"
                + "\n6 of Hearts"
                + "\n7 of Hearts"
                + "\n8 of Hearts"
                + "\n9 of Hearts"
                + "\n10 of Hearts"
                + "\nJack of Hearts"
                + "\nQueen of Hearts"
                + "\nKing of Hearts"
                + "\nAce of Hearts"
                + "\n2 of Clubs"
                + "\n3 of Clubs"
                + "\n4 of Clubs"
                + "\n5 of Clubs"
                + "\n6 of Clubs"
                + "\n7 of Clubs"
                + "\n8 of Clubs"
                + "\n9 of Clubs"
                + "\n10 of Clubs"
                + "\nJack of Clubs"
                + "\nQueen of Clubs"
                + "\nKing of Clubs"
                + "\nAce of Clubs"
                + "\n2 of Diamonds"
                + "\n3 of Diamonds"
                + "\n4 of Diamonds"
                + "\n5 of Diamonds"
                + "\n6 of Diamonds"
                + "\n7 of Diamonds"
                + "\n8 of Diamonds"
                + "\n9 of Diamonds"
                + "\n10 of Diamonds"
                + "\nJack of Diamonds"
                + "\nQueen of Diamonds"
                + "\nKing of Diamonds"
                + "\nAce of Diamonds"
                + "\n2 of Spades"
                + "\n3 of Spades"
                + "\n4 of Spades"
                + "\n5 of Spades"
                + "\n6 of Spades"
                + "\n7 of Spades"
                + "\n8 of Spades"
                + "\n9 of Spades"
                + "\n10 of Spades"
                + "\nJack of Spades"
                + "\nQueen of Spades"
                + "\nKing of Spades"
                + "\nAce of Spades"
                + "\n";
        assertEquals(deck.toString(),result);
        deck.shuffle();
    }

}