/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 27 January 2023
 * Student ID: 251211859
 * Task: Exercise 3
 * Description: This is the controller class for the improved Slider Application. It contains the methods for the buttons.
 * What can this do?
 * This application can convert the temperature from Celsius to Fahrenheit to Kelvin and vice versa.
 * Able to calculate resistance of a resistor with 3,4, and 5 bands.
 **********************************************************************************************************************/

package lab2.pgupta85.exercise3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.HashMap;

public class SliderControllerImproved {

    //Declaring the variables for resistance calculation
    @FXML
    private Text ColorOneWarning, ColorTwoWarning,ColorFourWarning, ColorThreeWarning,ColorFiveWarning;
    @FXML
    private Label BandOneLabel, BandTwoLabel, BandThreeLabel, MultiplierLabel, ToleranceLabel;
    @FXML
    private Label BandOneImageLabel, BandTwoImageLabel, BandThreeImageLabel, MultiplierImageLabel, ToleranceImageLabel;
    @FXML
    private Label ValuePartOne, ValuePartTwo, ResultLabel, ResistorQuestion;
    @FXML
    private Rectangle BandOneColor, BandTwoColor, BandThreeColor ,ToleranceColor, MultiplierColor;
    @FXML
    private ComboBox<String> BandOneSelector, BandTwoSelector, BandThreeSelector, MultiplierSelector, ToleranceSelector;
    @FXML
    private ComboBox<Integer> NumberOfBand;
    @FXML
    private Line BandOneLine, BandTwoLine, BandThreeLine, MultiplierLine, ToleranceLine;
    @FXML
    private Button CalculateButton;
    @FXML
    private ImageView ResistorImage;


    //Declaring the variables for temperature
    @FXML
    private Label BaseUnit, BaseUnitBValue, SecondUnit, SecondUnitValue, ThirdUnit, ThirdUnitValue;
    @FXML
    private RadioButton CelsiusRadioButton, FahrenheitRadioButton, KelvinRadioButton;
    @FXML
    private ToggleGroup TempSelector;
    @FXML
    private Slider TemperatureSlider;

    //Declaring the common variables
    @FXML
    private AnchorPane AboutMePlane, ResistorPane, WelcomeTabPlane, TemperatureTabPlane;
    @FXML
    private Tab ResistorTab, TemperatureTab, AboutMeTab, WelcomeTab;
    @FXML
    private ImageView aboutMeImage;

    //Private variables for the resistor
    private double sliderValue;
    private double tolerance;
    private double resistance;
    private int band;

    //HashMap for the color code
    HashMap<String, Double> colorBand = new HashMap<>();
    HashMap<String, Double> multiplierBand = new HashMap<>();
    HashMap<String, Double> toleranceBand = new HashMap<>();

    //Initializing method for
    public void initialize() {

        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false); //Disabling the close button
        //noinspection CssUnknownTarget
        WelcomeTabPlane.setStyle("-fx-background-image: url('file:src/main/resources/lab2/pgupta85/exercise3/Science.png')");

        hideAll(-1, false);

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

