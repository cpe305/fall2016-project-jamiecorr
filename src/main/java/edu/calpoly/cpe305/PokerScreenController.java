package edu.calpoly.cpe305;

import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.Statement;

public class PokerScreenController implements Initializable {

  private Deck deck = new Deck();
  private Hand playersHand = new Hand("Player");
  int betChoice = 0;
  boolean isPug1, isPug2, isPug3, isPug4, isPug5;
  Card cardA, cardB, cardC, cardD, cardE;

  @FXML
  private Rectangle vertRect1, vertRect2, vertRect3, vertRect4, vertRect5, royalRect, straightRect, fourRect, fullRect, flushRect, straightRect1, threeRect, twoRect, jacksRect;

  @FXML
  private Button finishButton, betButton, dealButton, newRoundButton, betButton1, betButton2, betButton3, betButton4, betButton5;
  
  @FXML
  private Button disButton1, disButton2, disButton3, disButton4, disButton5;   
  
  @FXML
  private ImageView redArrowImage, playerCard1, playerCard2, playerCard3, playerCard4, playerCard5;

  @FXML
  private Label welcomeLabel, bankLabel2;

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
    
    betButton1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        redArrowImage.setVisible(false);

        welcomeLabel.setText("");
        dealButton.setDisable(false);

        betChoice = 1;
        playersHand.setRoundBet(betChoice);

