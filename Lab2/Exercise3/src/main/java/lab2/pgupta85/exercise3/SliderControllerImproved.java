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
                double bandThreeValue = colorBand.get(BandThreeSelector.getValue());
                resistance = (bandOneValue * 100 + bandTwoValue * 10 + bandThreeValue) * multiplier;
            }
            displayResult();
        }
        catch (Exception e) {

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
    }

    public void DropDownMenu() {
        if (BandOneSelector.isShowing()){
            setRectangleColor(BandOneColor, BandOneSelector);
            //enable index for combo box
            ColorOneWarning.setVisible(false);

        }

        if (BandTwoSelector.isShowing()){
            setRectangleColor(BandTwoColor, BandTwoSelector);
            ColorTwoWarning.setVisible(false);

        }

        if (BandThreeSelector.isShowing()){
            setRectangleColor(BandThreeColor, BandThreeSelector);
            switch (band) {
                case 5 -> ColorThreeWarning.setVisible(false);
                default -> ColorFiveWarning.setVisible(false);
            }

        }

        if (MultiplierSelector.isShowing()){
            setRectangleColor(MultiplierColor, MultiplierSelector);
            switch (band) {
                case 4, 3 -> ColorThreeWarning.setVisible(false);
                default -> ColorFourWarning.setVisible(false);
            }

        }

        if (ToleranceSelector.isShowing()){
            setRectangleColor(ToleranceColor, ToleranceSelector);
            switch (band) {
                case 4 -> ColorFourWarning.setVisible(false);
                default -> ColorFiveWarning.setVisible(false);
            }

        }

        if (NumberOfBand.isShowing()){
            //clear all selection for combo box
            BandOneSelector.getSelectionModel().clearSelection();
            BandTwoSelector.getSelectionModel().clearSelection();
            BandThreeSelector.getSelectionModel().clearSelection();
            MultiplierSelector.getSelectionModel().clearSelection();
            ToleranceSelector.getSelectionModel().clearSelection();

            //clear all color for rectangle
            BandOneSelector.setStyle("-fx-border-color: transparent");
            BandTwoSelector.setStyle("-fx-border-color: transparent");
            BandThreeSelector.setStyle("-fx-border-color: transparent");
            MultiplierSelector.setStyle("-fx-border-color: transparent");
            ToleranceSelector.setStyle("-fx-border-color: transparent");

            ColorOneWarning.setVisible(false);
            ColorTwoWarning.setVisible(false);
            ColorThreeWarning.setVisible(false);
            ColorFourWarning.setVisible(false);
            ColorFiveWarning.setVisible(false);

            updateItem(BandOneSelector);
            updateItem(BandTwoSelector);
            updateItem(BandThreeSelector);
            updateItem(MultiplierSelector);
            updateItem(ToleranceSelector);

            rearrangeGUI();
        }
    }

    //update for combo box
    public void updateItem(ComboBox<String> comboBox){
        comboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Select Color");
                } else {
                    setText(item);
                }
            }
        });
    }

    public void setRectangleColor(Rectangle name, ComboBox<String> selector) {
        name.setVisible(true);
        selector.setStyle("-fx-border-color: transparent");
        name.setStyle("-fx-fill: " + (selector.getValue()));

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
        BandOneLine.setVisible(view);
        BandOneImageLabel.setVisible(view);

        //set all BandTwo item to false
        BandTwoLabel.setVisible(view);
        BandTwoSelector.setVisible(view);
        BandTwoline.setVisible(view);
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