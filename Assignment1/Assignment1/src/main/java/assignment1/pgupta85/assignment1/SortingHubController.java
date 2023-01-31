package assignment1.pgupta85.assignment1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class SortingHubController {

    @FXML
    private AnchorPane Stage;

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

        //copy backUpArray to intArray
        System.arraycopy(backUpArray, 0, intArray, 0, backUpArray.length);
    }

    //create initialize method to initialize the bars
    public void initialize() {
        SelectionMethodSelector.getItems().addAll("Merge Sort", "Selection Sort", "Bubble Sort", "InsertionSort Sort", "Quick Sort", "Heap Sort");
        //create 128 bars
        for (int i = 0; i < 128; i++) {
            //create a rectangle
            Rectangle rectangle = new Rectangle();
            bars.add(rectangle);
        }

        //change direction of combobox menu
        SelectionMethodSelector.setEffect( new javafx.scene.effect.DropShadow());
    }

    @FXML
    void SetArraySize() {
        arraySize = (int) ArraySizeSlider.getValue();
        System.out.println("set array size to " + arraySize);
        fillArray(arraySize);
        System.out.println("update graph");
        updateGraph(intArray);
    }

    public void fillArray (int arraySize) {
        System.out.println("fill array");
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
    }

    public void updateGraph(int[] intArray) {

        MainFrame.getChildren().clear();
        double width = (MainFrame.getPrefWidth() / intArray.length ) - 2;
        double x;
        double height;
        int y;
        //find max value in array
        for (int j = 0; j < intArray.length; j++) {
            height = (intArray[j] * MainFrame.getPrefHeight()) / arraySize;
            x = ((j  * (width + 2)));
            y = (int) (MainFrame.getHeight() - height);

            bars.get(j).setHeight(height);
            bars.get(j).setWidth(width);
            bars.get(j).setY(y);
            bars.get(j).setX(x);

            bars.get(j).setStyle("-fx-fill: #142174" + "; -fx-border-color: Black; -fx-border-width: 50px;");

            MainFrame.getChildren().add(bars.get(j));
        }
    }

    public void rainbow(int [] intArray){
        for (int j = 0; j < intArray.length; j++) {
            bars.get(j).setStyle("-fx-fill: #6200f4" + "; -fx-border-color: Black; -fx-border-width: 50px;");
        }
    }

    @FXML
    void SortButtonClicked() {
        try {
            SelectionMethodSelector.setStyle("-fx-border-color: green");
            sortingStrategy.sort(intArray);
            rainbow(intArray);
        }
        catch (Exception e){
            SelectionMethodSelector.setStyle("-fx-border-color: red");
            //create a alert box to show the error
            //set custom title
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to sort");
            alert.setContentText("Please select a sorting method");
            alert.showAndWait();
            //open selection method selector
            SelectionMethodSelector.show();
        }
    }

    @FXML
    void setSortStrategy() {

            String sortStrategy = SelectionMethodSelector.getValue();

            switch (sortStrategy) {
                case "InsertionSort Sort" -> sortingStrategy = new InsertionSort(this, intArray);
                case "Selection Sort" -> sortingStrategy = new SelectionSort(this, intArray);
                case "Bubble Sort" -> sortingStrategy = new BubbleSort(this, intArray);
                case "Merge Sort" -> sortingStrategy = new MergeSort(this, intArray);
                case "Quick Sort" -> sortingStrategy = new QuickSort(this, intArray);
                case "Heap Sort" -> sortingStrategy = new HeapSort(this, intArray);
            }


    }
}
