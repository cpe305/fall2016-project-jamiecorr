package edu.noob.blackjackfxproject;

import java.util.Collections;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class {@code Player} is used to set, hold and retrieve the values relevant to
 * the player. It implements the {@code Hand} interface.
 *
 * @author Anders
 */
public class Player implements Hand {

    private ObservableList<Card> hand = FXCollections.observableArrayList();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty sumOfChips = new SimpleIntegerProperty(1500);
    private final IntegerProperty totalBuyIn = new SimpleIntegerProperty();
    private final IntegerProperty valueOfHand = new SimpleIntegerProperty();
    private final IntegerProperty roundBet = new SimpleIntegerProperty();

    /**
     * {@inheritDoc}
     */
    @Override
    public List getViewOfHand() {
        return Collections.unmodifiableList(hand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueOfHand(int valueOfHand) {
        this.valueOfHand.set(valueOfHand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValueOfHand() {
        return valueOfHand.get();
    }

    @Override
    public void addOneCardToHand(ObservableList<Card> shoe) {
        Card card = shoe.get(0);
        hand.add(card);
//                add(shoe.get(0));
        shoe.remove(shoe.get(0));
    }

    /**
     * This method clears the hand of all the cards.
     */
    public void clearHand() {
        hand.clear();
    }

    /**
     * Returns the value of the roundBet.
     *
     * @return the value of the roundBet
     */
    public int getRoundBet() {
        return roundBet.get();
    }

    /**
     * Sets the value of roundBet.
     *
     * @param roundBet is the value that will be set.
     */
    public void setRoundBet(int roundBet) {
        this.roundBet.set(roundBet);
    }

    /**
     * Sets the value of sumOfChips.
     *
     * @param sumOfChips is the value that will be set.
     */
    public void addChips(int sumOfChips) {
        this.sumOfChips.set(this.sumOfChips.get() + sumOfChips);
    }
    
    /**
     * Returns the value of sumOfChips.
     *
     * @return the value of sumOfChips
     */
    public int getSumOfChips() {
        return sumOfChips.get();
    }
}