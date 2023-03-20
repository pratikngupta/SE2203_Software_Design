package se2203b.assignments.ifinance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModifyUserController {


    @FXML
    private TextField addressField, emailField, fullnameField, idField;

    @FXML
    private Text errorMessageField;


    @FXML
    void cancel(ActionEvent event) {
        // Get current stage reference
        Stage stage = (Stage) addressField.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {

    }

    public void setModel(UserAdapter users) {

    }

}
