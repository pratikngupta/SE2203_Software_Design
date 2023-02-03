package assignment1.pgupta85.assignment1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SortingHubController {

    @FXML
    private ProgressBar StatusBar;

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
    private final ArrayList<Rectangle> bars = new ArrayList<>();

    private int[] backUpArray;

    private int[] intArray;

    private int arraySize, arrayCounter, runNeeded;

    @FXML
    void ResetButtonClicked() {

        //hide all bars
        bars.listIterator().forEachRemaining(bar -> bar.setVisible(false));

        updateGraph(backUpArray);

        //copy backUpArray to intArray
        System.arraycopy(backUpArray, 0, intArray, 0, backUpArray.length);

    }

    //create initialize method to initialize the bars
    public void initialize() {
        SelectionMethodSelector.getItems().addAll("Merge Sort", "Selection Sort", "Bubble Sort", "InsertionSort Sort", "Quick Sort", "Heap Sort");

        StatusBar.setStyle("-fx-accent: #142174");
        StatusBar.setProgress(0);
        //create 128 bars
        for (int i = 0; i < 128; i++) {
            //create a rectangle
            Rectangle rectangle = new Rectangle();
            bars.add(rectangle);
            MainFrame.getChildren().add(bars.get(i));
            bars.get(i).setStyle("-fx-fill: #142174" + "; -fx-border-color: Black; -fx-border-width: 50px;");
        }

        //set the ar

        //set the arraySize to 64
        arraySize = 64;
        //call the fillArray method
        fillArray(arraySize);
        //call the updateGraph method
        updateGraph(intArray);
        //set slider value to 64
        ArraySizeSlider.setValue(64);

    }

    @FXML
    void SetArraySize() {
        arraySize = (int) ArraySizeSlider.getValue();
        fillArray(arraySize);
        updateGraph(intArray);
        //printGreen("User set array size to " + arraySize);
    }

    public void fillArray(int arraySize) {
        ArraySizeLabel.setText(arraySize + "");
        intArray = new int[arraySize];
        backUpArray = new int[arraySize];

        int min = 1;

        int range = arraySize - min + 1;

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

        bars.listIterator().forEachRemaining(bar -> bar.setVisible(false));

        double width = (MainFrame.getPrefWidth() / intArray.length) - 2;
        double x;
        double height;
        int y;
        //find max value in array
        for (int j = 0; j < intArray.length; j++) {
            height = (intArray[j] * MainFrame.getPrefHeight()) / arraySize;
            x = ((j * (width + 2)));
            y = (int) (MainFrame.getPrefHeight() - height);

            bars.get(j).setX(x);
            bars.get(j).setY(y);
            bars.get(j).setWidth(width);
            bars.get(j).setHeight(height);
            bars.get(j).setVisible(true);
        }
    }

    @FXML
    void SortButtonClicked() {
        StatusBar.setProgress(0);
        runNeeded = sortingStrategy.getRunNeeded(backUpArray);
        try {
            //call constructor of the sorting strategy
            sortingStrategy.SortingStrategy(intArray,this);
            //start the thread
            new Thread(sortingStrategy).start();

        } catch (Exception e) {
            SelectionMethodSelector.setStyle("-fx-border-color: red");
            //create an alert box to show the error
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
            case "InsertionSort Sort" -> sortingStrategy = new InsertionSort();
            case "Selection Sort" -> sortingStrategy = new SelectionSort();
            case "Bubble Sort" -> sortingStrategy = new BubbleSort();
            case "Merge Sort" -> sortingStrategy = new MergeSort();
            case "Quick Sort" -> sortingStrategy = new QuickSort();
            case "Heap Sort" -> sortingStrategy = new HeapSort();
        }
        SelectionMethodSelector.setStyle("-fx-border-color: green; -fx-border-radius: 5px; -fx-border-width: 2px;");
        StatusBar.setProgress(0.5);
    }

    public void setStatusBar(boolean counter) {
        System.out.println(runNeeded + "  -----  " + arrayCounter);

        if (counter) {
            arrayCounter ++;
            double progress = (double) arrayCounter / runNeeded;
            System.out.println(progress);
            StatusBar.setProgress(progress);
            //update status bar

        } else {
            arrayCounter = 0;
            StatusBar.setProgress(0);
        }

    }

}
