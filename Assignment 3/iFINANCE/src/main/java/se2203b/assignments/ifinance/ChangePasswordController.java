package se2203b.assignments.ifinance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.assignments.ifinance.Debug.*;

public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField FirstNewPasswordField;

    @FXML
    private Text infoField, errorMessageField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField secondNewPasswordField;

    private UserAdapter userAdapter;

    private User currentUser;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) infoField.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @FXML
    void save() throws SQLException {
        if (oldPasswordField.getText().isEmpty() && FirstNewPasswordField.getText().isEmpty() && secondNewPasswordField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter all fields");
            printRED("ChangePassword", "failed --> Please enter all fields");

        } else if (oldPasswordField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter old password");
            printRED("ChangePassword", "failed --> Please enter old password");

        } else if (FirstNewPasswordField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter new password");
            printRED("ChangePassword", "failed --> Please enter new password");

        } else if (secondNewPasswordField.getText().isEmpty()) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("Please enter new password again");
            printRED("ChangePassword", "failed --> Please enter new password again");
        }
        else if (!FirstNewPasswordField.getText().equals(secondNewPasswordField.getText())) {
            // show error message
            errorMessageField.setVisible(true);
            errorMessageField.setText("New passwords do not match");
            printRED("ChangePassword", "failed --> New passwords do not match");
        }
        else {

            String oldPassword = oldPasswordField.getText();
            String newPassword = FirstNewPasswordField.getText();
            String username = currentUser.getUsername();

            boolean login = userAdapter.checkUser(username, oldPassword);
            if (login) {
                userAdapter.updateUser(username, newPassword);

                printGREEN("ChangePassword", "Password changed successfully");

                // Get current stage reference
                Stage stage = (Stage) infoField.getScene().getWindow();
                // Close stage
                stage.close();

            } else {
                errorMessageField.setVisible(true);
                errorMessageField.setText("Old password is incorrect");
                printRED("ChangePassword", "failed --> Old password is incorrect");
            }
        }

    }

    public void setModel(UserAdapter userAdapter, User currentUser) {

        printPURPLE("ChangePassword", "setModel");

        this.userAdapter = userAdapter;
        this.currentUser = currentUser;

        infoField.setText("ChangePassword for " + currentUser.getUsername());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        printBLUE("ChangePassword", "initialize");
    }
}

