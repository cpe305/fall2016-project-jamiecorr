package edu.noob.blackjackfxproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class {@code Dealer} is used to set, hold and retrieve the values relevant to
 * the player. It implements the {@code Hand} interface.
 *
 * @author Anders
 */
public class Dealer implements Hand {

    private ObservableList<Card> hand = FXCollections.observableArrayList();
    private int valueOfHand;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValueOfHand() {
        return valueOfHand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueOfHand(int valueOfHand) {
        this.valueOfHand = valueOfHand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List getViewOfHand() {
        return Collections.unmodifiableList(hand);
    }

    /**
     * This method will empty the current hand.
     */
    public void clearHand() {
        hand.clear();
    }

    @Override
    public void addOneCardToHand(ObservableList<Card> shoe) {
        hand.add(shoe.get(0));
        shoe.remove(shoe.get(0));
    }
}
