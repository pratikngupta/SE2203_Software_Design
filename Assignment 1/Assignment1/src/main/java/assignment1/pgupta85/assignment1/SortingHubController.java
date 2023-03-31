/**************************************************************************************************************
 Name: Pratik Narendra Gupta
 Student ID: 251211859
 Date: 9th February
 Task: Create a controller class for SortingHub.fxml
    What will this class do?
    1) This will be the controller class for SortingHub.fxml
    2) This will handle all the events and actions performed by the user
    3) This will call the SortingStrategy class and pass the array to it for sorting
    4) This will also call the Debug class to print the array
    5) This will also call the UpdateGraph class to update the graph
 *****************************************************************************************************************/

package assignment1.pgupta85.assignment1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static assignment1.pgupta85.method.Debug.printGREEN;
import static assignment1.pgupta85.method.Debug.printSameLine;

public class SortingHubController {

    @FXML
    private Label IndicatorLabel, ArraySizeLabel;

    @FXML
    private ProgressBar StatusBar;

    @FXML
    private Slider ArraySizeSlider;

    @FXML
    private Pane rectanglePane;

    @FXML
    private Button ResetButton, SortButton;

    @FXML
    private ComboBox<String> SelectionMethodSelector;

    private SortingStrategy sortingMethod;

    //create a Rectangle arrayList to store the bars
    private final ArrayList<Rectangle> bars = new ArrayList<>();

    private int[] intArray;

    private int[] dummyArray;

    private int arraySize, arrayCounter, runNeeded;

    //create initialize method to initialize the bars
    public void initialize() {
        SelectionMethodSelector.getItems().addAll("Merge Sort", "Selection Sort", "Bubble Sort", "Insertion Sort", "Quick Sort", "Heap Sort");

        rectanglePane.setStyle("-fx-background-color: rgb(229,229,229); -fx-border-color: rgb(0,0,0)");

        StatusBar.setProgress(0);
        StatusBar.setStyle("-fx-accent: rgb(236,40,3)");
        IndicatorLabel.setVisible(false);

        SelectionMethodSelector.setValue("Merge Sort");
        setSortStrategy();

        //create 128 bars
        for (int i = 0; i < 128; i++) {
            //create a rectangle
            Rectangle rectangle = new Rectangle();
            bars.add(rectangle);
        }

        bars.listIterator().forEachRemaining(bar -> bar.setStyle("-fx-fill: rgb(236,40,3)"));
        bars.listIterator().forEachRemaining(bar -> rectanglePane.getChildren().add(bar));

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
        //use javaFX to check if the slider value has changed
        if (arraySize != (int) ArraySizeSlider.getValue()) {
            //set the arraySize to the value of the slider
            arraySize = (int) ArraySizeSlider.getValue();
            //call the fillArray method
            fillArray(arraySize);
            //call the updateGraph method
            updateGraph(intArray);
            StatusBar.setProgress(0);
            IndicatorLabel.setVisible(false);
        }
    }

    public void fillArray(int arraySize) {
        //set the arraySizeLabel to the value of the arraySize
        ArraySizeLabel.setText(arraySize + "");

        //create two arrays
        intArray = new int[arraySize];
        dummyArray = new int[arraySize];

        //add values to the array without duplicates
        for (int i = 0; i < arraySize; i++) {
            //generate a random number between 1 and arraySize
            int rand = (int) (Math.random() * arraySize) + 1;
            //add the random number to the array
            intArray[i] = rand;

            //check if the random number is already in the array
            for (int j = 0; j < i; j++) {
                //if the random number is already in the array, then loop again
                if (intArray[j] == rand) {
                    //decrement i so that the same index is filled again
                    i--;
                    break;
                }
            }
        }

        //copy the values from the intArray to the Dummy array
        System.arraycopy(intArray, 0, dummyArray, 0, arraySize);
    }

    public void updateGraph(int[] data) {
        //set all bars to invisible
        bars.listIterator().forEachRemaining(bar -> bar.setVisible(false));

        //set the width of the bars in such a way that there is a gap of 1 pixel between each bar and gap at end and    start
        double width = ((rectanglePane.getPrefWidth()) / data.length) - 2;
        //set the height of the bars
        double height;

        //set the x and y coordinates of the bars
        double x;
        double y;
        //find max value in array
        for (int j = 0; j < data.length; j++) {
            height = (data[j] * rectanglePane.getPrefHeight()) / arraySize;
            x = ((j * (width + 2)));
            y = (int) (rectanglePane.getPrefHeight() - height);

            //set everything in 1 line
            bars.get(j).setX(x);           //set x
            bars.get(j).setY(y);           //set y
            bars.get(j).setWidth(width);   //set width
            bars.get(j).setHeight(height); //set height
            bars.get(j).setVisible(true);  //set visible
        }
    }

