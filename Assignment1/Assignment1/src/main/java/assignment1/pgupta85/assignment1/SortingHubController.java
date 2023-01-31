package assignment1.pgupta85.assignment1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SortingHubController{

    @FXML
    private Label ArraySizeLabel;

    @FXML
    private Slider ArraySizeSlider;

    @FXML
    private Pane MainFrame;

    @FXML
    private Button ResetButton, SortButton;

    @FXML
    private ComboBox<String> SelectionMethodSelector;

    private SortingStrategy sortingStrategy;

    //create a Rectangle arrayList to store the bars
    private ArrayList<Rectangle> bars = new ArrayList<>();

    private int[] backUpArray;

    private int[] intArray;

    private int arraySize;
    @FXML
    void ResetButtonClicked() {
        MainFrame.getChildren().clear();
        updateGraph(backUpArray);
    }

    //create initialize method to initialize the bars
    public void initialize() {
        SelectionMethodSelector.getItems().addAll("Merge Sort");
        ArraySizeSlider.setValue(64);
    }

    @FXML
    void SetArraySize() {
        arraySize = (int) ArraySizeSlider.getValue();
        ArraySizeLabel.setText(arraySize + "");
        intArray = new int[arraySize];
        backUpArray = new int[arraySize];

        int min = 1;
        int max = arraySize;

        int range = max - min + 1;

        //add values to the array without duplicates
        for (int i = 0; i < arraySize; i++) {
            int rand = (int) (Math.random() * range) + min;
            intArray[i] = rand;
            for (int j = 0; j < i; j++) {
                if (intArray[j] == rand) {
                    i--;
                    break;
                }
            }
        }
        //initialize the backup array

        //copy the values from the intArray to the backup array
        System.arraycopy(intArray, 0, backUpArray, 0, arraySize);

        updateGraph(intArray);
    }


    public void updateGraph(int[] intArray) {

        MainFrame.getChildren().clear();
        double width = (MainFrame.getPrefWidth() / intArray.length ) - 2;
        double x;
        double height;
        int y;
        //find max value in array

        for (int i=0; i < intArray.length; i++){
            //set height of bar in proportion to max value
            height = (intArray[i] * MainFrame.getPrefHeight()) / arraySize;

            //set the x and width of the bar such that it is evenly spaced and there is a space of 1 pixel between each bar, and it is in the prefWidth of the mainFrame
            x = ((i  * (width + 2)));
            y = (int) (MainFrame.getHeight() - height);

            Rectangle bar = new Rectangle(x, y , width, height);
            bars.add(bar);

            bar.setStyle("-fx-fill: #ff0000" + "; -fx-border-color: Black; -fx-border-width: 50px;");

            MainFrame.getChildren().add(bar);
        }
    }

    @FXML
    void SortButtonClicked() {
        //call the sort method from the SelectionSort class
        //pass the bars to the SelectionSort class
        //pass sortingHubController to the SelectionSort class
        sortingStrategy = new BubbleSort(this, intArray);
        sortingStrategy.sort(intArray);
    }

    @FXML
    void setSortStrategy() {

    }


}
