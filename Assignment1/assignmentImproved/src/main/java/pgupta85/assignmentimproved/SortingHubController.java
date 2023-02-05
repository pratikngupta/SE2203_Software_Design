package pgupta85.assignmentimproved;


import eu.hansolo.medusa.Gauge;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import static pgupta85.method.Debug.printSameLine;

public class SortingHubController {

    @FXML
    private Gauge ArraySizeGauge, percentageGauge;

    @FXML
    private MFXSlider ArraySizeSlider;

    @FXML
    private Label IndicatorLabel;

    @FXML
    private Pane MainFrame;

    @FXML
    private MFXButton ResetButton, SortButton;

    @FXML
    private MFXComboBox<String> SelectionMethodSelector, ColorSelector, SortSpeedSelector;

    @FXML
    private AnchorPane Stage;

    @FXML
    private MFXProgressBar StatusBar;

    @FXML
    private MFXToggleButton resetType;

    private SortingStrategy sortingStrategy;

    //create a Rectangle arrayList to store the bars
    private final ArrayList<Rectangle> bars = new ArrayList<>();

    private int[] intArray;

    private int[] dummyArray;

    private int arraySize, arrayCounter, runNeeded;

    //create sortingStrategy thread
    
}