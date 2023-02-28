package assignment2.pgupta85.ifinance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class iFinanceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(iFinanceApplication.class.getResource("iFINANCE-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 725);
        stage.setTitle("iFinance");
        stage.setScene(scene);
        stage.getIcons().add(new Image(iFinanceApplication.class.getResourceAsStream("WesternLogo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}