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

public class SortingHubController{

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



    private SortingStrategy sortingStrategy;

    //create a Rectangle arrayList to store the bars
    private ArrayList<Rectangle> bars = new ArrayList<>();

    private int[] intArray;

    private int size;
    @FXML
    void ResetButtonClicked() {
        MainFrame.getChildren().clear();
    }

    //create initialize method to initialize the bars
    public void initialize() {
        SelectionMethodSelector.getItems().addAll("Merge Sort");
        ArraySizeSlider.setValue(64);
    }

    @FXML
    void SetArraySize() {
        size = (int) ArraySizeSlider.getValue();
        ArraySizeLabel.setText(size + "");
        intArray = new int[size];
        int min = 1;
        int max = size;

        int range = max - min + 1;

        //add values to the array without duplicates
        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * range) + min;
            intArray[i] = rand;
            for (int j = 0; j < i; j++) {
                if (intArray[j] == rand) {
                    i--;
                    break;
                }
            }
        }
        updateGraph();
    }


    public void updateGraph(){

        MainFrame.getChildren().clear();
        double width = (MainFrame.getPrefWidth() / intArray.length ) - 2;
        double x = 0;
        double height;
        int y = 0;
        //find max value in array

        System.out.println("Max: " + size);

        for (int i=0; i < intArray.length; i++){
            //set height of bar in proportion to max value
            height = (intArray[i] * MainFrame.getPrefHeight()) / size;

            //set the x and width of the bar such that it is evenly spaced and there is a space of 1 pixel between each bar, and it is in the prefWidth of the mainFrame
            x = ((i  * (width + 2)));
            //set width of bar such that it is 1 pixel less than the width of the frame so that there is a space of 1 pixel between each bar

            Rectangle bar = new Rectangle(x, y + (MainFrame.getHeight() - height), width, height);

            bar.setStyle("-fx-fill: #ff0000" + "; -fx-border-color: Black; -fx-border-width: 50px;");
            bars.add(bar);
            MainFrame.getChildren().add(bar);
        }
    }

    @FXML
    void SortButtonClicked() {
        //call the sort method from the SelectionSort class
        //pass the bars to the SelectionSort class
        //pass sortingHubController to the SelectionSort class
        sortingStrategy = new SelectionSort(this, intArray);

        Thread thread = new Thread(sortingStrategy);
        thread.start();

    }

    @FXML
    void setSortStrategy() {

    }

    public int[] getArray() {
        return intArray;
    }
}
