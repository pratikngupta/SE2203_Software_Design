package se2203b.assignments.ifinance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static se2203b.assignments.ifinance.Debug.*;

public class LoginController implements Initializable {

    public Text errorTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    private UserAdapter userAdapter;

    public void setModel(UserAdapter userAdapter) {
        printPURPLE("LoginController", "setModel");
        this.userAdapter = userAdapter;
    }
    @FXML
    void cancelButtonClicked(ActionEvent event) {
        // Get current stage reference
        Stage stage = (Stage) passwordField.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @FXML
    void okButtonClicked() throws SQLException {

        // check if username and password are filled
        if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter username and password");
            printRED("LoginController", "Login failed --> username and password are empty");

        } else if (usernameField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter username");
            printRED("LoginController", "Login failed --> username is empty");

        } else if (passwordField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter password");
            printRED("LoginController", "Login failed --> password is empty");
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean login = userAdapter.checkUser(username, password);


        userAdapter.updateUser(username, login);

        if (login) {
            printGREEN("LoginController", "Login successful");
            // Get current stage reference
            Stage stage = (Stage) passwordField.getScene().getWindow();
            // Close stage
            stage.close();
        } else {
            // show error message
            printRED("LoginController", "Login failed");
            errorTextField.setVisible(true);
            errorTextField.setText("Username or password is incorrect");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        printBLUE("LoginController", "initialize");
    }
}
