package edu.noob.blackjackfxproject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class {@code Card} specifies the relevant variables of a card in a deck of
 * cards.
 *
 * @author Anders
 */
public class Card {

    private final StringProperty face = new SimpleStringProperty();
    private final StringProperty suit = new SimpleStringProperty();
    private final IntegerProperty value = new SimpleIntegerProperty();
    private final StringProperty shortValue = new SimpleStringProperty();

    /**
     * A constructor for Card
     *
     * @param face holds the face value of each card
     * @param suit holds the different suits
     * @param value holds the actual value of the card that is used in a game of
     * Black Jack.
     */
    public Card(String face, String suit, int value, String shortValue) {
        this.face.set(face);
        this.suit.set(suit);
        this.value.set(value);
        this.shortValue.set(shortValue);
    }

    /**
     * Returns the face of the card.
     *
     * @return the face of the card
     */
    public String getFace() {
        return face.getValueSafe();
    }
    
    /**
     * Returns the suit of the card.
     *
     * @return the suit of the card
     */
    public String getSuit() {
        return suit.getValueSafe();
    }

    /**
     * Returns the value of the card used in the game.
     *
     * @return the actual value of the card used in the game
     */
    public int getValue() {
        return value.get();
    }

    public String getShortValue() {
        return shortValue.getValueSafe();
    }

    /**
     * Returns the card as "face" of "suit.
     *
     * @return the card as "face" of "suit"
     */
    @Override
    public String toString() {

        return new StringBuilder().append(getFace()).append(" of ")
                .append(getSuit()).toString();
    }
}
