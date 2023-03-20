package se2203b.assignments.ifinance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.assignments.ifinance.Debug.*;

public class LoginController implements Initializable {

    public Text errorTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    private UserAdapter userAdapter;
    private User currentUser;
    private MenuBar mainMenu;

    public void setModel(UserAdapter userAdapter, User currentUser, MenuBar mainMenu) {
        printPURPLE("LoginController", "setModel");
        this.userAdapter = userAdapter;
        this.currentUser = currentUser;
        this.mainMenu = mainMenu;
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

        if(!check()){
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean login = userAdapter.checkUser(username, password);

        userAdapter.updateUser(username, login);

        if (login) {
            if (currentUser != null) {
                userAdapter.updateUser(currentUser.getUsername(), false);
            }

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

    public boolean check(){

        // check if username and password are filled
        if (usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter username and password");
            printRED("LoginController", "Login failed --> username and password are empty");
            return false;

        } else if (usernameField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter username");
            printRED("LoginController", "Login failed --> username is empty");
            return false;

        } else if (passwordField.getText().isEmpty()) {
            // show error message
            errorTextField.setVisible(true);
            errorTextField.setText("Please enter password");
            printRED("LoginController", "Login failed --> password is empty");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        printBLUE("LoginController", "initialize");
    }
}
