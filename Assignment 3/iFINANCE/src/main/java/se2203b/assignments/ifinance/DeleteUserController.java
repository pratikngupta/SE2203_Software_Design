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

import static se2203b.assignments.ifinance.DisplayAlert.displayAlert;

public class DeleteUserController implements Initializable {

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
    void save(ActionEvent event) {
        try {
            userAdapter.deleteUser(usernameList.getValue());
            data.clear();
            usernameList.getItems().clear();
            addressField.clear();
            emailField.clear();
            fullnameField.clear();
            idField.clear();
            buildData();
        } catch (SQLException ex) {
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
        this.userAdapter = users;
        buildData();
        errorMessageField.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameList.setOnAction(e -> {
            try {
                User user = UserAdapter.getUserInfo(usernameList.getValue());
                idField.setText(String.valueOf(user.getId()));
                fullnameField.setText(user.getFullname());
                emailField.setText(user.getEmail());
                addressField.setText(user.getAddress());
            } catch (SQLException ex) {
                displayAlert("ERROR: " + ex.getMessage());
            }
        });
    }
}
