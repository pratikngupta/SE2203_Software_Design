package se2203.pgupta85.exercise3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
public class BeautifulFlowersController implements Initializable {
    public ToggleGroup flowersToggleGroup;
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

    public BeautifulFlowersController(TitledPane aboutMe) {
    }

    //declare private field for image: Rose.gif
    private Image roseImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Rose.gif");
    //declare private field for image: CallaLily.gif
    private Image callaLilyImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Calla.gif");
    //declare private field for image: Canna.gif
    private Image cannaImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Canna.gif");
    //declare private field for image: BleedingHeart.gif
    private Image bleedingHeartImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Bleeding Heart.gif");
    //declare private field for image: CherryBlossom.gif
    private Image cherryBlossomImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Cherry Blossom.gif");

    //create a method to set the image of the ImageView
    public void setFlowersImageView(Image image) {
        flowersImageView.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ADD LINES FOR TASK #3 HERE
        // Initialize the images
        // Set the default image
        //set image from resources
        setFlowersImageView(roseImage);
        flowersNote.setText("This beautiful flower and symbol of love belongs the genus Rosa. The family name of this flower is Rosaceae and it contains different other species in almost all parts of the world. The flower of rose vary in size from each other depending upon the species to which they belong. A large number of species of this flower are found in different parts of Asia.");
    }
    public void roseRadioButtonListener() {
        // ADD THE LINES FOR TASK #3 HERE
        // Set the image
        // Set the note
        setFlowersImageView(roseImage);
        flowersNote.setText("This beautiful flower and symbol of love belongs the genus Rosa. The family name of this flower is Rosaceae and it contains different other species in almost all parts of the world. The flower of rose vary in size from each other depending upon the species to which they belong. A large number of species of this flower are found in different parts of Asia.");
        if (roseRadioButton.isSelected()) {
            setVisible();
        }
        // display image and data for Rose.
    }
    public void cannaRadioButtonListener() {

        setFlowersImageView(cannaImage);
        flowersNote.setText("This beautiful flowering plant belongs to a genus that contain around 10 species and its family is known as Cannaceae. This flower plant also provides large quantity of starch which is further used for different purposes. This flower is mostly found in tropical regions of the world. The flowers of this plant have three sepals and three petals.");
        if (cannaRadioButton.isSelected()) {
            setVisible();
        }

    }
    public void callaLilyRadioButtonListener() {

        setFlowersImageView(callaLilyImage);
        flowersNote.setText("One simple and common name of this beautiful flower is arum lily and this flowering plant belongs to the family known as Araceae. This flowering plant is grown well in areas which have reasonable rainfall and moderate temperatures.");
        if (callaLilyRadioButton.isSelected()) {
            setVisible();
        }
    }
    public void bleedingHeartRadioButtonListener() {

        setFlowersImageView(bleedingHeartImage);
        flowersNote.setText("The bleeding heart flower belongs to the family known as Papaveraceae. This heart shaped flower is famous all over the world due to its unique beauty and is found in great numbers in Siberia and China. Blooming season of this flower starts in spring when there spread beautiful pink heart-shaped flowers in gardens. Lady-in-a-bath is also a common name of this flower.");
        if (bleedingHeartRadioButton.isSelected()) {
            setVisible();
        }
    }
    public void cherryBlossomRadioButtonListener() {

        setFlowersImageView(cherryBlossomImage);
        flowersNote.setText("Cherry blossom, a beautiful flowering plant is found in different parts of the world including the Northern Hemisphere. It is found in those areas which have moderate climate. All species of this flowering plant do not produce cherries as there are special species of this flower that produce edible cherries.");
        if (cherryBlossomRadioButton.isSelected()) {
            setVisible();
        }
        // ADD THE LINES FOR TASK #3 HERE
        // If this radio button is selected,
        // display image and data for Rose.
    }

    public void setVisible() {
        flowersNote.setVisible(true);
        flowersImageView.setVisible(true);
    }
    // Repeat and modify the above handler for the rest of the other flowers.
}