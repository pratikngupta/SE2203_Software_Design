package lab2.pgupta85.exercise2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComboBoxController {
    @FXML
    private ComboBox selectionBox;
    @FXML
    private Label DisplayName;


    public void initialize() {
        //create a list of items for the ComboBox

        ArrayList name = new ArrayList<String>();
        name.add("Select a name");
        name.add("Will");
        name.add("Megan");
        name.add("Amanda");
        name.add("Tyler");

        //set the items to the ComboBox
        //set the default value to select a name

        selectionBox.getItems().addAll(name);

    }


    public void sayHello(ActionEvent actionEvent) {

        if (selectionBox.getValue() == "Select a name") {
            DisplayName.setText("");
        } else {
            DisplayName.setText("Hello " + selectionBox.getValue() + "!");
        }

    }
}