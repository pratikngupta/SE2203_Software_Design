package se2203.pgupta85.exercise3;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
public class BeautifulFlowersController implements Initializable {

    public AnchorPane WelcomeTabView;
    public Tab RoseTab;
    public Tab BleedingTab;
    public Tab cannaTab;
    public Tab CherryTab;
    public Tab WelcomeTab;
    public Tab callaTab;
    public Tab aboutMeTab;
    public ImageView roseImage;
    public ImageView cherryImage;
    public ImageView cannaImage;
    public ImageView bleedingImage;
    public ImageView callaImage;
    public ImageView aboutMeImage;
    public AnchorPane aboutMePane;


    //set background for the welcomeTabView
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set background for the welcomeTabView from the resources folder
        WelcomeTabView.setStyle("-fx-background-image: url('file:src/main/resources/se2203/pgupta85/exercise3/walpaper.jpg')");
    }

    public void RoseTabClicked(Event event) {
        //set default tab
        RoseTab.isSelected();
        RoseTab.setClosable(false);
        //set image for the RoseTab
        roseImage.setImage(new Image("file:src/main/resources/se2203/pgupta85/exercise3/Rose.gif"));
    }


    public void BleedingTabCLicked(Event event) {
        //set default tab
        BleedingTab.isSelected();
        BleedingTab.setClosable(false);
        //set image for the BleedingTab
        bleedingImage.setImage(new Image("file:src/main/resources/se2203/pgupta85/exercise3/Bleeding Heart.gif"));
        
    }

    public void cannaTabClicked(Event event) {
        //set default tab
        cannaTab.isSelected();
        cannaTab.setClosable(false);
        //set image for the cannaTab
        cannaImage.setImage(new Image("file:src/main/resources/se2203/pgupta85/exercise3/Canna.gif"));
    }

    public void CherryTabClicked(Event event) {
        //set default tab
        CherryTab.isSelected();
        CherryTab.setClosable(false);
        //set image for the CherryTab
        cherryImage.setImage(new Image("file:src/main/resources/se2203/pgupta85/exercise3/Cherry Blossom.gif"));
        
    }

    public void WelcomeTabClicked(Event event) {
        //set default tab
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);
        //set image for the WelcomeTab

    }

    public void CallaTabClicked(Event event) {
        //set default tab
        callaTab.isSelected();
        callaTab.setClosable(false);
        //set image for the callaTab
        callaImage.setImage(new Image("file:src/main/resources/se2203/pgupta85/exercise3/Calla Lily.gif"));
    }

    public void aboutMeTabClicked(Event event) {
        //set default tab
        aboutMeTab.isSelected();
        aboutMeTab.setClosable(false);
        //set image for the aboutMeTab
        aboutMeImage.setImage(new Image("file:src/main/resources/se2203/pgupta85/exercise3/WesternLogo.png"));
        aboutMePane.setStyle("-fx-background-image: url('file:src/main/resources/se2203/pgupta85/exercise3/aboutMe.jpg')");
    }
}