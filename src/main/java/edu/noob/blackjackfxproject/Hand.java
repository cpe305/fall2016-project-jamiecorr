package edu.noob.blackjackfxproject;

import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Anders
 */
public interface Hand {

    /**
     * Returns an unmodifiable view of the cards in this hand.
     *
     * @return an unmodifiable view of the cards in this hand
     */
    List<Card> getViewOfHand();

    /**
     * Sets the value of the hand.
     *
     * @param valueOfHand holds the value that will be set to the value of the
     * hand.
     */
    void setValueOfHand(int valueOfHand);

    /**
     * Returns the value of the hand.
     *
     * @return the value of the hand
     */
    int getValueOfHand();

    void addOneCardToHand(ObservableList<Card> shoe);
}
