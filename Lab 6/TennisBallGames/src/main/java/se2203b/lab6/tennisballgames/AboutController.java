package se2203b.lab6.tennisballgames;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Abdelkader Ouda
 */
public class AboutController  {

    @FXML
    Button okBtn;


    public void exit() {
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }

}
