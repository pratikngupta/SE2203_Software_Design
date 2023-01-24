package lab2.pgupta85.exercise3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class SliderControllerImproved {

    @FXML
    private AnchorPane AboutMePlane;
    @FXML
    private Rectangle BandOneColor;
    @FXML
    private ComboBox<String> BandOneSelector;
    @FXML
    private Rectangle BandTwoColor;
    @FXML
    private ComboBox<String> BandTwoSelector;
    @FXML
    private Label BaseUnit;
    @FXML
    private Label BaseUnitBValue;
    @FXML
    private Button CalculateButton;
    @FXML
    private RadioButton CelsiusRadioButton;
    @FXML
    private RadioButton FahrenheitRadioButton;
    @FXML
    private RadioButton KelvinRadioButton;
    @FXML
    private Tab ResistorTab;
    @FXML
    private AnchorPane MetricTabPlane;
    @FXML
    private Rectangle MultiplerColor;
    @FXML
    private ComboBox<String> MultiplerSelector;
    @FXML
    private ImageView ResistorImage;
    @FXML
    private Label SecondUnit;
    @FXML
    private Label SecondUnitValue;
    @FXML
    private ToggleGroup TempSelector;
    @FXML
    private Slider TemperatureSlider;
    @FXML
    private Tab TemperatureTab;
    @FXML
    private AnchorPane TemperatureTabPlane;
    @FXML
    private Label ThirdUnit;
    @FXML
    private Label ThirdUnitValue;
    @FXML
    private Rectangle ToleranceColor;
    @FXML
    private ComboBox<String> ToleranceSelector;
    @FXML
    private Tab WelcomeTab;
    @FXML
    private AnchorPane WelcomeTabPlane;
    @FXML
    private ImageView aboutMeImage;
    @FXML
    private Tab aboutMeTab;
    @FXML
    private Label ValuePartOne;
    @FXML
    private Label ValuePartTwo;

    private double celsius;
    private double fahrenheit;
    private double kelvin;
    private double sliderValue;
    private double multiplier;
    private double tolerance;
    private double bandOne;
    private double bandTwo;
    private String color;

    //create a hashmap for the color bands
    //create a hashmap for the multipliers
    //create a hashmap for the tolerances

    HashMap <String, Double> colorBand = new HashMap<>();
    HashMap <String, Double> multiplierBand = new HashMap<>();
    HashMap <String, Double> toleranceBand = new HashMap<>();

    public void initialize(){
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);

        WelcomeTabPlane.setStyle("-fx-background-color: rgba(0,57,190,0.67)");

        //add the color bands to the hashmap
        colorBand.put("Black", 0.0);
        colorBand.put("Brown", 1.0);
        colorBand.put("Red", 2.0);
        colorBand.put("Orange", 3.0);
        colorBand.put("Yellow", 4.0);
        colorBand.put("Green", 5.0);
        colorBand.put("Blue", 6.0);
        colorBand.put("Violet", 7.0);
        colorBand.put("Gray", 8.0);
        colorBand.put("White", 9.0);

        //add the multipliers to the hashmap
        multiplierBand.put("Black", 1.0);
        multiplierBand.put("Brown", 10.0);
        multiplierBand.put("Red", 100.0);
        multiplierBand.put("Orange", 1000.0);
        multiplierBand.put("Yellow", 10000.0);
        multiplierBand.put("Green", 100000.0);
        multiplierBand.put("Blue", 1000000.0);
        multiplierBand.put("Violet", 10000000.0);
        multiplierBand.put("Gray", 100000000.0);
        multiplierBand.put("White", 1000000000.0);
        multiplierBand.put("Gold", 0.1);
        multiplierBand.put("Silver", 0.01);

        //add the tolerances to the hashmap
        toleranceBand.put("Brown", 1.0);
        toleranceBand.put("Red", 2.0);
        toleranceBand.put("Green", 0.5);
        toleranceBand.put("Blue", 0.25);
        toleranceBand.put("Violet", 0.1);
        toleranceBand.put("Gray", 0.05);
        toleranceBand.put("Gold", 5.0);
        toleranceBand.put("Silver", 10.0);
    }

    public void WelcomeTabClicked( ) {
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);
    }

    public void TemperatureTabClicked( ) {
        TemperatureTab.isSelected();
        TemperatureTab.setClosable(false);

        BaseUnit.setText("Celsius");
        BaseUnitBValue.setStyle("-fx-border-color: black");
        BaseUnitBValue.setAlignment(javafx.geometry.Pos.CENTER);
        BaseUnitBValue.setMinWidth(50);
        BaseUnitBValue.setText("0");

        SecondUnit.setText("Fahrenheit");
        SecondUnitValue.setStyle("-fx-border-color: black");
        SecondUnitValue.setAlignment(javafx.geometry.Pos.CENTER);
        SecondUnitValue.setMinWidth(50);
        SecondUnitValue.setText("32");

        ThirdUnit.setText("Kelvin");
        ThirdUnitValue.setStyle("-fx-border-color: black");
        ThirdUnitValue.setAlignment(javafx.geometry.Pos.CENTER);
        ThirdUnitValue.setMinWidth(50);
        ThirdUnitValue.setText("273.15");
    }

    public void CelsiusRadioButtonClicked( ) {
        BaseUnit.setText("Celsius");
        SecondUnit.setText("Fahrenheit");
        ThirdUnit.setText("Kelvin");
        TempConverter();
    }

    public void FahrenheitRadioButtonClicked() {
        BaseUnit.setText("Fahrenheit");
        SecondUnit.setText("Celsius");
        ThirdUnit.setText("Kelvin");
        TempConverter();
    }

    public void KelvinRadioButtonClicked() {
        BaseUnit.setText("Kelvin");
        SecondUnit.setText("Celsius");
        ThirdUnit.setText("Fahrenheit");
        TempConverter();
    }

    public void TemperatureSlider( ) {
        sliderValue = TemperatureSlider.getValue();
        TempConverter();
    }

    public void TempConverter (){

        if (CelsiusRadioButton.isSelected()){
            celsius = sliderValue;
            fahrenheit = (celsius * 9 / 5) + 32;
            kelvin = celsius + 273.15;

            //set value with 2 decimal places
            BaseUnitBValue.setText(String.format("%.2f", celsius));
            SecondUnitValue.setText(String.format("%.2f", fahrenheit));
            ThirdUnitValue.setText(String.format("%.2f", kelvin));
        }

        else if (FahrenheitRadioButton.isSelected()){
            fahrenheit = sliderValue;
            celsius = (fahrenheit - 32) * 5 / 9;
            kelvin = celsius + 273.15;

            //set value with 2 decimal places
            BaseUnitBValue.setText(String.format("%.2f", fahrenheit));
            SecondUnitValue.setText(String.format("%.2f", celsius));
            ThirdUnitValue.setText(String.format("%.2f", kelvin));
        }

        else if (KelvinRadioButton.isSelected()){
            kelvin = sliderValue;
            celsius = kelvin - 273.15;
            fahrenheit = (celsius * 9 / 5) + 32;

            //set value with 2 decimal places
            BaseUnitBValue.setText(String.format("%.2f", kelvin));
            SecondUnitValue.setText(String.format("%.2f", celsius));
            ThirdUnitValue.setText(String.format("%.2f", fahrenheit));
        }
    }

    public void aboutMeTabClicked( ) {
        aboutMeTab.isSelected();
        aboutMeTab.setClosable(false);

        AboutMePlane.setStyle("-fx-background-color: #824d4d");

    }

    public void CalculateResistance() {

        //get the value of the color
        bandOne = colorBand.get(BandOneSelector.getValue());
        bandTwo = colorBand.get(BandTwoSelector.getValue());
        multiplier = multiplierBand.get(MultiplerSelector.getValue());
        tolerance = toleranceBand.get(ToleranceSelector.getValue());

        //calculate the resistance
        double resistance = (bandOne * 10 + bandTwo) * multiplier;

        //convert the resistance to the correct unit
        if (resistance < 1000) {
            ValuePartOne.setText(String.format("%.0f", resistance) + " Ω");
        } else if (resistance >= 1000 && resistance < 1000000) {
            ValuePartOne.setText(String.format("%.0f", resistance / 1000) + " kΩ");
        } else if (resistance >= 1000000 && resistance < 1000000000) {
            ValuePartOne.setText(String.format("%.0f", resistance / 1000000) + " MΩ");
        } else if (resistance >= 1000000000) {
            ValuePartOne.setText(String.format("%.0f", resistance / 1000000000) + " GΩ");
        }
        ValuePartTwo.setText(String.format("%.2f", tolerance));

    }

    public void ResistorTabClicked( ) {
        ResistorTab.isSelected();
        ResistorTab.setClosable(false);
        BandOneColor.setVisible(false);
        BandTwoColor.setVisible(false);
        MultiplerColor.setVisible(false);
        ToleranceColor.setVisible(false);
        ResistorImage.setImage(new Image("file:src/main/resources/lab2/pgupta85/exercise3/resistor.png"));
        BandOneSelector.getItems().addAll(colorBand.keySet());
        BandTwoSelector.getItems().addAll(colorBand.keySet());
        MultiplerSelector.getItems().addAll(multiplierBand.keySet());
        ToleranceSelector.getItems().addAll(toleranceBand.keySet());

        //edit text color for comboBox
        BandOneSelector.setStyle("-fx-text-fill: White");

    }

    public void DropDownMenu() {
        if (BandOneSelector.isShowing()){
            setRectangleColor(1, BandOneSelector.getValue());
        }

        if (BandTwoSelector.isShowing()){
            setRectangleColor(2, BandTwoSelector.getValue());
        }

        if (MultiplerSelector.isShowing()){
            setRectangleColor(3, MultiplerSelector.getValue());
        }

        if (ToleranceSelector.isShowing()){
            setRectangleColor(4, ToleranceSelector.getValue());
        }
    }

    public void setRectangleColor(int caseNumber, String color) {
        if (caseNumber == 1) {
            BandOneColor.setVisible(true);
            //compare 2 string
            if (color.equals("Black")){
                BandOneColor.setFill(Color.BLACK);
            }
            if (color.equals("Brown")){
                BandOneColor.setFill(Color.BROWN);
            }
            if (color.equals("Red")){
                BandOneColor.setFill(Color.RED);
            }
            if (color.equals("Orange")){
                BandOneColor.setFill(Color.ORANGE);
            }
            if (color.equals("Yellow")){
                BandOneColor.setFill(Color.YELLOW);
            }
            if (color.equals("Green")){
                BandOneColor.setFill(Color.GREEN);
            }
            if (color.equals("Blue")){
                BandOneColor.setFill(Color.BLUE);
            }
            if (color.equals("Violet")){
                BandOneColor.setFill(Color.VIOLET);
            }
            if (color.equals("Gray")){
                BandOneColor.setFill(Color.GRAY);
            }
            if (color.equals("White")){
                BandOneColor.setFill(Color.WHITE);
            }

        }
        if (caseNumber == 2) {
            BandTwoColor.setVisible(true);
            //compare 2 string
            if (color.equals("Black")){
                BandTwoColor.setFill(Color.BLACK);
            }
            if (color.equals("Brown")){
                BandTwoColor.setFill(Color.BROWN);
            }
            if (color.equals("Red")){
                BandTwoColor.setFill(Color.RED);
            }
            if (color.equals("Orange")){
                BandTwoColor.setFill(Color.ORANGE);
            }
            if (color.equals("Yellow")){
                BandTwoColor.setFill(Color.YELLOW);
            }
            if (color.equals("Green")){
                BandTwoColor.setFill(Color.GREEN);
            }
            if (color.equals("Blue")){
                BandTwoColor.setFill(Color.BLUE);
            }
            if (color.equals("Violet")){
                BandTwoColor.setFill(Color.VIOLET);
            }
            if (color.equals("Gray")){
                BandTwoColor.setFill(Color.GRAY);
            }
            if (color.equals("White")){
                BandTwoColor.setFill(Color.WHITE);
            }

        }
        if (caseNumber == 3) {
            MultiplerColor.setVisible(true);
            MultiplerSelector.setStyle("-fx-background-color: " + color);
            //compare 2 string
            if (color.equals("Black")){
                MultiplerColor.setFill(Color.BLACK);
            }
            if (color.equals("Brown")){
                MultiplerColor.setFill(Color.BROWN);
            }
            if (color.equals("Red")){
                MultiplerColor.setFill(Color.RED);
            }
            if (color.equals("Orange")){
                MultiplerColor.setFill(Color.ORANGE);
            }
            if (color.equals("Yellow")){
                MultiplerColor.setFill(Color.YELLOW);
            }
            if (color.equals("Green")){
                MultiplerColor.setFill(Color.GREEN);
            }
            if (color.equals("Blue")){
                MultiplerColor.setFill(Color.BLUE);
            }
            if (color.equals("Violet")){
                MultiplerColor.setFill(Color.VIOLET);
            }
            if (color.equals("Gray")){
                MultiplerColor.setFill(Color.GRAY);
            }
            if (color.equals("White")){
                MultiplerColor.setFill(Color.WHITE);
            }

        }
        if (caseNumber == 4) {
            ToleranceColor.setVisible(true);
            ToleranceSelector.setStyle("-fx-background-color: " + color);
            //compare 2 string
            if (color.equals("Black")){
                ToleranceColor.setFill(Color.BLACK);
            }
            if (color.equals("Brown")){
                ToleranceColor.setFill(Color.BROWN);
            }
            if (color.equals("Red")){
                ToleranceColor.setFill(Color.RED);
            }
            if (color.equals("Orange")){
                ToleranceColor.setFill(Color.ORANGE);
            }
            if (color.equals("Yellow")){
                ToleranceColor.setFill(Color.YELLOW);
            }
            if (color.equals("Green")){
                ToleranceColor.setFill(Color.GREEN);
            }
            if (color.equals("Blue")){
                ToleranceColor.setFill(Color.BLUE);
            }
            if (color.equals("Violet")){
                ToleranceColor.setFill(Color.VIOLET);
            }
            if (color.equals("Gray")){
                ToleranceColor.setFill(Color.GRAY);
            }
            if (color.equals("White")){
                ToleranceColor.setFill(Color.WHITE);
            }

        }
    }

}