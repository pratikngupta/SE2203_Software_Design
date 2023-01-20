/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 19th January 2023
 * Student ID: 251211859
 * Task: Exercise 3
 * Description: This is the main class for the Beautiful Flowers Application. It is used to create the GUI for the application.
 * It is a major improvement over the previous version of the application.
 **********************************************************************************************************************/

package se2203.pgupta85.exercise3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BeautifulFlowersImprovedApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BeautifulFlowersImprovedApplication.class.getResource("BeautifulFlowers-improvedView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Beautiful Flowers");
        stage.setScene(scene);
        stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/se2203/pgupta85/exercise3/WesternLogo.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
