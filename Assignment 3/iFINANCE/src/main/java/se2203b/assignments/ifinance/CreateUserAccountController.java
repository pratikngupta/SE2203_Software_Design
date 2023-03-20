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
import java.util.ResourceBundle;

import static se2203b.assignments.ifinance.Debug.printGREEN;
import static se2203b.assignments.ifinance.Debug.printRED;

public class CreateUserAccountController implements Initializable {

    public Text errorMessageField;
    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField firstPasswordField;

    @FXML
    private TextField fullnameField;

    @FXML
    private PasswordField secondPasswordField;

    @FXML
    private TextField usernameField;

    private UserAdapter userAdapter;

    private final User newUser = new User();

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        if (verify()) {
            if (firstPasswordField.getText().equals(secondPasswordField.getText())) {

                newUser.setAddress(addressField.getText());
                newUser.setEmail(emailField.getText());
                newUser.setFullname(fullnameField.getText());
                newUser.setPassword(firstPasswordField.getText());
                newUser.setUsername(usernameField.getText());

                userAdapter.addUser(newUser);

                printGREEN("CreateUser", "success");
                Stage stage = (Stage) usernameField.getScene().getWindow();
                // Close stage
                stage.close();
            } else {
                printRED("CreateUser", "failed");
            }
        }
    }

    boolean verify() throws SQLException {
        if (usernameField.getText().isEmpty() && fullnameField.getText().isEmpty() && addressField.getText().isEmpty() && emailField.getText().isEmpty() && firstPasswordField.getText().isEmpty() && secondPasswordField.getText().isEmpty()) {
            //show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter all fields");
            printRED("CreateUser", "failed --> Please enter all fields");
            return false;
        } else if (fullnameField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter full name");
            printRED("CreateUser", "failed --> Please enter full name");
            return false;
        } else if (addressField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter address");
            printRED("CreateUser", "failed --> Please enter address");
            return false;
        } else if (emailField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter email");
            printRED("CreateUser", "failed --> Please enter email");
            return false;
        } else if (usernameField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter username");
            printRED("CreateUser", "failed --> Please enter username");
            return false;
        } else if (firstPasswordField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter password");
            printRED("CreateUser", "failed --> Please enter password");
            return false;
        } else if (secondPasswordField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter confirm password");
            printRED("CreateUser", "failed --> Please enter confirm password");
            return false;
        } else if (!firstPasswordField.getText().equals(secondPasswordField.getText())) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Password does not match");
            printRED("CreateUser", "failed --> Password does not match");
            return false;
        } else if (userAdapter.checkUser(usernameField.getText())) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Username already exists");
            printRED("CreateUser", "failed --> Username already exists");
            return false;
        }
        return true;
    }

    public void setModel(UserAdapter users) {
        this.userAdapter = users;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessageField.setVisible(false);
    }
}
