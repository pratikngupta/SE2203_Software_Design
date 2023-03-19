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

import java.io.IOException;
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
    private Menu aboutMenu, fileMenu, manageAccountGroupsMenu, chartsOfAccountMenu, doubleEntryMenu, financialMenu, manageUserAccountMenu, userInfoMenu;
    @FXML
    private MenuBar mainMenu;
    private Connection connection;
    private UserAdapter users;

    User currentUser = null;

    private final String logoPath = "file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png";

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
        setMenuItems();
        userInfoMenu.setVisible(false);

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iFinanceDatabase;create=true";
            // Create a connection to the database
            connection = DriverManager.getConnection(DB_URL);

            reset();

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }

    public void login() throws SQLException, IOException {
        users = new UserAdapter(connection, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("Login-view.fxml"));
        Parent standings = (Parent) fxmlLoader.load();

        LoginController loginController = (LoginController) fxmlLoader.getController();
        loginController.setModel(users);

        // create new stage
        Scene scene = new Scene(standings);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image(logoPath));
        stage.setTitle("Login");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();

        currentUser = UserAdapter.setCurrentUser();

        setMenuItems();
    }

    public void setMenuItems() {
        if (currentUser == null) {
            manageAccountGroupsMenu.setDisable(true);
            chartsOfAccountMenu.setDisable(true);
            doubleEntryMenu.setDisable(true);
            financialMenu.setDisable(true);
            manageUserAccountMenu.setDisable(true);
            userInfoMenu.setVisible(false);

        } else {
            userInfoMenu.setText(currentUser.getUsername());
            userInfoMenu.setVisible(true);

            if (currentUser.isAdmin()) {
                manageAccountGroupsMenu.setDisable(true);
                chartsOfAccountMenu.setDisable(true);
                doubleEntryMenu.setDisable(true);
                financialMenu.setDisable(true);
                manageUserAccountMenu.setDisable(false);
            } else {
                manageAccountGroupsMenu.setDisable(false);
                chartsOfAccountMenu.setDisable(false);
                doubleEntryMenu.setDisable(false);
                financialMenu.setDisable(false);
                manageUserAccountMenu.setDisable(true);
            }
        }
    }

    @FXML
    void showChangePassword( ) throws SQLException, IOException {
        users = new UserAdapter(connection, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("ChangePassword-view.fxml"));
        Parent standings = (Parent) fxmlLoader.load();

        ChangePasswordController changePassword = (ChangePasswordController) fxmlLoader.getController();
        changePassword.setModel(users, currentUser);

        // create new stage
        Scene scene = new Scene(standings);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image(logoPath));
        stage.setTitle("Change Password");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    @FXML
    void showCreateUserAccount( ) throws SQLException, IOException {
        users = new UserAdapter(connection, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("CreateUserAccount-view.fxml"));
        Parent standings = (Parent) fxmlLoader.load();

        CreateUserAccountController createAccount = (CreateUserAccountController) fxmlLoader.getController();
        createAccount.setModel(users);

        // create new stage
        Scene scene = new Scene(standings);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image(logoPath));
        stage.setTitle("Create User Account");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }

    @FXML
    void showDeleteUserAccount( ) {

    }

    @FXML
    void showLogout( ) {
        currentUser = null;
        displayAlert("You have been logged out");
        setMenuItems();
    }

    @FXML
    void showModifyUserAccount( ) {

    }

    public void reset() {
        try {
            // create Teams model
            users = new UserAdapter(connection, true);
            displayAlert("User table has been created");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    public void loginAction(){
        //enable all menu items
//        setMenuItems(true);
        userInfoMenu.setVisible(true);
    }
}
