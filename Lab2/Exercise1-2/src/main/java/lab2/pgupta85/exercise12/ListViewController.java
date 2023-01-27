/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 27 January 2023
 * Student ID: 251211859
 * Task: Exercise 1-1
 * Description: This is the controller class for the ListView Application.
 **********************************************************************************************************************/

package lab2.pgupta85.exercise12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListViewController {

    @FXML
    private Label selectedItem;
    @FXML
    private Button listViewBtn;
    @FXML
    private ListView<String> lvItem;

    // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        // Add items to the ListView
        lvItem.getItems().addAll("Poodle", "Great Dane", "Labrador", "Terrier");
    }
    @FXML
    void displaySelectedItem() {
        // Get the selected item and display it
        // Enable index-based access to the items in the ListView
        selectedItem.setText(lvItem.getSelectionModel().getSelectedIndex() + "-" +
                lvItem.getSelectionModel().getSelectedItem());
    }

}