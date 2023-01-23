package lab2.pgupta85.exercise2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ComboBoxController {
    @FXML
    private ComboBox selectionBox;
    @FXML
    private Label DisplayName;


    public void initialize() {
        selectionBox.getItems().addAll("A", "B", "C", "D", "E");
    }


    public void sayHello(ActionEvent actionEvent) {
        DisplayName.setText("Hello " + selectionBox.getValue() + "!");
    }
}