        vertRect1.setVisible(true);
        vertRect2.setVisible(false);
        vertRect3.setVisible(false);
        vertRect4.setVisible(false);
        vertRect5.setVisible(false);
      }
    });
    
    betButton2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        redArrowImage.setVisible(false);

        welcomeLabel.setText("");
        dealButton.setDisable(false);

        betChoice = 2;
        playersHand.setRoundBet(betChoice);

        vertRect2.setVisible(true);
        vertRect1.setVisible(false);
        vertRect3.setVisible(false);
        vertRect4.setVisible(false);
        vertRect5.setVisible(false);
      }
    });
    
    betButton3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        redArrowImage.setVisible(false);

        welcomeLabel.setText("");
        dealButton.setDisable(false);

        betChoice = 3;
        playersHand.setRoundBet(betChoice);

        vertRect3.setVisible(true);
        vertRect1.setVisible(false);
        vertRect2.setVisible(false);
        vertRect4.setVisible(false);
        vertRect5.setVisible(false);
      }
    });
    
    betButton4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        redArrowImage.setVisible(false);

        welcomeLabel.setText("");
        dealButton.setDisable(false);

        betChoice = 4;
        playersHand.setRoundBet(betChoice);

        vertRect4.setVisible(true);
        vertRect1.setVisible(false);
        vertRect2.setVisible(false);
        vertRect3.setVisible(false);
        vertRect5.setVisible(false);
      }
    });
    
    betButton5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        redArrowImage.setVisible(false);

        welcomeLabel.setText("");
        dealButton.setDisable(false);

        betChoice = 5;
        playersHand.setRoundBet(betChoice);

        vertRect5.setVisible(true);
        vertRect1.setVisible(false);
        vertRect2.setVisible(false);
        vertRect3.setVisible(false);
        vertRect4.setVisible(false);
      }
    });
    
    disButton1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isPug1) {
          playerCard1.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardA.getPosition() + cardA.getSuit().charAt(0) + ".png"));
          isPug1 = false;
          disButton1.setText("Discard");

        } else {
          playerCard1.setImage(new Image("/edu/calpoly/cpe305/Images/cardBack.png"));
          isPug1 = true;
          disButton1.setText("Undo");
        }
      }
    });
    
    disButton2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isPug2) {
          playerCard2.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardB.getPosition() + cardB.getSuit().charAt(0) + ".png"));
          isPug2 = false;
          disButton2.setText("Discard");

        } else {
          playerCard2.setImage(new Image("/edu/calpoly/cpe305/Images/cardBack.png"));
          isPug2 = true;
          disButton2.setText("Undo");

        }
      }
    });
    
    disButton3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isPug3) {
          playerCard3.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardC.getPosition() + cardC.getSuit().charAt(0) + ".png"));
          isPug3 = false;
          disButton3.setText("Discard");

        } else {
          playerCard3.setImage(new Image("/edu/calpoly/cpe305/Images/cardBack.png"));
          isPug3 = true;
          disButton3.setText("Undo");

        }
      }
    });
    
    disButton4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isPug4) {
          playerCard4.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardD.getPosition() + cardD.getSuit().charAt(0) + ".png"));
          isPug4 = false;
          disButton4.setText("Discard");

        } else {
          playerCard4.setImage(new Image("/edu/calpoly/cpe305/Images/cardBack.png"));
          isPug4 = true;
          disButton4.setText("Undo");

        }
      }
    });
    
    disButton5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (isPug5) {
          playerCard5.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardE.getPosition() + cardE.getSuit().charAt(0) + ".png"));
          isPug5 = false;
          disButton5.setText("Discard");

        } else {
          playerCard5.setImage(new Image("/edu/calpoly/cpe305/Images/cardBack.png"));
          isPug5 = true;
          disButton5.setText("Undo");

        }
      }
    });
    
    dealButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        
        disableBetButtons();
        showDisButtons();
        dealCards();
        
        dealButton.setDisable(true);
        finishButton.setDisable(false);

      }
    });
    
    finishButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        
        
        hideDisButtons();
        finishButton.setDisable(true);
        newRoundButton.setDisable(false);
        
        reDeal();
        evaluateRoundandPrintResult();
        bankLabel2.setText("  $" + playersHand.getSumOfChips());
        checkIfBroke();
      }
    });

    newRoundButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        startGame();
      }
    });

  }

  private void reDeal() {
    boolean[] choices = { isPug1, isPug2, isPug3, isPug4 , isPug5 };
    ImageView[] playerCards = { playerCard1, playerCard2, playerCard3, playerCard4, playerCard5 };
    for (int i = 0; i < 5; i++) {
      ImageView current = playerCards[i];
      if (choices[i] == true) {
        playersHand.removeCard(i);

        Card newCard = deck.drawRandomCard();
        playersHand.addCardToIndex(i, newCard);
        current.setImage(new Image("/edu/calpoly/cpe305/Images/" + newCard.getPosition()
            + newCard.getSuit().charAt(0) + ".png"));
      }
    }
  }

  private void evaluateRoundandPrintResult() {
    welcomeLabel.setText("You won!");

    if (PokerHandEvaluator.hasRoyalFlush(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 250);
      royalRect.setVisible(true);
    } else if (PokerHandEvaluator.hasStraightFlush(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 50);
      straightRect.setVisible(true);
    } else if (PokerHandEvaluator.hasQuad(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 25);
      fourRect.setVisible(true);
    } else if (PokerHandEvaluator.hasFullHouse(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 9);
      fullRect.setVisible(true);
    } else if (PokerHandEvaluator.hasFlush(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 6);
      flushRect.setVisible(true);
    } else if (PokerHandEvaluator.hasStraight(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 4);
      straightRect1.setVisible(true);
    } else if (PokerHandEvaluator.hasTriple(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 3);
      threeRect.setVisible(true);
    } else if (PokerHandEvaluator.hasTwoPairs(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet() * 2);
      twoRect.setVisible(true);
    } else if (PokerHandEvaluator.hasFacePair(playersHand)) {
      playersHand.addChips(playersHand.getRoundBet());
      jacksRect.setVisible(true);
    } else {
      playersHand.subChips(playersHand.getRoundBet());
      welcomeLabel.setText("You lost");
    }
  }

  protected void dealCards() {
    cardA = deck.drawRandomCard();
    playersHand.addCard(cardA);
    cardB = deck.drawRandomCard();
    playersHand.addCard(cardB);
    cardC = deck.drawRandomCard();
    playersHand.addCard(cardC);
    cardD = deck.drawRandomCard();
    playersHand.addCard(cardD);
    cardE = deck.drawRandomCard();
    playersHand.addCard(cardE);
    
    playerCard1.setVisible(true);
    playerCard1.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardA.getPosition() + cardA.getSuit().charAt(0) + ".png"));
    playerCard2.setVisible(true);
    playerCard2.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardB.getPosition() + cardB.getSuit().charAt(0) + ".png"));
    playerCard3.setVisible(true);
    playerCard3.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardC.getPosition() + cardC.getSuit().charAt(0) + ".png"));
    playerCard4.setVisible(true);
    playerCard4.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardD.getPosition() + cardD.getSuit().charAt(0) + ".png"));
    playerCard5.setVisible(true);
    playerCard5.setImage(new Image("/edu/calpoly/cpe305/Images/" + cardE.getPosition() + cardE.getSuit().charAt(0) + ".png"));
    
  }

  protected void hideDisButtons() {
    disButton1.setVisible(false);
    disButton2.setVisible(false);
    disButton3.setVisible(false);
    disButton4.setVisible(false);
    disButton5.setVisible(false);
    disButton1.setText("Discard");
    disButton2.setText("Discard");
    disButton3.setText("Discard");
    disButton4.setText("Discard");
    disButton5.setText("Discard");
  }
  
  protected void showDisButtons() {
    disButton1.setVisible(true);
    disButton2.setVisible(true);
    disButton3.setVisible(true);
    disButton4.setVisible(true);
    disButton5.setVisible(true);
  }

  protected void disableBetButtons() {
    betButton1.setDisable(true);
    betButton2.setDisable(true);
    betButton3.setDisable(true);
    betButton4.setDisable(true);
    betButton5.setDisable(true);
  }
  
  protected void enableBetButtons() {
    betButton1.setDisable(false);
    betButton2.setDisable(false);
    betButton3.setDisable(false);
    betButton4.setDisable(false);
    betButton5.setDisable(false);
  }

  private void checkIfBroke() {
    if (playersHand.getSumOfChips() <= 0) {
      newRoundButton.setDisable(true);
      welcomeLabel.setText("Game over, no more money!");
    }
  }

  private void startGame() {
    playersHand.clearHand();
    redArrowImage.setVisible(true);
    welcomeLabel.setText("Select a bet level to start");
    enableBetButtons();
    newRoundButton.setDisable(true);
    bankLabel2.setText("  $" + playersHand.getSumOfChips());
    playersHand.setRoundBet(0);
    finishButton.setDisable(true);
    dealButton.setDisable(true);
    newRoundButton.setDisable(true);
    
    hideDisButtons();
    
    isPug1 = false;
    isPug2 = false;
    isPug3 = false;
    isPug4 = false;
    isPug5 = false;
    playerCard1.setVisible(false);
    playerCard2.setVisible(false);
    playerCard3.setVisible(false);
    playerCard4.setVisible(false);
    playerCard5.setVisible(false);
    vertRect1.setVisible(false);
    vertRect2.setVisible(false);
    vertRect3.setVisible(false);
    vertRect4.setVisible(false);
    vertRect5.setVisible(false);
    royalRect.setVisible(false);
    straightRect.setVisible(false);
    fourRect.setVisible(false);
    fullRect.setVisible(false);
    flushRect.setVisible(false);
    straightRect1.setVisible(false);
    threeRect.setVisible(false);
    twoRect.setVisible(false);
    jacksRect.setVisible(false);

    
    if (deck.getSize() < 52) {
      deck = new Deck();
    }
  }

}