package assignment2.pgupta85.ifinance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class iFinanceController {


    public MenuItem AboutUs;
    @FXML
    private Menu aboutMenu;


    public void initialize() {
    }

    @FXML
    void openAboutMenu(ActionEvent event) {
        //when the menu is clicked, show the about menu
        aboutMenu.show();
    }

    //when about us is clicked, show the about us page
    @FXML
    void openAboutUs() {
        System.out.println("About Us");
    }

    public void AboutUsClicked() {
        //open new pop up window with about us info
        System.out.println("About Us");

        //pop up new window with about us info


        
    }
}
