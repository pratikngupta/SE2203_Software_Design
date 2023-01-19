package se2203.pgupta85.exercise2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class SampleHandlerController {
    @FXML
    private Label jumpingLabel;
    public void movingLabel(){
        Random randomPos = new Random();
        jumpingLabel.setLayoutX(randomPos.nextInt(500));
        jumpingLabel.setLayoutY(randomPos.nextInt(300));
    }

}