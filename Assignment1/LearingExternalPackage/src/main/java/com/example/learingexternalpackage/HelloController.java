package com.example.learingexternalpackage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
//import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.*;
import eu.hansolo.medusa.*;


public class HelloController {
    public Slider slider;
    @FXML
    private Label welcomeText;

    @FXML
    private Gauge gauge;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        gauge.setSkinType(Gauge.SkinType.MODERN);

    }

    public void onSide() {
        gauge.setValue(slider.getValue());
    }


}