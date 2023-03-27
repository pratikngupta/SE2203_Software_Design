/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 19th January 2023
 * Student ID: 251211859
 * Task: Exercise 1
 * Description: Created a skeleton for the undestanding JavaFX and Scene Builder
 **********************************************************************************************************************/

package se2203.pgupta85.exercise1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240); // Create the scene using the FXML file.
        stage.setTitle("Hello!"); // Set the stage title.
        stage.setScene(scene); // Set the scene on the stage.
        stage.show();   // Show the stage to the user.
    }

    // The main() method is ignored in correctly deployed JavaFX application.
    public static void main(String[] args) {
        launch();
    }
}