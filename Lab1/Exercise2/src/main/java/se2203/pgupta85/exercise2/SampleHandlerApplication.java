/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 19th January 2023
 * Student ID: 251211859
 * Task: Exercise 3
 * Description: This is the main class for the sample handler. This class is used to launch the GUI
 **********************************************************************************************************************/

package se2203.pgupta85.exercise2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleHandlerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file.
        FXMLLoader fxmlLoader = new FXMLLoader(SampleHandlerApplication.class.getResource("SampleHandler-view.fxml"));
        // Create and display the stage.
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        // Set the scene to the stage.
        stage.setTitle("SampleHandler");
        stage.setScene(scene);
        // Show the stage.
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}