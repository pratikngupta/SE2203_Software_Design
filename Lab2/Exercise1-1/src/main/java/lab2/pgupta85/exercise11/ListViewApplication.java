/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 27 January 2023
 * Student ID: 251211859
 * Task: Exercise 1-1
 * Description: This is the main class for the ListView Application.
 **********************************************************************************************************************/

package lab2.pgupta85.exercise11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListViewApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ListViewApplication.class.getResource("ListView-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ListView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}