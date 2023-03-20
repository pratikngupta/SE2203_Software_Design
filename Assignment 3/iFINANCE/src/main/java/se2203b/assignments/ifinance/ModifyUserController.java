package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.assignments.ifinance.Debug.*;
import static se2203b.assignments.ifinance.DisplayAlert.displayAlert;

public class ModifyUserController implements Initializable {

    public ComboBox<String> usernameList;
    @FXML
    private TextField addressField, emailField, fullnameField, idField;

    @FXML
    private Text errorMessageField;

    @FXML
    final ObservableList<String> data = FXCollections.observableArrayList();

    private UserAdapter userAdapter;

    @FXML
    void cancel(ActionEvent event) {
        // Get current stage reference
        Stage stage = (Stage) addressField.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @FXML
    void save() {
        try {
            User user = new User();
            user.setId(Integer.parseInt(idField.getText()));
            user.setFullname(fullnameField.getText());
            user.setEmail(emailField.getText());
            user.setAddress(addressField.getText());
            user.setUsername(usernameList.getValue());
            userAdapter.updateUser(user);
            errorMessageField.setFill(javafx.scene.paint.Color.GREEN);
            errorMessageField.setText("User updated successfully");
            errorMessageField.setVisible(true);
            printGREEN("ModifyUser", "User updated successfully");
            data.clear();
            buildData();
            usernameList.setValue(user.getUsername());

        } catch (NumberFormatException ex) {
            errorMessageField.setFill(javafx.scene.paint.Color.RED);
            errorMessageField.setText("Please select a user to update");
            errorMessageField.setVisible(true);
            printRED("ModifyUser", "Please select a user to update");
        }
        catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    public void buildData() {
        try {
            data.addAll(userAdapter.getUsersList());
            usernameList.setItems(data);
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    public void setModel(UserAdapter users) {
        printPURPLE("ModifyUser", "SetModel");
        this.userAdapter = users;
        buildData();
        errorMessageField.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        printBLUE("ModifyUser", "Initialize");

        usernameList.setOnAction(e -> {
            try {
                User user = UserAdapter.getUserInfo(usernameList.getValue());
                idField.setText(String.valueOf(user.getId()));
                fullnameField.setText(user.getFullname());
                emailField.setText(user.getEmail());
                addressField.setText(user.getAddress());

                idField.setDisable(false);
                fullnameField.setDisable(false);
                emailField.setDisable(false);
                addressField.setDisable(false);

                idField.setEditable(true);
                fullnameField.setEditable(true);
                emailField.setEditable(true);
                addressField.setEditable(true);

            } catch (SQLException ex) {
                displayAlert("ERROR: " + ex.getMessage());
            }
        });
    }
}
