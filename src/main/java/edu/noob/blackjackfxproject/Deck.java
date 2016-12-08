package edu.noob.blackjackfxproject;

import java.util.Collections;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class {@code Deck} creates a deck of card, shuffles it and adds it to shoe
 * that can hold a number of shuffled decks.
 *
 * @author Anders
 */
public class Deck {

    final ObservableList<Card> deck = FXCollections.observableArrayList();
    private ObservableList<Card> shoe = FXCollections.observableArrayList();

    private final String clubs = "\u2663";
    private final String spades = "\u2660";
    private final String hearts = "\u2661";
    private final String diamonds = "\u2662";

    /**
     * Return the shoe that holds the cards that are used in the game.
     *
     * @return the shoe that holds the cards that are used in the game
     */
    public ObservableList<Card> getShoe() {
        return shoe;
    }

    public Deck() {

        deck.add(new Card("Ace", "Clubs", 11, "A" + clubs));
        deck.add(new Card("Ace", "Spades", 11, "A" + spades));
        deck.add(new Card("Ace", "Hearts", 11, "A" + hearts));
        deck.add(new Card("Ace", "Diamonds", 11, "A" + diamonds));
        deck.add(new Card("King", "Clubs", 10, "K" + clubs));
        deck.add(new Card("King", "Spades", 10, "K" + spades));
        deck.add(new Card("King", "Hearts", 10, "K" + hearts));
        deck.add(new Card("King", "Diamonds", 10, "K" + diamonds));
        deck.add(new Card("Queen", "Clubs", 10, "Q" + clubs));
        deck.add(new Card("Queen", "Spades", 10, "Q" + spades));
        deck.add(new Card("Queen", "Hearts", 10, "Q" + hearts));
        deck.add(new Card("Queen", "Diamonds", 10, "Q" + diamonds));
        deck.add(new Card("Jack", "Clubs", 10, "J" + clubs));
        deck.add(new Card("Jack", "Spades", 10, "J" + spades));
        deck.add(new Card("Jack", "Hearts", 10, "J" + hearts));
        deck.add(new Card("Jack", "Diamonds", 10, "J" + diamonds));
        deck.add(new Card("Ten", "Clubs", 10, "10" + clubs));
        deck.add(new Card("Ten", "Spades", 10, "10" + spades));
        deck.add(new Card("Ten", "Hearts", 10, "10" + hearts));
        deck.add(new Card("Ten", "Diamonds", 10, "10" + diamonds));
        deck.add(new Card("Nine", "Clubs", 9, "9" + clubs));
        deck.add(new Card("Nine", "Spades", 9, "9" + spades));
        deck.add(new Card("Nine", "Hearts", 9, "9" + hearts));
        deck.add(new Card("Nine", "Diamonds", 9, "9" + diamonds));
        deck.add(new Card("Eight", "Clubs", 8, "8" + clubs));
        deck.add(new Card("Eight", "Spades", 8, "8" + spades));
        deck.add(new Card("Eight", "Hearts", 8, "8" + hearts));
        deck.add(new Card("Eight", "Diamonds", 8, "8" + diamonds));
        deck.add(new Card("Seven", "Clubs", 7, "7" + clubs));
        deck.add(new Card("Seven", "Spades", 7, "7" + spades));
        deck.add(new Card("Seven", "Hearts", 7, "7" + hearts));
        deck.add(new Card("Seven", "Diamonds", 7, "7" + diamonds));
        deck.add(new Card("Six", "Clubs", 6, "6" + clubs));
        deck.add(new Card("Six", "Spades", 6, "6" + spades));
        deck.add(new Card("Six", "Hearts", 6, "6" + hearts));
        deck.add(new Card("Six", "Diamonds", 6, "6" + diamonds));
        deck.add(new Card("Five", "Clubs", 5, "5" + clubs));
        deck.add(new Card("Five", "Spades", 5, "5" + spades));
        deck.add(new Card("Five", "Hearts", 5, "5" + hearts));
        deck.add(new Card("Five", "Diamonds", 5, "5" + diamonds));
        deck.add(new Card("Four", "Clubs", 4, "4" + clubs));
        deck.add(new Card("Four", "Spades", 4, "4" + spades));
        deck.add(new Card("Four", "Hearts", 4, "4" + hearts));
        deck.add(new Card("Four", "Diamonds", 4, "4" + diamonds));
        deck.add(new Card("Three", "Clubs", 3, "3" + clubs));
        deck.add(new Card("Three", "Spades", 3, "3" + spades));
        deck.add(new Card("Three", "Hearts", 3, "3" + hearts));
        deck.add(new Card("Three", "Diamonds", 3, "3" + diamonds));
        deck.add(new Card("Two", "Clubs", 2, "2" + clubs));
        deck.add(new Card("Two", "Spades", 2, "2" + spades));
        deck.add(new Card("Two", "Hearts", 2, "2" + hearts));
        deck.add(new Card("Two", "Diamonds", 2, "2" + diamonds));
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    /**
     * Adds the deck of cards to the shoe.
     */
    public void addDeckToShoe() {
        for (int i = 0; i < deck.size(); ++i) {
            shoe.add(deck.get(i));
        }
    }
}
