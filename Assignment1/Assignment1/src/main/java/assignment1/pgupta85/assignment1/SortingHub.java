package assignment1.pgupta85.assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SortingHub extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SortingHub.class.getResource("SortingHub-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sorting Hub");
        stage.getIcons().add(new Image("file:src/main/resources/assignment1/pgupta85/assignment1/WesternLogo.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}