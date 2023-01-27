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
        ArrayList<String> strArrayList = new ArrayList<>();
        strArrayList.add("Poodle");
        strArrayList.add("Great Dane");
        strArrayList.add("Labrador");
        strArrayList.add("Terrier");

        ObservableList<String> strList = FXCollections.observableArrayList(strArrayList);

        lvItem.getItems().setAll(strList);
        lvItem.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void displaySelectedItem() {
        //Multiple selection
        ObservableList<String> selectedItems = lvItem.getSelectionModel().getSelectedItems();
        selectedItem.setText(selectedItems.toString());
    }

}