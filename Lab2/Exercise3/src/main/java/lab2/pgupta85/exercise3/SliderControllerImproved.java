package lab2.pgupta85.exercise3;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.HashMap;

public class SliderControllerImproved {
    @FXML
    private Text ColorOneWarning;
    @FXML
    private Text ColorFourWarning;
    @FXML
    private Text ColorTwoWarning;
    @FXML
    private Text ColorThreeWarning;
    @FXML
    private Text ColorFiveWarning;
    @FXML
    private Label BandOneImageLabel;
    @FXML
    private Label BandOneLabel;
    @FXML
    private Label BandTwoLabel;
    @FXML
    private Label MultiplierLabel;
    @FXML
    private Label ToleranceLabel;
    @FXML
    private Label BandTwoImageLabel;
    @FXML
    private Label ToleranceImageLabel;
    @FXML
    private Line BandTwoline;
    @FXML
    private Line ToleranceLine;
    @FXML
    private Label ResultLabel;
    @FXML
    private Line MultiplierLine;
    @FXML
    private Line BandOneLine;
    @FXML
    private Label MultiplierImageLabel;
    @FXML
    private Label ResistorQuestion;
    @FXML
    private Label BandThreeLabel;
    @FXML
    private Line BandThreeLine;
    @FXML
    private Label BandThreeImageLabel;
    @FXML
    private Rectangle BandThreeColor;
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
    private ComboBox<String> BandThreeSelector;
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
    private AnchorPane ResistorPane;
    @FXML
    private Rectangle MultiplierColor;
    @FXML
    private ComboBox<String> MultiplierSelector;
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
    @FXML
    private ComboBox<Integer> NumberOfBand;

    private double sliderValue;
    private double tolerance;
    private double bandThreeValue;
    private double resistance;
    private int band;


    HashMap<String, Double> colorBand = new HashMap<>();
    HashMap<String, Double> multiplierBand = new HashMap<>();
    HashMap<String, Double> toleranceBand = new HashMap<>();

    public void initialize() {
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);
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

