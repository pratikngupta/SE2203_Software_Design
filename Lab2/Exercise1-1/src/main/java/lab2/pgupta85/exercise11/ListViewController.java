package lab2.pgupta85.exercise11;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListViewController {

    @FXML
    private ListView<String> lvItem;

    // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        lvItem.getItems().addAll("Poodle", "Great Dane", "Labrador", "Terrier");
    }
    
}