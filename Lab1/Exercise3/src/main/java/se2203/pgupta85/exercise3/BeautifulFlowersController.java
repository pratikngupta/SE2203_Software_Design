package se2203.pgupta85.exercise3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BeautifulFlowersController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}