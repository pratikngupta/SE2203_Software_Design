package assignment1.pgupta85.assignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SortingHubController {

    @FXML
    private Label ArraySizeLabel;

    @FXML
    private Slider ArraySizeSlider;

    @FXML
    private Pane MainFrame;

    @FXML
    private Button ResetButton;

    @FXML
    private ComboBox<String> SelectionMethodSelector;

    @FXML
    private Button SortButton;

    @FXML
    private AnchorPane Stage;

    //create a Rectangle arrayList to store the bars
    private ArrayList<Rectangle> bars = new ArrayList<Rectangle>();

    private  int[] ramdomNumbersGenerator = new int[96];

    private int[] intArray;

    @FXML
    void ResetButtonClicked() {
        MainFrame.getChildren().clear();
    }

    //create initialize method to initialize the bars
    public void initialize() {
        SelectionMethodSelector.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort");
        ArraySizeSlider.setValue(64);

        int number = 32;
        for (int i = 0; i < 96; i++) {
            ramdomNumbersGenerator[i] = number;
            number ++;
        }

    }

    @FXML
    void SetArraySize() {
        int size = (int) ArraySizeSlider.getValue();
        ArraySizeLabel.setText(size + "");
        intArray = new int[size];
        //generate number between 0 and 95 inclusive
        for (int i = 0; i < size; i++) {

            //check if the number is already in the array
            int randomNumber = (int) (Math.random() * 96);
            for (int j = 0; j < i; j++) {
                if (intArray[j] == ramdomNumbersGenerator[randomNumber]) {
                    randomNumber = (int) (Math.random() * 96);
                    j = -1;
                }
            }
            intArray[i] = ramdomNumbersGenerator[randomNumber];
        }

        updateGraph();
    }

    public void updateGraph(){
        MainFrame.getChildren().clear();
        double width = (MainFrame.getPrefWidth() / intArray.length )- 2;
        double x = 0;
        double height;
        int y = 0;
        //find max value in array
        int max = intArray[0];
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > max) {
                max = intArray[i];
            }
        }

        for (int i=0; i < intArray.length; i++){
            //set the height of the bar with proportion to the value of the array with 32 being the lowest and 128 being the highest, and it should fit in mainFrame height
            //set the height in such a way that the max value is at the top of the frame and the min value is 5 pixels from the bottom of the frame also min value is 32 should be visible
            height = (intArray[i] - 31) * (MainFrame.getPrefHeight()) / (max - 31);
            //cap the height at prefHeight

            //set the x and width of the bar such that it is evenly spaced and there is a space of 1 pixel between each bar, and it is in the prefWidth of the mainFrame
            x = ((i  * (width+2)));
            //set width of bar such that it is 1 pixel less than the width of the frame so that there is a space of 1 pixel between each bar


            Rectangle bar = new Rectangle(x, y + (MainFrame.getHeight() - height), width, height);

            bar.setStyle("-fx-fill: #ff0000" + "; -fx-border-color: Black; -fx-border-width: 50px;");
            bars.add(bar);
            MainFrame.getChildren().add(bar);
        }
    }

    @FXML
    void SortButtonClicked(ActionEvent event) {

    }

    @FXML
    void setSortStrategy(ActionEvent event) {

    }

}
