/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 27 January 2023
 * Student ID: 251211859
 * Task: Exercise 3
 * Description: This is the main class for the Slider Application.
 **********************************************************************************************************************/

package lab2.pgupta85.exercise3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import fx:deploy to create a jar file

import java.io.IOException;

public class SliderApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SliderApplication.class.getResource("Slider-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Slider");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}