    public void WelcomeTabClicked() {
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);
    }

    public void TemperatureTabClicked() {
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

    public void CelsiusRadioButtonClicked() {
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

    public void TemperatureSlider() {
        sliderValue = TemperatureSlider.getValue();
        TempConverter();
    }

    public void TempConverter() {

        double celsius;
        double fahrenheit;
        double kelvin;
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

    public void aboutMeTabClicked() {
        aboutMeTab.isSelected();
        aboutMeTab.setClosable(false);
        //noinspection CssUnknownTarget
        AboutMePlane.setStyle("-fx-background-image: url('file:src/main/resources/lab2/pgupta85/exercise3/aboutMe.jpg')");
        aboutMeImage.setImage(new Image("file:src/main/resources/lab2/pgupta85/exercise3/WesternLogo.png"));
    }

    public void CalculateResistance() {
        band = NumberOfBand.getValue();
        resistance = 0;
        double bandOneValue;
        double bandTwoValue ;
        double multiplier;
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
                bandThreeValue = colorBand.get(BandThreeSelector.getValue());
                resistance = (bandOneValue * 100 + bandTwoValue * 10 + bandThreeValue) * multiplier;
            }
            displayResult();
        }
        catch (Exception e) {
            if (String.valueOf(BandOneSelector.getValue()).equals("null")){
                ColorOneWarning.setVisible(true);
            }
            if (String.valueOf(BandTwoSelector.getValue()).equals("null")){
                ColorTwoWarning.setVisible(true);
            }
            if (String.valueOf(MultiplierSelector.getValue()).equals("null")){
                ColorThreeWarning.setVisible(true);
            }
            if (band == 4 || band == 5){
                if (String.valueOf(ToleranceSelector.getValue()).equals("null")){
                    ColorFourWarning.setVisible(true);
                }
            }
            if (band == 5){
                if (String.valueOf(BandThreeSelector.getValue()).equals("null")){
                    ColorFiveWarning.setVisible(true);
                }
            }
        }


    }

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

    public void ResistorTabClicked( ) {
        ResistorTab.isSelected();
        ResistorTab.setClosable(false);
        hideAll(-1,false);
        //change y position of the ResistorQuestion
        ResistorQuestion.setLayoutY(150);
        NumberOfBand.setLayoutY(150);

        ColorOneWarning.setVisible(false);
        ColorTwoWarning.setVisible(false);
        ColorThreeWarning.setVisible(false);
        ColorFourWarning.setVisible(false);
        ColorFiveWarning.setVisible(false);

        ResistorImage.setImage(new Image("file:src/main/resources/lab2/pgupta85/exercise3/resistor.png"));
        //clear all item in the combo box
        NumberOfBand.getItems().clear();
        BandOneSelector.getItems().clear();
        BandTwoSelector.getItems().clear();
        BandThreeSelector.getItems().clear();
        MultiplierSelector.getItems().clear();
        ToleranceSelector.getItems().clear();

        NumberOfBand.getItems().addAll(3,4,5);
        BandOneSelector.getItems().addAll(colorBand.keySet());
        BandTwoSelector.getItems().addAll(colorBand.keySet());
        BandThreeSelector.getItems().addAll(colorBand.keySet());
        MultiplierSelector.getItems().addAll(multiplierBand.keySet());
        ToleranceSelector.getItems().addAll(toleranceBand.keySet());

        //edit text color for comboBox
        BandOneSelector.setStyle("-fx-text-fill: White");

    }

    public void DropDownMenu() {
        if (BandOneSelector.isShowing()){
            setRectangleColor(1, BandOneSelector.getValue());
            //enable index for combo box
            ColorOneWarning.setVisible(false);
        }

        if (BandTwoSelector.isShowing()){
            setRectangleColor(2, BandTwoSelector.getValue());
            ColorTwoWarning.setVisible(false);
        }

        if (BandThreeSelector.isShowing()){
            setRectangleColor(3, BandThreeSelector.getValue());
            ColorFiveWarning.setVisible(false);
        }

        if (MultiplierSelector.isShowing()){
            setRectangleColor(4, MultiplierSelector.getValue());
            ColorThreeWarning.setVisible(false);
        }

        if (ToleranceSelector.isShowing()){
            setRectangleColor(5, ToleranceSelector.getValue());
            ColorFourWarning.setVisible(false);
        }

        if (NumberOfBand.isShowing()){
            rearrangeGUI();
        }
    }

    public void setRectangleColor(int caseNumber, String color) {

        if (caseNumber == 1) {
            BandOneColor.setVisible(true);
            BandOneColor.setStyle("-fx-fill: " + color);
        }

        if (caseNumber == 2) {
            BandTwoColor.setVisible(true);
            BandTwoColor.setStyle("-fx-fill: " + color);
        }

        if (caseNumber == 3) {
            BandThreeColor.setVisible(true);
            BandThreeColor.setStyle("-fx-fill: " + color);
        }

        if (caseNumber == 4) {
            MultiplierColor.setVisible(true);
            MultiplierColor.setStyle("-fx-fill: " + color);
        }

        if (caseNumber == 5) {
            ToleranceColor.setVisible(true);
            ToleranceColor.setStyle("-fx-fill: " + color);
        }
    }

    public void rearrangeGUI(){
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

    public void hideAll (int caseNumber, boolean view){
        //set all BandOne item to false
        BandOneLabel.setVisible(view);
        BandOneSelector.setVisible(view);
        BandOneColor.setVisible(view);
        BandOneLine.setVisible(view);
        BandOneImageLabel.setVisible(view);

        //set all BandTwo item to false
        BandTwoLabel.setVisible(view);
        BandTwoSelector.setVisible(view);
        BandTwoColor.setVisible(view);
        BandTwoline.setVisible(view);
        BandTwoImageLabel.setVisible(view);

        //set all Multiplier item to false
        MultiplierLabel.setVisible(view);
        MultiplierSelector.setVisible(view);
        MultiplierColor.setVisible(view);
        MultiplierImageLabel.setVisible(view);
        MultiplierLine.setVisible(view);

        if (caseNumber == 4 || caseNumber == 5 || caseNumber == -1){
            //set all Tolerance item to false
            ToleranceLabel.setVisible(view);
            ToleranceSelector.setVisible(view);
            ToleranceColor.setVisible(view);
            ToleranceImageLabel.setVisible(view);
            ToleranceLine.setVisible(view);
        }

        if (caseNumber == 5 || caseNumber == -1){
            //set all Tolerance item to false
            BandThreeLabel.setVisible(view);
            BandThreeSelector.setVisible(view);
            BandThreeColor.setVisible(view);
            BandThreeImageLabel.setVisible(view);
            BandThreeLine.setVisible(view);
        }

        //set all Value item to false
        ResistorImage.setVisible(view);

        if (caseNumber == 10 || caseNumber == -1){
            ValuePartOne.setVisible(view);
            ValuePartTwo.setVisible(view);
            ResultLabel.setVisible(view);
        }
        //set button to false
        CalculateButton.setVisible(view);
    }

}