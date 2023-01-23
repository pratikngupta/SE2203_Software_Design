package lab2.pgupta85.exercise13;

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
        lvItem.getItems().addAll("Poodle", "Great Dane", "Labrador", "Terrier");
    }
    @FXML
    void displaySelectedItem() {
        selectedItem.setText(lvItem.getSelectionModel().getSelectedIndex() + "-" +
                lvItem.getSelectionModel().getSelectedItem());
    }

}