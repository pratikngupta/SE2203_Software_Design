/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 27 January 2023
 * Student ID: 251211859
 * Task: Exercise 1-4
 * Description: This is the Controller class for the ListView Application.
 **********************************************************************************************************************/

package lab2.pgupta85.exercise14;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class ListViewController {

    @FXML
    private javafx.scene.control.Button listViewBtn;
    @FXML
    private Label selectedItem;
    @FXML
    private ListView<String> lvItem;

    // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        // Initialize the ListView with the items
        ArrayList<String> strArrayList = new ArrayList<>();
        strArrayList.add("Poodle");
        strArrayList.add("Great Dane");
        strArrayList.add("Labrador");
        strArrayList.add("Terrier");

        // Create an ObservableList from the ArrayList
        ObservableList<String> strList = FXCollections.observableArrayList(strArrayList);

        // Set the items to the ListView
        lvItem.getItems().setAll(strList);

        // Set the selection mode to multiple
        lvItem.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void displaySelectedItem() {
        //Multiple selection
        ObservableList<String> selectedItems = lvItem.getSelectionModel().getSelectedItems();
        
        //Single selection
        selectedItem.setText(selectedItems.toString());
    }

}