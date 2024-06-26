package se2203b.assignments.ifinance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static se2203b.assignments.ifinance.Debug.printRED;

public class DisplayAlert {
    public static void displayAlert(String msg) {
        try {
            FXMLLoader loader = new FXMLLoader(DisplayAlert.class.getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);

            printRED("ALERT", msg);

            stage.showAndWait();

        } catch (IOException ex1) {
            System.out.println("ERROR: " + ex1.getMessage());
        }
    }
}
