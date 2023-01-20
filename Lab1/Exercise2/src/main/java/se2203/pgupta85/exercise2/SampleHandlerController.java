/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 19th January 2023
 * Student ID: 251211859
 * Task: Exercise 2
 * Description: This is the controller class for the sample handler. This will add functionality to the GUI by moving the label when mouse is over it
 **********************************************************************************************************************/

package se2203.pgupta85.exercise2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class SampleHandlerController {
    @FXML
    private Label jumpingLabel;
    public void movingLabel(){
        // Create a random number generator.
        Random randomPos = new Random();
        // Generate a random number between 0 and 700 for the x coordinate.
        jumpingLabel.setLayoutX(randomPos.nextInt(500));
        // Generate a random number between 0 and 500 for the y-axis.
        jumpingLabel.setLayoutY(randomPos.nextInt(300));
    }

}