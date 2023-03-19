package se2203b.assignments.ifinance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {

    public Text errorTextField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        // Get current stage reference
        Stage stage = (Stage) passwordField.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @FXML
    void okButtonClicked() {

        // check if username and password are filled
        if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter username and password");

        } else if (usernameField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter username");

        } else if (passwordField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter password");

        }


    }

}
