
package edu.calpoly.cpe305;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root = new BorderPane();
      Scene scene = new Scene(root, 400, 400);
      scene.getStylesheets().add("application.css");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception exc) {
      exc.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
