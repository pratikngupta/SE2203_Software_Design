package assignment1.pgupta85.assignment1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

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
        ArraySizeSlider.setValue(64);
        //create 128 bars
        for (int i = 0; i < 128; i++) {
            //create a rectangle
            Rectangle rectangle = new Rectangle();
            bars.add(rectangle);
        }
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
        //call the sort method from the SelectionSort class
        //pass the bars to the SelectionSort class
        //pass sortingHubController to the SelectionSort class

        sortingStrategy.sort(intArray);
        rainbow(intArray);
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
