package edu.calpoly.cpe305;

import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
  
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      Scene scene = new Scene(root, 400, 400);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception exc) {
      LOGGER.log(Level.SEVERE,"context", exc);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
