/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 19th January 2023
 * Student ID: 251211859
 * Task: Exercise 3
 * Description: This is the Controller class for the Beautiful Flowers Application. It is used to add functionality to the GUI.
 **********************************************************************************************************************/

package se2203.pgupta85.exercise3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class BeautifulFlowersController implements Initializable {
    @FXML
    private ImageView flowersImageView;
    @FXML
    private Label flowersNote;
    @FXML
    private RadioButton roseRadioButton;
    @FXML
    private RadioButton callaLilyRadioButton;
    @FXML
    private RadioButton cannaRadioButton;
    @FXML
    private RadioButton bleedingHeartRadioButton;
    @FXML
    private RadioButton cherryBlossomRadioButton;
    @FXML

    //Private variables for the images of the flowers
    private Image roseImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Rose.gif");
    private final Image callaLilyImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Calla Lily.gif");
    private final Image cannaImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Canna.gif");
    private final Image bleedingHeartImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Bleeding Heart.gif");
    private final Image cherryBlossomImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Cherry Blossom.gif");

    //Getter methods for the images
    public Image getRoseImage() {
        return roseImage;
    }
    public Image getCallaLilyImage() {
        return callaLilyImage;
    }
    public Image getCannaImage() {
        return cannaImage;
    }
    public Image getBleedingHeartImage() {
        return bleedingHeartImage;
    }
    public Image getCherryBlossomImage() {
        return cherryBlossomImage;
    }

    @Override //This method is called by the FXMLLoader when initialization is complete, it is used to set the initial values for the controller's variables
    public void initialize(URL url, ResourceBundle rb) {
        flowersImageView.setImage(new Image(getRoseImage().getUrl()));
        flowersNote.setText("This beautiful flower and symbol of love belongs the genus Rosa. The family name of this flower is Rosaceae and it contains different other species in almost all parts of the world. The flower of rose vary in size from each other depending upon the species to which they belong. A large number of species of this flower are found in different parts of Asia.");
    }

    //This method is called when the user clicks on the roseRadioButton
    public void roseRadioButtonListener() {
        //Set the image and notes of the flower: rose
        flowersImageView.setImage(new Image(getRoseImage().getUrl()));
        flowersNote.setText("This beautiful flower and symbol of love belongs the genus Rosa. The family name of this flower is Rosaceae and it contains different other species in almost all parts of the world. The flower of rose vary in size from each other depending upon the species to which they belong. A large number of species of this flower are found in different parts of Asia.");
    }

    //This method is called when the user clicks on the callaLilyRadioButton
    public void cannaRadioButtonListener() {
        //Set the image and notes of the flower: canna
        flowersImageView.setImage(new Image(getCannaImage().getUrl()));
        flowersNote.setText("This beautiful flowering plant belongs to a genus that contain around 10 species and its family is known as Cannaceae. This flower plant also provides large quantity of starch which is further used for different purposes. This flower is mostly found in tropical regions of the world. The flowers of this plant have three sepals and three petals.");
    }

    //This method is called when the user clicks on the callaLilyRadioButton
    public void callaLilyRadioButtonListener() {
        //Set the image and notes of the flower: calla lily
        flowersImageView.setImage(new Image(getCallaLilyImage().getUrl()));
        flowersNote.setText("One simple and common name of this beautiful flower is arum lily and this flowering plant belongs to the family known as Araceae. This flowering plant is grown well in areas which have reasonable rainfall and moderate temperatures.");
    }

    //This method is called when the user clicks on the bleedingHeartRadioButton
    public void bleedingHeartRadioButtonListener() {
        //Set the image and notes of the flower: bleeding heart
        flowersImageView.setImage(new Image(getBleedingHeartImage().getUrl()));
        flowersNote.setText("The bleeding heart flower belongs to the family known as Papaveraceae. This heart shaped flower is famous all over the world due to its unique beauty and is found in great numbers in Siberia and China. Blooming season of this flower starts in spring when there spread beautiful pink heart-shaped flowers in gardens. Lady-in-a-bath is also a common name of this flower.");
    }

    //This method is called when the user clicks on the cherryBlossomRadioButton
    public void cherryBlossomRadioButtonListener() {
        //Set the image and notes of the flower: cherry blossom
        flowersImageView.setImage(new Image(getCherryBlossomImage().getUrl()));
        flowersNote.setText("Cherry blossom, a beautiful flowering plant is found in different parts of the world including the Northern Hemisphere. It is found in those areas which have moderate climate. All species of this flowering plant do not produce cherries as there are special species of this flower that produce edible cherries.");
    }

}