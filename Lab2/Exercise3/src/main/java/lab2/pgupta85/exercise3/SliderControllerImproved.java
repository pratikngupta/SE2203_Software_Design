package lab2.pgupta85.exercise3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SliderControllerImproved {

    @FXML
    private AnchorPane AboutMePlane;
    @FXML
    private Label BaseUnit;
    @FXML
    private Label BaseUnitBValue;
    @FXML
    private RadioButton CelsiusRadioButton;
    @FXML
    private RadioButton FahrenheitRadioButton;
    @FXML
    private RadioButton KelvinRadioButton;
    @FXML
    private Tab MetricTab;
    @FXML
    private AnchorPane MetricTabPlane;
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
    private Tab WelcomeTab;
    @FXML
    private AnchorPane WelcomeTabPlane;
    @FXML
    private ImageView aboutMeImage;
    @FXML
    private Tab aboutMeTab;

    private double celsius;
    private double fahrenheit;
    private double kelvin;
    private double sliderValue;

    public void initialize(){
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);

        WelcomeTabPlane.setStyle("-fx-background-color: rgba(0,57,190,0.67)");
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

    public void MetricTabClicked( ) {
        MetricTab.isSelected();
        MetricTab.setClosable(false);
    }

    public void aboutMeTabClicked( ) {
        aboutMeTab.isSelected();
        aboutMeTab.setClosable(false);

        AboutMePlane.setStyle("-fx-background-color: #824d4d");

    }

}
