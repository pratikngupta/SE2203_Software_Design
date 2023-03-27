/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 27 January 2023
 * Student ID: 251211859
 * Task: Exercise 1-3
 * Description: This is the controller class for the ComboBox Application. It contains the methods for the ComboBox
 **********************************************************************************************************************/

package lab2.pgupta85.exercise2;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Objects;

public class ComboBoxController {
    @FXML
    private ComboBox<String> selectionBox;
    @FXML
    private Label DisplayName;


    public void initialize() {
        //create a list of items for the ComboBox
        ArrayList<String> name = new ArrayList<String>();
        name.add("Select a name");
        name.add("Will");
        name.add("Megan");
        name.add("Amanda");
        name.add("Tyler");

        //set the items to the ComboBox
        selectionBox.getItems().addAll(name);
    }

    public void sayHello () {
        if (Objects.equals(selectionBox.getValue(), "Select a name")) {
            DisplayName.setText("");
        } else {
            DisplayName.setText(selectionBox.getValue() + "!");
        }
    }
}