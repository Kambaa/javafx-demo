package io.github.kambaa.javafxdemo;

import static io.github.kambaa.javafxdemo.Utils.getJavaVersion;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HelloController {

  private Stage primaryStage;

  // Called from Main.java after loading FXML
  public void setPrimaryStage(Stage stage) {
    this.primaryStage = stage;
  }

  @FXML
  private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {

    welcomeText.setText("Welcome to JavaFX Application!");
  }

  @FXML
  private void handleSelectJdkDirectory() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Choose a directory");

    // Set initial directory (optional)
    File initialDir = new File(System.getProperty("user.home"));
    if (initialDir.exists()) {
      directoryChooser.setInitialDirectory(initialDir);
    }
    File selectedDirectory = directoryChooser.showDialog(primaryStage);


    if (selectedDirectory == null || !selectedDirectory.isDirectory()) {
      System.out.println("❌ No valid folder selected.");
      return;
    }

    if (selectedDirectory != null && selectedDirectory.isDirectory()) {
      System.out.println("Selected Directory: " + selectedDirectory.getAbsolutePath());

      boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

      File javaExe = new File(selectedDirectory, "bin/" + (isWindows ? "java.exe" : "java"));
      File keytoolExe = new File(selectedDirectory, "bin/" + (isWindows ? "keytool.exe" : "keytool"));

      if (!javaExe.exists() || !javaExe.canExecute()) {
        System.out.println("❌ java executable not found or not executable.");
        return;
      }

      if (!keytoolExe.exists() || !keytoolExe.canExecute()) {
        System.out.println("❌ keytool not found or not executable.");
        return;
      }

      String jdkVersion = getJavaVersion(javaExe);
      if (jdkVersion == null) {
        System.out.println("❌ Could not determine Java version.");
        return;
      }

      System.out.println("🧠 Detected JDK version: " + jdkVersion);

      File cacerts;
      if (jdkVersion.startsWith("1.8")) {
        cacerts = new File(selectedDirectory, "jre/lib/security/cacerts");
      } else {
        cacerts = new File(selectedDirectory, "lib/security/cacerts");
      }

      if (cacerts.exists()) {
        System.out.println("✅ All good! 'cacerts' found at: " + cacerts.getAbsolutePath());
      } else {
        System.out.println("❌ 'cacerts' not found where expected: " + cacerts.getAbsolutePath());
      }

    } else {
      System.out.println("❌ Invalid selection or no directory chosen.");
    }

    // if (selectedDirectory != null) {
    //   System.out.println("Selected Directory: " + selectedDirectory.getAbsolutePath());
    // } else {
    //   System.out.println("No Directory selected.");
    // }
  }

  @FXML
  private void handleCertFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Certificate File");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Certificate Files", "*.crt"));
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null) {
      System.out.println("Selected File: " + selectedFile.getAbsolutePath());
    } else {
      System.out.println("No Certificate File selected.");
    }
  }





}