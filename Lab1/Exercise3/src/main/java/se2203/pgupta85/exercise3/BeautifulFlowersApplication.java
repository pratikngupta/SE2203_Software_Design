package se2203.pgupta85.exercise3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BeautifulFlowersApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BeautifulFlowersApplication.class.getResource("BeautifulFlowers-view.fxml"));
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