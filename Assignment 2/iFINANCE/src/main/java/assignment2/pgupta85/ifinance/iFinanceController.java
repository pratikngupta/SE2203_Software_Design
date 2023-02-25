package assignment2.pgupta85.ifinance;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class iFinanceController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}