    //Welcome tab
    public void WelcomeTabClicked() {
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);
    }

    //Temperature tab
    public void TemperatureTabClicked() {
        TemperatureTab.isSelected();
        TemperatureTab.setClosable(false); //This is to make the tab not closable

        //Setting the default values
        defaultOnLoad(BaseUnit, BaseUnitBValue, "Celsius", "0");
        defaultOnLoad(SecondUnit, SecondUnitValue, "Fahrenheit", "32");
        defaultOnLoad(ThirdUnit, ThirdUnitValue, "Kelvin", "273.15");
    }

    // method to set the default values for the radio buttons
    public void defaultOnLoad(Label unit, Label unitValue, String text, String value) {
        unit.setText(text);
        unitValue.setStyle("-fx-border-color: black");
        unitValue.setAlignment(javafx.geometry.Pos.CENTER);
        unitValue.setMinWidth(50);
        unitValue.setText(value);
    }

    //Action method for radio buttons
    public void CelsiusRadioButtonClicked() {
        TempButtonHelper("Celsius", "Fahrenheit", "Kelvin");
    }

    //Action method for radio buttons
    public void FahrenheitRadioButtonClicked() {
        TempButtonHelper("Fahrenheit", "Celsius", "Kelvin");
    }

    //Action method for radio buttons
    public void KelvinRadioButtonClicked() {
        TempButtonHelper("Kelvin", "Celsius", "Fahrenheit");
    }

    public void TempButtonHelper(String baseUnitText, String secondUnitText, String thirdUnitText) {
        BaseUnit.setText(baseUnitText);
        SecondUnit.setText(secondUnitText);
        ThirdUnit.setText(thirdUnitText);
        TempConverter();
    }

    //Action method for slider
    public void TemperatureSlider() {
        sliderValue = TemperatureSlider.getValue();
        TempConverter();
    }

    //Method to convert the temperature
    public void TempConverter() {
        //Some variables
        double celsius;
        double fahrenheit;
        double kelvin;

        //Converting the temperature
        if (CelsiusRadioButton.isSelected()) {
            celsius = sliderValue;
            fahrenheit = (celsius * 9 / 5) + 32;
            kelvin = celsius + 273.15;
            //set value with 2 decimal places
            BaseUnitBValue.setText(String.format("%.2f", celsius));
            SecondUnitValue.setText(String.format("%.2f", fahrenheit));
            ThirdUnitValue.setText(String.format("%.2f", kelvin));
        } else if (FahrenheitRadioButton.isSelected()) {
            fahrenheit = sliderValue;
            celsius = (fahrenheit - 32) * 5 / 9;
            kelvin = celsius + 273.15;
            //set value with 2 decimal places
            BaseUnitBValue.setText(String.format("%.2f", fahrenheit));
            SecondUnitValue.setText(String.format("%.2f", celsius));
            ThirdUnitValue.setText(String.format("%.2f", kelvin));
        } else if (KelvinRadioButton.isSelected()) {
            kelvin = sliderValue;
            celsius = kelvin - 273.15;
            fahrenheit = (celsius * 9 / 5) + 32;
            //set value with 2 decimal places
            BaseUnitBValue.setText(String.format("%.2f", kelvin));
            SecondUnitValue.setText(String.format("%.2f", celsius));
            ThirdUnitValue.setText(String.format("%.2f", fahrenheit));
        }
    }

    //About tab
    public void aboutMeTabClicked() {
        AboutMeTab.isSelected();
        AboutMeTab.setClosable(false);
        //noinspection CssUnknownTarget
        AboutMePlane.setStyle("-fx-background-image: url('file:src/main/resources/lab2/pgupta85/exercise3/aboutMe.jpg')");
        aboutMeImage.setImage(new Image("file:src/main/resources/lab2/pgupta85/exercise3/WesternLogo.png"));
    }

    //Calculator for the resistor
    public void CalculateResistance() {
        //Some variables
        band = NumberOfBand.getValue();
        resistance = 0;
        double bandOneValue;
        double bandTwoValue ;
        double multiplier;
        //Getting the values from the hashmap
        try {
            bandOneValue = colorBand.get(BandOneSelector.getValue());
            bandTwoValue = colorBand.get(BandTwoSelector.getValue());
            multiplier = multiplierBand.get(MultiplierSelector.getValue());
            if (band == 3 || band == 4) {
                //calculate the resistance using 3 bands
                resistance = (bandOneValue * 10 + bandTwoValue) * multiplier;
            }
            if (band == 4 || band == 5){
                tolerance = toleranceBand.get(ToleranceSelector.getValue());
            }
            if (band == 5){
                double bandThreeValue = colorBand.get(BandThreeSelector.getValue());
                resistance = (bandOneValue * 100 + bandTwoValue * 10 + bandThreeValue) * multiplier;
            }
            displayResult();
        }
        //catch the exception if the user does not select any value
        catch (NullPointerException e) {

            if (String.valueOf(BandOneSelector.getValue()).equals("null")){
                ColorOneWarning.setVisible(true);
                BandOneSelector.setStyle("-fx-border-color: red");
            }
            if (String.valueOf(BandTwoSelector.getValue()).equals("null")){
                ColorTwoWarning.setVisible(true);
                BandTwoSelector.setStyle("-fx-border-color: red");
            }

            switch (band) {
                case 3 -> {
                    if (String.valueOf(MultiplierSelector.getValue()).equals("null")) {
                        ColorThreeWarning.setVisible(true);
                        MultiplierSelector.setStyle("-fx-border-color: red");
                    }
                }
                case 4 -> {
                    if (String.valueOf(MultiplierSelector.getValue()).equals("null")) {
                        ColorThreeWarning.setVisible(true);
                        MultiplierSelector.setStyle("-fx-border-color: red");
                    }
                    if (String.valueOf(ToleranceSelector.getValue()).equals("null")) {
                        ColorFourWarning.setVisible(true);
                        ToleranceSelector.setStyle("-fx-border-color: red");
                    }
                }
                case 5 -> {
                    if (String.valueOf(BandThreeSelector.getValue()).equals("null")) {
                        ColorThreeWarning.setVisible(true);
                        BandThreeSelector.setStyle("-fx-border-color: red");
                    }
                    if (String.valueOf(MultiplierSelector.getValue()).equals("null")) {
                        ColorFourWarning.setVisible(true);
                        MultiplierSelector.setStyle("-fx-border-color: red");
                    }
                    if (String.valueOf(ToleranceSelector.getValue()).equals("null")) {
                        ColorFiveWarning.setVisible(true);
                        ToleranceSelector.setStyle("-fx-border-color: red");
                    }
                }
            }
        }
    }

    //Method to display the result
    public void displayResult(){
        if (resistance < 1000){
            ValuePartOne.setText(String.format("%.2f", resistance) + " Ω");
        }
        else if (resistance >= 1000 && resistance < 1000000){
            ValuePartOne.setText(String.format("%.2f", resistance / 1000) + " kΩ");
        }
        else if (resistance >= 1000000 && resistance < 1000000000){
            ValuePartOne.setText(String.format("%.2f", resistance / 1000000) + " MΩ");
        }
        else if (resistance >= 1000000000){
            ValuePartOne.setText(String.format("%.2f", resistance / 1000000000) + " GΩ");
        }
        if (band == 4 || band == 5){
            ValuePartTwo.setText("Tolerance: " + tolerance + "%");
        }
        hideAll(10,true);
    }

    //Resistor tab
    public void ResistorTabClicked( ) {
        ResistorTab.isSelected();
        ResistorTab.setClosable(false); //disable the close button

        hideAll(-1,false);
        //change y position of the ResistorQuestion
        ResistorQuestion.setLayoutY(150);
        NumberOfBand.setLayoutY(150);


        ResistorImage.setImage(new Image("file:src/main/resources/lab2/pgupta85/exercise3/resistor.png"));
        //clear all item in the combo box
        NumberOfBand.getItems().clear();
        BandOneSelector.getItems().clear();
        BandTwoSelector.getItems().clear();
        BandThreeSelector.getItems().clear();
        MultiplierSelector.getItems().clear();
        ToleranceSelector.getItems().clear();

        //add items to the combo box
        NumberOfBand.getItems().addAll(3,4,5);
        BandOneSelector.getItems().addAll(colorBand.keySet());
        BandTwoSelector.getItems().addAll(colorBand.keySet());
        BandThreeSelector.getItems().addAll(colorBand.keySet());
        MultiplierSelector.getItems().addAll(multiplierBand.keySet());
        ToleranceSelector.getItems().addAll(toleranceBand.keySet());
    }

    //action for the combo box
    public void DropDownMenu() {
        //If the user select band one
        if (BandOneSelector.isShowing()){
            setRectangleColor(BandOneColor, BandOneSelector);
            //enable index for combo box
            ColorOneWarning.setVisible(false);
        }

        //If the user select band two
        if (BandTwoSelector.isShowing()){
            setRectangleColor(BandTwoColor, BandTwoSelector);
            ColorTwoWarning.setVisible(false);

        }

        //If the user select band three
        if (BandThreeSelector.isShowing()){
            setRectangleColor(BandThreeColor, BandThreeSelector);
            switch (band) {
                case 5 -> ColorThreeWarning.setVisible(false);
                default -> ColorFiveWarning.setVisible(false);
            }

        }

        //If the user select multiplier
        if (MultiplierSelector.isShowing()){
            setRectangleColor(MultiplierColor, MultiplierSelector);
            switch (band) {
                case 4, 3 -> ColorThreeWarning.setVisible(false);
                default -> ColorFourWarning.setVisible(false);
            }

        }

        //If the user select tolerance
        if (ToleranceSelector.isShowing()){
            setRectangleColor(ToleranceColor, ToleranceSelector);
            switch (band) {
                case 4 -> ColorFourWarning.setVisible(false);
                default -> ColorFiveWarning.setVisible(false);
            }

        }

        //If the user select number of band
        if (NumberOfBand.isShowing()){

            numberOfBandHelper(ColorOneWarning, BandOneSelector);
            numberOfBandHelper(ColorTwoWarning, BandTwoSelector);
            numberOfBandHelper(ColorThreeWarning, BandThreeSelector);
            numberOfBandHelper(ColorFourWarning, MultiplierSelector);
            numberOfBandHelper(ColorFiveWarning, ToleranceSelector);

            rearrangeGUI();
        }
    }

    //helper method for NumberOfBand menu in DropDownMenu()
    public void numberOfBandHelper(Text label, ComboBox<String> comboBox){
        label.setVisible(false); //hide the warning label
        comboBox.getSelectionModel().clearSelection(); //clear the selection
        comboBox.setStyle("-fx-border-color: transparent"); //clear the border

        comboBox.setButtonCell(new ListCell<>() {
            @Override
            //set the default value to null
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty); //call the super class
                if (empty || item == null) {
                    setText("Select Color"); //set the default value
                } else {
                    setText(item); //set the value
                }
            }
        });
    }

    public void setRectangleColor(Rectangle name, ComboBox<String> selector) {
        name.setVisible(true); //show the rectangle
        selector.setStyle("-fx-border-color: transparent"); //clear the border
        name.setStyle("-fx-fill: " + (selector.getValue())); //set the color
    }

    public void rearrangeGUI(){
        //rearrange the GUI after the user select the number of band

        hideAll(-1,false);
        ResistorQuestion.setLayoutY(31);
        NumberOfBand.setLayoutY(28);

        if (NumberOfBand.getValue() == 3){
            hideAll(0,true);
            MultiplierLabel.setLayoutY(165);
            MultiplierSelector.setLayoutY(160);
        }
        if (NumberOfBand.getValue() == 4){
            hideAll(4,true);
            MultiplierLabel.setLayoutY(165);
            ToleranceLabel.setLayoutY(205);
            MultiplierSelector.setLayoutY(160);
            ToleranceSelector.setLayoutY(200);
        }
        if (NumberOfBand.getValue() == 5){
            hideAll(5,true);
            BandThreeLabel.setLayoutY(165);
            MultiplierLabel.setLayoutY(205);
            ToleranceLabel.setLayoutY(245);
            BandThreeSelector.setLayoutY(160);
            MultiplierSelector.setLayoutY(200);
            ToleranceSelector.setLayoutY(240);
        }
    }

    //hide all the GUI or a certain part base on caseNumber provider
    public void hideAll (int caseNumber, boolean view){

        //set all BandOne item to false
        BandOneLabel.setVisible(view);
        BandOneSelector.setVisible(view);
        BandOneLine.setVisible(view);
        BandOneImageLabel.setVisible(view);

        //set all BandTwo item to false
        BandTwoLabel.setVisible(view);
        BandTwoSelector.setVisible(view);
        BandTwoLine.setVisible(view);
        BandTwoImageLabel.setVisible(view);

        //set all Multiplier item to false
        MultiplierLabel.setVisible(view);
        MultiplierSelector.setVisible(view);
        MultiplierImageLabel.setVisible(view);
        MultiplierLine.setVisible(view);

        switch (caseNumber) {
            case 4, 5, -1 -> {
                //set all Tolerance item to false
                ToleranceLabel.setVisible(view);
                ToleranceSelector.setVisible(view);
                ToleranceImageLabel.setVisible(view);
                ToleranceLine.setVisible(view);
            }
        }

        switch (caseNumber) {
            case 5, -1 -> {
                //set all Tolerance item to false
                BandThreeLabel.setVisible(view);
                BandThreeSelector.setVisible(view);
                BandThreeImageLabel.setVisible(view);
                BandThreeLine.setVisible(view);
            }
        }

        if (caseNumber == -1){
            //set all Tolerance item to false
            ColorOneWarning.setVisible(view);
            ColorTwoWarning.setVisible(view);
            ColorThreeWarning.setVisible(view);
            ColorFourWarning.setVisible(view);
            ColorFiveWarning.setVisible(view);

            BandOneColor.setVisible(view);
            BandTwoColor.setVisible(view);
            BandThreeColor.setVisible(view);
            MultiplierColor.setVisible(view);
            ToleranceColor.setVisible(view);
        }

        switch (caseNumber) {
            case 10, -1 -> {
                ValuePartOne.setVisible(view);
                ValuePartTwo.setVisible(view);
                ResultLabel.setVisible(view);
            }
        }
        ResistorImage.setVisible(view);
        //set button to false
        CalculateButton.setVisible(view);
    }

}