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
    private final Image RoseImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Rose.gif");
    private final Image CallaLilyImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Calla Lily.gif");
    private final Image CannaImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Canna.gif");
    private final Image BleedingHeartImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Bleeding Heart.gif");
    private final Image CherryBlossomImage = new Image("file:src/main/resources/se2203/pgupta85/exercise3/Cherry Blossom.gif");
    private final Image WesternLogo = new Image("file:src/main/resources/se2203/pgupta85/exercise3/WesternLogo.png");

    //Getter methods for the images
    public String getRoseImage() {
        return RoseImage.getUrl();
    }
    public String getCallaLilyImage() {
        return CallaLilyImage.getUrl();
    }
    public String getCannaImage() {
        return CannaImage.getUrl();
    }
    public String getBleedingHeartImage() {
        return BleedingHeartImage.getUrl();
    }
    public String getCherryBlossomImage() {
        return CherryBlossomImage.getUrl();
    }
    public String getWesternLogo() {
        return WesternLogo.getUrl();
    }

    //set background for the welcomeTabView
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set background for the welcomeTabView from the resources folder and set default tab to welcomeTab
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
        roseImage.setImage(new Image(getRoseImage()));
    }

    //change view when the Bleeding tab is selected
    public void BleedingTabCLicked() {
        BleedingTab.isSelected();
        BleedingTab.setClosable(false);
        //set image for the BleedingTab
        bleedingImage.setImage(new Image(getBleedingHeartImage()));
    }

    //change view when the canna tab is selected
    public void cannaTabClicked() {
        cannaTab.isSelected();
        cannaTab.setClosable(false);
        //set image for the cannaTab
        cannaImage.setImage(new Image(getCannaImage()));
    }

    //change view when the cherry tab is selected
    public void CherryTabClicked() {
        CherryTab.isSelected();
        CherryTab.setClosable(false);
        //set image for the CherryTab
        cherryImage.setImage(new Image(getCherryBlossomImage()));
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
        callaImage.setImage(new Image(getCallaLilyImage()));
    }

    //change view when the aboutMe tab is selected
    public void aboutMeTabClicked() {
        aboutMeTab.isSelected();
        aboutMeTab.setClosable(false);
        //set image for the aboutMeTab
        aboutMeImage.setImage(new Image(getWesternLogo()));
        //noinspection CssUnknownTarget
        aboutMePane.setStyle("-fx-background-image: url('file:src/main/resources/se2203/pgupta85/exercise3/aboutMe.jpg')"); //set background for the aboutMePane from the resources folder
    }

}
