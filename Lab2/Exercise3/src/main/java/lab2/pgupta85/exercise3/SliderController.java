package lab2.pgupta85.exercise3;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

public class SliderController {

    @FXML
    private Label celsiusBox;

    @FXML
    private Slider celsiusSlider;

    @FXML
    private Label fahrenheitBox;


    public void initialize() {

        celsiusBox.setStyle("-fx-border-color: black");
        celsiusBox.setAlignment(Pos.CENTER);
        //set fixed width for the label
        celsiusBox.setMinWidth(50);
        celsiusBox.setText("0");

        fahrenheitBox.setStyle("-fx-border-color: black");
        //set fixed width for the label
        fahrenheitBox.setAlignment(Pos.CENTER);
        fahrenheitBox.setMinWidth(50);
        fahrenheitBox.setText("32");

        celsiusSlider.setMin(0);
    }

    public void CelsiusToFah(MouseEvent mouseEvent) {
        double celsius = celsiusSlider.getValue();
        celsiusBox.setText(String.format("%.2f", celsius));
        fahrenheitBox.setText(String.format("%.2f", (converter(celsius))));
    }

    public double converter (double celsius) {
        return (celsius * 9 / 5) + 32;
    }
}