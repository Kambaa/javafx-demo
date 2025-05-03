package io.github.kambaa.javafxdemo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setResizable(false);
    // Get the controller instance and inject the stage
    MainController controller = fxmlLoader.getController();
    controller.setPrimaryStage(stage);

    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();


  }

  public static void main(String[] args) {
    launch();
  }
}