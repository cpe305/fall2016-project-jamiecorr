package edu.noob.blackjackfxproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Anders
 */
public class Controller implements Initializable {

  private final Player player;
  private final Dealer dealer;
  private final Deck deck;

  private final List<Card> playersHand;
  private final List<Card> dealersHand;

  public Controller(Player player, Dealer dealer, Deck deck, List<Card> playersHand, List<Card> dealersHand) {
    this.player = player;
    this.dealer = dealer;
    this.deck = deck;
    this.playersHand = playersHand;
    this.dealersHand = dealersHand;
  }

  @FXML
  private TextField betTextField;

  @FXML
  private Button betButton, dealButton, hitButton, stayButton, newRoundButton, newGameButton, quitButton, exitButton;

  @FXML
  private Label playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6, playerCard7, playerCard8,
      playerCard9, playerCard10;

  @FXML
  private Label dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7, dealerCard8,
      dealerCard9, dealerCard10;

  @FXML
  private StackPane stackPlayerCard1, stackPlayerCard2, stackPlayerCard3, stackPlayerCard4, stackPlayerCard5,
      stackPlayerCard6, stackPlayerCard7, stackPlayerCard8, stackPlayerCard9, stackPlayerCard10;

  @FXML
  private StackPane stackDealerCard1, stackDealerCard2, stackDealerCard3, stackDealerCard4, stackDealerCard5,
      stackDealerCard6, stackDealerCard7, stackDealerCard8, stackDealerCard9, stackDealerCard10;

  @FXML
  private Rectangle dealerHiddenCard;

  @FXML
  private Label result, chipsDisplay;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    startSequence();

    betButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        try {
          int newBet = Integer.parseInt(betTextField.getText().trim());
          if (newBet >= 5 && newBet <= 500) {
            player.setRoundBet(newBet);
            player.addChips(-newBet);
            betTextField.setDisable(true);
            betButton.setDisable(true);
            dealButton.setDisable(false);
            chipsDisplay.setText("Sum: " + player.getSumOfChips());
          } else {
            betTextField.setStyle("-fx-border-color: red;");
          }

        } catch (NumberFormatException e) {
          betTextField.setStyle("-fx-border-color: red;");
        }
        result.setText("Player bets " + player.getRoundBet());
      }
    });

    dealButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        result.setText("Dealer starts dealing!");
        dealButton.setDisable(true);

        while (dealer.getViewOfHand().size() < 2) {
          player.addOneCardToHand(deck.getShoe());
          dealer.addOneCardToHand(deck.getShoe());
        }
        stackPlayerCard1.setVisible(true);
        playerCard1.setText(playersHand.get(0).getShortValue());
        setSuitColor(player, playerCard1, 0);
        stackDealerCard1.setVisible(true);

        dealerCard1.setText(dealersHand.get(0).getShortValue());
        setSuitColor(dealer, dealerCard1, 0);

        playerCard2.setText(playersHand.get(1).getShortValue());
        stackPlayerCard2.setVisible(true);
        setSuitColor(player, playerCard2, 1);

        stackDealerCard2.setVisible(true);
        dealerCard2.setText(dealersHand.get(1).getShortValue());
        setSuitColor(dealer, dealerCard2, 1);

        dealerHiddenCard.setVisible(true);
        setValueOfCurrentHand(player);
        setValueOfCurrentHand(dealer);
        result.setText("Players hand has a value of " + player.getValueOfHand());

        hitButton.setDisable(false);
        stayButton.setDisable(false);
      }
    });

    hitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        player.addOneCardToHand(deck.getShoe());
        int currentIndex = playersHand.size() - 1;
        Label[] playerLabel = { playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6,
            playerCard7, playerCard8, playerCard9, playerCard10 };
        StackPane[] playerStack = { stackPlayerCard1, stackPlayerCard2, stackPlayerCard3, stackPlayerCard4,
            stackPlayerCard5, stackPlayerCard6, stackPlayerCard7, stackPlayerCard8, stackPlayerCard9,
            stackPlayerCard10 };
        Label currentPlayerLabel = playerLabel[currentIndex];
        StackPane currentPlayerStack = playerStack[currentIndex];

        currentPlayerLabel.setText(playersHand.get(currentIndex).getShortValue());
        result.setText("Player gets " + playersHand.get(currentIndex).toString());
        setSuitColor(player, currentPlayerLabel, currentIndex);

        currentPlayerStack.setVisible(true);
        setValueOfCurrentHand(player);
        if (player.getValueOfHand() > 21) {
          result.setText("Player hand: " + player.getValueOfHand() + ". Player is bust! The house wins!");
          newRoundButton.setDisable(false);
          dealerHiddenCard.setVisible(false);
          hitButton.setDisable(true);
          stayButton.setDisable(true);
          checkIfGameOver();
        } else {
          result.setText("Players hand has a value of " + player.getValueOfHand());
        }
      }
    });

    stayButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        hitButton.setDisable(true);
        dealerHiddenCard.setVisible(false);
        Label[] dealerLabel = { dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6,
            dealerCard7, dealerCard8, dealerCard9, dealerCard10 };
        StackPane[] dealerStack = { stackDealerCard1, stackDealerCard2, stackDealerCard3, stackDealerCard4,
            stackDealerCard5, stackDealerCard6, stackDealerCard7, stackDealerCard8, stackDealerCard9,
            stackDealerCard10 };

        while (dealer.getValueOfHand() < 17) {
          dealer.addOneCardToHand(deck.getShoe());
          setValueOfCurrentHand(dealer);
        }

        for (int i = 0; i < dealersHand.size(); ++i) {
          Label currentDealerLabel = dealerLabel[i];
          StackPane currentDealerStack = dealerStack[i];
          currentDealerStack.setVisible(true);
          currentDealerLabel.setText(dealersHand.get(i).getShortValue());
          currentDealerStack.setVisible(true);
          setSuitColor(dealer, currentDealerLabel, i);
        }
        evaluateRoundAndPrintResult();
        stayButton.setDisable(true);

        checkIfGameOver();
      }
    });

    newRoundButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        startSequence();
      }
    });

    newGameButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        player.addChips(1500 - player.getSumOfChips());
        startSequence();
      }
    });

    quitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        exitButton.setVisible(true);
        betTextField.setDisable(true);
        betButton.setDisable(true);
        dealButton.setDisable(true);
        hitButton.setDisable(true);
        stayButton.setDisable(true);
        newRoundButton.setDisable(true);
        newGameButton.setVisible(true);
      }
    });

    exitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Platform.exit();
      }
    });
  }

  private void resetTable() {
    player.clearHand();
    dealer.clearHand();
    player.setRoundBet(0);
    betTextField.setText("");

    Label[] dealerLabel = { dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7,
        dealerCard8, dealerCard9, dealerCard10 };
    StackPane[] dealerStack = { stackDealerCard1, stackDealerCard2, stackDealerCard3, stackDealerCard4,
        stackDealerCard5, stackDealerCard6, stackDealerCard7, stackDealerCard8, stackDealerCard9, stackDealerCard10 };
    Label[] playerLabel = { playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6, playerCard7,
        playerCard8, playerCard9, playerCard10 };
    StackPane[] playerStack = { stackPlayerCard1, stackPlayerCard2, stackPlayerCard3, stackPlayerCard4,
        stackPlayerCard5, stackPlayerCard6, stackPlayerCard7, stackPlayerCard8, stackPlayerCard9, stackPlayerCard10 };

    for (Label pLabels : playerLabel) {
      pLabels.setText("");
      pLabels.setTextFill(Color.BLACK);
    }
    for (StackPane pStack : playerStack) {
      pStack.setVisible(false);
    }
    for (Label dLabels : dealerLabel) {
      dLabels.setText("");
      dLabels.setTextFill(Color.BLACK);
    }
    for (StackPane dStack : dealerStack) {
      dStack.setVisible(false);
    }
  }

  private void startSequence() {

    result.setText("");
    betTextField.setStyle("-fx-border-color: black;");
    quitButton.setVisible(true);
    exitButton.setVisible(false);
    newRoundButton.setDisable(true);
    chipsDisplay.setText("Sum: " + player.getSumOfChips());
    player.setRoundBet(0);
    dealerHiddenCard.setVisible(false);
    betTextField.setFocusTraversable(true);
    betTextField.requestFocus();
    betTextField.setDisable(false);
    betButton.setDisable(false);
    dealButton.setDisable(true);
    hitButton.setDisable(true);
    stayButton.setDisable(true);
    betTextField.setPromptText("Bet 5-500");
    player.setValueOfHand(0);
    dealer.setValueOfHand(0);
    resetTable();

    if (deck.getShoe().size() < 52) {
      deck.shuffleDeck();
      deck.addDeckToShoe();
    }
  }

  private void checkIfGameOver() {
    if (player.getSumOfChips() < 5) {
      newGameButton.setVisible(true);
      newRoundButton.setVisible(false);
      quitButton.setVisible(false);
      exitButton.setVisible(true);
      result.setText("Game over. Click new game to play again!");
    }
  }

  private void evaluateRoundAndPrintResult() {

    int valueOfPlayerHand = player.getValueOfHand();
    int valueOfDealerHand = dealer.getValueOfHand();

    dealerHiddenCard.setVisible(false);
    if (valueOfPlayerHand == valueOfDealerHand) {
      result.setText(
          "Player hand: " + valueOfPlayerHand + ". Dealer hand: " + valueOfDealerHand + ". The hand is a push!");
      player.addChips(player.getRoundBet());
    }
    if (player.getValueOfHand() > dealer.getValueOfHand()) {
      result.setText("Player hand: " + valueOfPlayerHand + ". Dealer hand: " + valueOfDealerHand + ". Player wins!");
      player.addChips(player.getRoundBet() * 2);
    }
    if (player.getValueOfHand() < dealer.getValueOfHand()) {
      result.setText("Player hand: " + valueOfPlayerHand + ". Dealer hand: " + valueOfDealerHand + ". Dealer wins!");
    }
    if (dealer.getValueOfHand() > 21) {
      result.setText("Player hand: " + valueOfPlayerHand + ". Dealer hand: " + valueOfDealerHand
          + ". Dealer is bust! Player wins!");
      player.addChips(player.getRoundBet() * 2);
    }
    chipsDisplay.setText("Sum: " + player.getSumOfChips());
    newRoundButton.setDisable(false);

  }

  private void setSuitColor(Hand hand, Label currentLabel, int index) {

    String currentSuit = hand.getViewOfHand().get(index).getSuit();

    if (currentSuit.matches("Hearts") || currentSuit.matches("Diamonds")) {
      currentLabel.setTextFill(Color.RED);
    }
  }

  private void setValueOfCurrentHand(Hand h) {
    List<Card> hand = h.getViewOfHand();
    int numberOfAces = numberOfAces(hand);
    int valueOfHand = 0;

    for (int i = 0; i < hand.size(); ++i) {
      valueOfHand += hand.get(i).getValue();
    }

    while (valueOfHand > 21 && numberOfAces != 0) {
      valueOfHand -= 10;
      numberOfAces--;
    }
    h.setValueOfHand(valueOfHand);
  }

  private int numberOfAces(List<Card> hand) {
    return (int) hand.stream().filter(new Predicate<Card>() {
      @Override
      public boolean test(Card card) {
        return (card.getFace().equals("Ace"));
      }
    }).count();
  }
}
