package se2203b.assignments.ifinance;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.assignments.ifinance.DisplayAlert.displayAlert;

/**
 * @author Abdelkader Ouda
 */
public class IFinanceController implements Initializable {

    @FXML
    private Menu aboutMenu, fileMenu, manageAccountGroupsMenu, chartsOfAccountMenu, doubleEntryMenu, financialMenu, changePasswordMenu;
    @FXML
    private MenuBar mainMenu;

    private Connection connection;


    public void showAbout() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("About-view.fxml"));
        // create the root node
        Parent About = fxmlLoader.load();
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void exit() {
        // Get current stage reference
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //disable all menu items
        setMenuItems(false);

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iFinanceDatabase;create=true";
            // Create a connection to the database
            connection = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }

    public void login() {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("Login-view.fxml"));
        // create the root node
        Parent Login = null;
        try {
            Login = fxmlLoader.load();
            // create new stage
            Stage stage = new Stage();
            // add the about's UI elements to the stage
            stage.setScene(new Scene(Login));
            // add icon to the About window
            stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
            stage.setTitle("Login");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            System.out.println("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMenuItems(boolean status) {
        manageAccountGroupsMenu.setDisable(!status);
        chartsOfAccountMenu.setDisable(!status);
        doubleEntryMenu.setDisable(!status);
        financialMenu.setDisable(!status);
        changePasswordMenu.setDisable(!status);
    }
}
