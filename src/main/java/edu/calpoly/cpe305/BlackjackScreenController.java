package edu.calpoly.cpe305;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.Statement;

public class BlackjackScreenController implements Initializable {

  private Deck deck = new Deck();
  private Hand playersHand = new Hand("Player");
  private Hand dealersHand = new Hand("Dealer");
  Bank myBank = Bank.getInstance();

  @FXML
  private TextField betTextField;

  @FXML
  private Button betButton, dealButton, hitButton, standButton, doubleButton, newRoundButton;

  @FXML
  private ImageView redArrowImage, playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6, playerChip;

  @FXML
  private ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerChip;

  @FXML
  private Label welcomeLabel, bankLabel2, playerChipLabel, dealerChipLabel, resultLabel;

  @FXML
  private void handleBackButtonAction(ActionEvent event) throws IOException {
    Parent home_page_parent = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
    Scene home_page_scene = new Scene(home_page_parent);
    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    app_stage.setScene(home_page_scene);
    app_stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    startGame();

    betButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        redArrowImage.setVisible(false);
        welcomeLabel.setText("");

        try {
          int newBet = Integer.parseInt(betTextField.getText().trim());
          if (newBet < 0) {
            throw new NumberFormatException();
          } else if (newBet > myBank.getSumOfChips()) {
            throw new IllegalArgumentException();
          } else {
            myBank.setRoundBet(newBet);
            betTextField.setDisable(true);
            betButton.setDisable(true);
            dealButton.setDisable(false);
            betTextField.setStyle("-fx-border-color: black;");
          }
        } catch (NumberFormatException exc) {
          betTextField.setStyle("-fx-border-color: red;");
          welcomeLabel.setText("Enter a positive integer ");
        } catch (IllegalArgumentException exc) {
          betTextField.setStyle("-fx-border-color: red;");
          welcomeLabel.setText("Enter a positive integer <= " + myBank.getSumOfChips());
        }
      }
    });

    dealButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        betButton.setDisable(true);

        dealButton.setDisable(true);
        playerChip.setVisible(true);

        Card card1, card2, card3, card4;
        card1 = deck.drawRandomCard();
        playersHand.addCard(card1);
        card2 = deck.drawRandomCard();
        dealersHand.addCard(card2);
        card3 = deck.drawRandomCard();
        playersHand.addCard(card3);
        card4 = deck.drawRandomCard();
        dealersHand.addCard(card4);

        playerCard1.setVisible(true);
        playerCard1.setImage(
            new Image("/edu/calpoly/cpe305/Images/" + card1.getPosition() + card1.getSuit().charAt(0) + ".png"));

        dealerCard1.setVisible(true);
        dealerCard1.setImage(
            new Image("/edu/calpoly/cpe305/Images/" + card2.getPosition() + card2.getSuit().charAt(0) + ".png"));
        playerCard2.setVisible(true);
        playerCard2.setImage(
            new Image("/edu/calpoly/cpe305/Images/" + card3.getPosition() + card3.getSuit().charAt(0) + ".png"));

        dealerCard2.setVisible(true);
        dealerCard2.setImage(new Image("/edu/calpoly/cpe305/Images/cardBackB.png"));
        dealerCard2.setFitWidth(74);
        
        playerChipLabel.setText("" + BlackjackHandEvaluator.getHandValue(playersHand));
        
        hitButton.setDisable(false);
        standButton.setDisable(false);
        doubleButton.setDisable(false);
      }
    });

    hitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        myBank.setRoundBet(myBank.getRoundBet());
        makeHitMove();
        bankLabel2.setText("  $" + myBank.getSumOfChips());
      }
    });

    standButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        hitButton.setDisable(true);
        doubleButton.setDisable(true);

        while (BlackjackHandEvaluator.getHandValue(dealersHand) < 17) {
          Card newCard = deck.drawRandomCard();
          dealersHand.addCard(newCard);
        }

        dealerCardHandler();
        evaluateRoundAndPrintResult();
        standButton.setDisable(true);
        checkIfBroke();
        bankLabel2.setText("  $" + myBank.getSumOfChips());
      }
    });

    doubleButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        myBank.setRoundBet(myBank.getRoundBet() * 2);
        makeHitMove();
        bankLabel2.setText("  $" + myBank.getSumOfChips());
      }
    });

    newRoundButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        startGame();
      }
    });

  }

  protected void makeHitMove() {
    Card newCard = deck.drawRandomCard();
    playersHand.addCard(newCard);

    ImageView[] playerCards = { playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6 };
    for (int i = 0; i < playersHand.size(); ++i) {
      ImageView current = playerCards[i];
      current.setVisible(true);
      current.setImage(new Image("/edu/calpoly/cpe305/Images/" + playersHand.getCard(i).getPosition()
          + playersHand.getCard(i).getSuit().charAt(0) + ".png"));
    }

    if (BlackjackHandEvaluator.getHandValue(playersHand) > 21) {
      myBank.subChips(myBank.getRoundBet());
      welcomeLabel.setText("Busted, house wins!");
      playerChipLabel.setText("" + BlackjackHandEvaluator.getHandValue(playersHand));
      newRoundButton.setDisable(false);
      hitButton.setDisable(true);
      standButton.setDisable(true);
      dealButton.setDisable(true);
      doubleButton.setDisable(true);
      checkIfBroke();
    } else {
        playerChipLabel.setText("" + BlackjackHandEvaluator.getHandValue(playersHand));
    }
    
  }

  protected void dealerCardHandler() {
    ImageView[] dealerCards = { dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6 };
    for (int i = 0; i < dealersHand.size(); ++i) {
      ImageView current = dealerCards[i];
      current.setVisible(true);
      current.setImage(new Image("/edu/calpoly/cpe305/Images/" + dealersHand.getCard(i).getPosition()
          + dealersHand.getCard(i).getSuit().charAt(0) + ".png"));
    }
  }

  private void checkIfBroke() {
    if (myBank.getSumOfChips() <= 0) {
      newRoundButton.setDisable(true);
      welcomeLabel.setText("Game over, no more money!");
    }
  }

  private void evaluateRoundAndPrintResult() {
    int valueOfPlayerHand = BlackjackHandEvaluator.getHandValue(playersHand);
    int valueOfDealerHand = BlackjackHandEvaluator.getHandValue(dealersHand);

    
    dealerChipLabel.setText("" + BlackjackHandEvaluator.getHandValue(dealersHand));
    dealerCardHandler();
    
    if (valueOfPlayerHand == valueOfDealerHand) {
      welcomeLabel.setText("The hand is a push!");
    } else if ((valueOfPlayerHand > valueOfDealerHand) || (valueOfDealerHand > 21)) {
      welcomeLabel.setText("Player wins!");
      myBank.addChips(myBank.getRoundBet());
    } else if (valueOfPlayerHand < valueOfDealerHand) {
      welcomeLabel.setText("Dealer wins!");
      myBank.subChips(myBank.getRoundBet());
    }
    
    dealerChip.setVisible(true);
    playerChip.setVisible(true);
    dealerChipLabel.setText("" + valueOfDealerHand);
    playerChipLabel.setText("" + valueOfPlayerHand);
    
    newRoundButton.setDisable(false);
  }


  private void resetTable() {
    playersHand.clearHand();
    dealersHand.clearHand();
    myBank.setRoundBet(0);
    betTextField.setText("");

    ImageView[] playerCards = { playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6 };
    ImageView[] dealerCards = { dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6 };

    for (ImageView pStack : playerCards) {
      pStack.setVisible(false);
    }
    for (ImageView dStack : dealerCards) {
      dStack.setVisible(false);
    }
  }

  private void startGame() {    
    welcomeLabel.setText("Place a bet to start");
    redArrowImage.setVisible(true);
    playerChipLabel.setText("");
    dealerChipLabel.setText("");
    playerChip.setVisible(false);
    dealerChip.setVisible(false);
    
    betTextField.setStyle("-fx-border-color: black;");
    newRoundButton.setDisable(true);
    bankLabel2.setText("  $" + myBank.getSumOfChips());
    myBank.setRoundBet(0);
    betTextField.setFocusTraversable(true);
    betTextField.requestFocus();
    
    betTextField.setDisable(false);
    betButton.setDisable(false);
    dealButton.setDisable(true);
    hitButton.setDisable(true);
    standButton.setDisable(true);
    doubleButton.setDisable(true);
    
    resetTable();

    if (deck.getSize() < 52) {
      deck = new Deck();
    }
  }

}