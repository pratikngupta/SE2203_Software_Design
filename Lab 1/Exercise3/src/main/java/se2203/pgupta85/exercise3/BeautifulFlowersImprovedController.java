/***********************************************************************************************************************
 * Name: Pratik Narendra Gupta
 * Date: 19th January 2023
 * Student ID: 251211859
 * Task: Exercise 3
 * Description: This is the main class for the Beautiful Flowers Application. It is used to add functionality to the GUI.
 * It is a major improvement over the previous version of the application.
 **********************************************************************************************************************/

package se2203.pgupta85.exercise3;

import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
public class BeautifulFlowersImprovedController implements Initializable {

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

    //Private variables for the images of the flowers
    private Image RoseImage;
    private Image CallaLilyImage;
    private Image CannaImage;
    private Image BleedingHeartImage;
    private Image CherryBlossomImage;
    private Image WesternLogo;

    //set background for the welcomeTabView
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set background for the welcomeTabView from the resources folder and set default tab to welcomeTab

        RoseImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Rose.gif");
        CallaLilyImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Calla Lily.gif");
        CannaImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Canna.gif");
        BleedingHeartImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Bleeding Heart.gif");
        CherryBlossomImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Cherry Blossom.gif");
        WesternLogo = new Image("file:src/main/resources/se2203/pgupta85/exercise3/WesternLogo.png");

        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);

        //noinspection CssUnknownTarget
        WelcomeTabView.setStyle("-fx-background-image: url('file:src/main/resources/se2203/pgupta85/exercise3/walpaper.jpg')"); //set background for the welcomeTabView from the resources folder
    }

    //change view when the rose tab is selected
    public void RoseTabClicked() {
        RoseTab.isSelected();
        RoseTab.setClosable(false);
        //set image for the RoseTab
        roseImage.setImage(RoseImage);
    }

    //change view when the Bleeding tab is selected
    public void BleedingTabCLicked() {
        BleedingTab.isSelected();
        BleedingTab.setClosable(false);
        //set image for the BleedingTab
        bleedingImage.setImage(BleedingHeartImage);
    }

    //change view when the canna tab is selected
    public void cannaTabClicked() {
        cannaTab.isSelected();
        cannaTab.setClosable(false);
        //set image for the cannaTab
        cannaImage.setImage(CannaImage);
    }

    //change view when the cherry tab is selected
    public void CherryTabClicked() {
        CherryTab.isSelected();
        CherryTab.setClosable(false);
        //set image for the CherryTab
        cherryImage.setImage(CherryBlossomImage);
    }

    //change view when the rose tab is selected
    public void WelcomeTabClicked() {
        //set default tab
        WelcomeTab.isSelected();
        WelcomeTab.setClosable(false);
        //set image for the WelcomeTab
    }

    //change view when the Calla tab is selected
    public void CallaTabClicked() {
        callaTab.isSelected();
        callaTab.setClosable(false);
        //set image for the callaTab
        callaImage.setImage(CallaLilyImage);
    }

    //change view when the aboutMe tab is selected
    public void aboutMeTabClicked() {
        aboutMeTab.isSelected();
        aboutMeTab.setClosable(false);
        //set image for the aboutMeTab
        aboutMeImage.setImage(WesternLogo);
        //noinspection CssUnknownTarget
        aboutMePane.setStyle("-fx-background-image: url('file:src/main/resources/se2203/pgupta85/exercise3/aboutMe.jpg')"); //set background for the aboutMePane from the resources folder
    }

}
