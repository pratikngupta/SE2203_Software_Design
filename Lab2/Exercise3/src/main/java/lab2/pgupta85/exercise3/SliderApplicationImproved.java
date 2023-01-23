package lab2.pgupta85.exercise3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SliderApplicationImproved extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SliderApplicationImproved.class.getResource("Slider-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}