    @FXML
    void SortButtonClicked() {

        //sort the array
        //using try catch to catch the exception if the user doesn't select a sorting method
        try {
            //call constructor of the sorting strategy
            StatusBar.setProgress(0);
            arrayCounter = 0;

            //call the getRunNeeded method to get the number of runs needed
            runNeeded = sortingMethod.getRunNeeded(dummyArray);

            //call the sort method
            sortingMethod.setValue(intArray,this);

            //start the thread
            new Thread(sortingMethod).start();

            //set indicator label to green
            IndicatorLabel.setStyle("-fx-text-fill: green");

        } catch (NullPointerException e) {
            //if the user doesn't select a sorting method
            //set the border color of the selection method selector to red
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
    void sortSelector() {

        setSortStrategy();
    }

    public void setSortStrategy() {

        //set sortStrategy to the value of the selection method selector
        String sortStrategy = SelectionMethodSelector.getValue();

        //using switch case to set the sorting method
        switch (sortStrategy) {

            //Merge and Selection sort is my first preference: rest were created as a side project

            case "Insertion Sort" -> sortingMethod = new InsertionSort();
            case "Selection Sort" -> sortingMethod = new SelectionSort();
            case "Bubble Sort" -> sortingMethod = new BubbleSort();
            case "Merge Sort" -> sortingMethod = new MergeSort();
            case "Quick Sort" -> sortingMethod = new QuickSort();
            case "Heap Sort" -> sortingMethod = new HeapSort();
        }

        //set the border color of the selection method selector to green
        SelectionMethodSelector.setStyle("-fx-border-color: green; -fx-border-radius: 5px; -fx-border-width: 2px;");
    }

    @FXML
    void ResetButtonClicked() {
        //hide all bars
        bars.listIterator().forEachRemaining(bar -> bar.setVisible(false));
        //set the arraySize to 64
        arraySize = 64;
        //call the fillArray method
        fillArray(arraySize);
        //call the updateGraph method
        updateGraph(intArray);
        //set slider value to 64
        ArraySizeSlider.setValue(64);
        //set the progress bar to 0
        StatusBar.setProgress(0);
        //hide the indicator label
        IndicatorLabel.setVisible(false);
        //set combo box to merge sort
        SelectionMethodSelector.setValue("Merge Sort");
    }

    public void setStatusBar(boolean counter) {
        //method to set the progress bar
        IndicatorLabel.setVisible(true); //show the indicator label
        if (counter) { //if counter is true

            //increment the arrayCounter
            arrayCounter ++;
            double progress = (double) arrayCounter / runNeeded;

            if (arrayCounter % 100 == 0 | arrayCounter == runNeeded | arrayCounter == 1 && runNeeded>150) {
                String text = "\b\b Total run: "+runNeeded + "  -----  " + "Run Completed: "+ arrayCounter + "  -----  " + String.format("Percentage: %.2f", progress * 100) + "%";
                printSameLine(text, "DEBUG: Progress Bar ---> ");
            }
            if (runNeeded < 150 && arrayCounter % 10 == 0 | arrayCounter == runNeeded | arrayCounter == 1) {
                String text = "Total run: " + runNeeded + "  -----  " + "Run Completed: " + arrayCounter + "  -----  " + String.format("Percentage: %.2f", progress * 100) + "%";
                printSameLine(text, "DEBUG: Progress Bar ---> ");
            }

            //format the progress to 2 decimal places
            IndicatorLabel.setText(String.format("%.0f", progress * 100) + "%");

            //set the progress of the status bar
            StatusBar.setProgress(progress);

        } else { //if counter is false
            arrayCounter = 0;
            StatusBar.setProgress(0);
        }
    }

    public void disableButtons(boolean disable) {
        //method to disable buttons/enabled buttons depending on the value of the disable variable
        SortButton.setDisable(disable);
        ResetButton.setDisable(disable);
        ArraySizeSlider.setDisable(disable);
    }

    //if the user clicks on the array size slider
    public void onClicked( ) {
        arraySize = 0; //set arraySize to 0
        printGREEN("Array Size: "  + arraySize, "DEBUG: Array Size Slider Clicked---> "); //print the array size
        SetArraySize(); //call the setSortStrategy method
    }
}
