package edu.noob.blackjackfxproject;

//import classes.com.ar.blackjackproject.New.*;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

/**
 *
 * @author Anders
 */
public class FXStart extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    stage.setTitle("Black Jack");
    Player player = new Player();
    Dealer dealer = new Dealer();
    Deck deck = new Deck();
    deck.shuffleDeck();
    deck.addDeckToShoe();
    List<Card> playersHand = player.getViewOfHand();
    List<Card> dealersHand = dealer.getViewOfHand();

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(FXStart.class.getResource("BlackJack.fxml"));
    loader.setController(new Controller(player, dealer, deck, playersHand, dealersHand));

    StackPane root = loader.load();
    root.setAlignment(Pos.CENTER);

    Path path = new Path();
    path.setStrokeWidth(2);
    path.setRotate(180);
    path.setStroke(Color.BLANCHEDALMOND);

    MoveTo move = new MoveTo();
    move.setX(100);
    move.setY(150);

    QuadCurveTo quadCurve1 = new QuadCurveTo();
    quadCurve1.setX(500);
    quadCurve1.setY(150);
    quadCurve1.setControlX(300);
    quadCurve1.setControlY(10);

    LineTo line1 = new LineTo();
    line1.setX(500);
    line1.setY(180);

    QuadCurveTo quadCurve2 = new QuadCurveTo();
    quadCurve2.setX(100);
    quadCurve2.setY(180);
    quadCurve2.setControlX(300);
    quadCurve2.setControlY(30);

    LineTo line2 = new LineTo();
    line2.setX(100);
    line2.setY(150);

    path.getElements().addAll(move, quadCurve1, line1, quadCurve2, line2);

    root.getChildren().add(path);
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("BlackJack.css").toExternalForm());
    stage.setScene(scene);
    stage.setAlwaysOnTop(true);

    stage.show();
  }
}
