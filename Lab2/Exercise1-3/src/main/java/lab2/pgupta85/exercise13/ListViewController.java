/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 27 January 2023
 * Student ID: 251211859
 * Task: Exercise 1-3
 * Description: This is the controller class for the ListView Application. NO NEED to click on the BUTTON
 **********************************************************************************************************************/
package lab2.pgupta85.exercise13;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ListViewController {

    @FXML
    private Button listViewBtn;
    @FXML
    private Label selectedItem;
    @FXML
    private ListView<String> lvItem;

    // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        // Add items to the ListView
        lvItem.getItems().addAll("Poodle", "Great Dane", "Labrador", "Terrier");
        // Disable the button
        listViewBtn.setVisible(false);
    }

    @FXML
    void displaySelectedItem() {
        // Get the selected item
        selectedItem.setText(lvItem.getSelectionModel().getSelectedIndex() + "-" +
                lvItem.getSelectionModel().getSelectedItem());
    }

}