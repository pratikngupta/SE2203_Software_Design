package se2203b.assignments.ifinance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Abdelkader Ouda
 */
public class IFinanceController implements Initializable {

    @FXML
    private Menu userMenuItem, chartOfAccountMenuItem, manageUserAccountsMenu, manageAccountGroupsMenuItem, doubleEntryMenuItem, financialReportMenuItem, fileMenu;

    @FXML
    private MenuItem closeMenuItem, loginMenuItem, logoutMenuItem;

    @FXML
    private MenuBar mainMenu;

    private Connection conn;

    private UserAccountAdapter account;

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

    public void login() throws Exception {
        account = new UserAccountAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("Login-view.fxml"));

        // create the root node
        Parent Login = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        loginController.setIFinanceController(this);
        loginController.setUserAccountModel(account);

        // create new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(Login));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Login");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void changePassword() throws Exception {
        account = new UserAccountAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("ChangePassword-view.fxml"));

        // create the root node
        Parent changePassword = fxmlLoader.load();
        ChangePasswordController changePasswordController = fxmlLoader.getController();
        changePasswordController.setIFinanceController(this);
        changePasswordController.setUserAccountModel(account);

        // create new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(changePassword));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Change Password");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void createUserAccount() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("CreateUserAccount-view.fxml"));
        // create the root node
        Parent newUser = fxmlLoader.load();
        CreateUserAccountController createUserAccountController = fxmlLoader.getController();
        createUserAccountController.setIFinanceController(this);
        createUserAccountController.setAdapters(new UserAccountAdapter(conn, false), new NonAdminUserAdapter(conn, false));
        // create new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(newUser));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Create User Account");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void modifyUserProfile() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("ModifyUserProfile-view.fxml"));
        // create the root node
        Parent aUser = fxmlLoader.load();
        ModifyUserProfileController modifyUserProfileController = fxmlLoader.getController();
        modifyUserProfileController.setIFinanceController(this);
        modifyUserProfileController.setAdapters(new UserAccountAdapter(conn, false), new NonAdminUserAdapter(conn, false));
        // create new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(aUser));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Modify User Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void deleteUserProfile() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("DeleteUserProfile-view.fxml"));
        // create the root node
        Parent toDelete = fxmlLoader.load();
        DeleteUserProfileController deleteUserProfileController = fxmlLoader.getController();
        deleteUserProfileController.setIFinanceController(this);
        deleteUserProfileController.setAdapters(new UserAccountAdapter(conn, false), new NonAdminUserAdapter(conn, false));
        // create new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(toDelete));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Modify User Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void logout() {
        disableMenuItems();
    }

    public void exit() {
        // Get current stage reference
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        // Close stage
        stage.close();
    }

    public void enableAdminControls() {
        chartOfAccountMenuItem.setDisable(true);
        doubleEntryMenuItem.setDisable(true);
        financialReportMenuItem.setDisable(true);
        loginMenuItem.setDisable(true);
        manageAccountGroupsMenuItem.setDisable(true);

        fileMenu.setDisable(false);
        logoutMenuItem.setDisable(false);
        mainMenu.setDisable(false);
        closeMenuItem.setDisable(false);
        userMenuItem.setVisible(true);
        manageUserAccountsMenu.setDisable(false);
    }

    public void enableNonAdminControls() {
        chartOfAccountMenuItem.setDisable(false);
        doubleEntryMenuItem.setDisable(false);
        financialReportMenuItem.setDisable(false);
        manageAccountGroupsMenuItem.setDisable(false);
        fileMenu.setDisable(false);
        logoutMenuItem.setDisable(false);
        mainMenu.setDisable(false);
        closeMenuItem.setDisable(false);
        userMenuItem.setVisible(true);

        loginMenuItem.setDisable(true);
        manageUserAccountsMenu.setDisable(true);
    }

    public void disableMenuItems() {
        chartOfAccountMenuItem.setDisable(true);
        doubleEntryMenuItem.setDisable(true);
        financialReportMenuItem.setDisable(true);
        manageAccountGroupsMenuItem.setDisable(true);
        logoutMenuItem.setDisable(true);
        userMenuItem.setVisible(false);
        manageUserAccountsMenu.setDisable(true);

        fileMenu.setDisable(false);
        mainMenu.setDisable(false);
        closeMenuItem.setDisable(false);
        loginMenuItem.setDisable(false);
    }

    public String getUserName() {
        return userMenuItem.getText();
    }

    // set the logged-in username into the menu item
    public void setUserName(String userName) {
        userMenuItem.setText(userName);
    }

    @FXML
    void openAccountGroups() throws Exception {
        //account = new UserAccountAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(IFinanceController.class.getResource("AccountGroups.fxml"));

        // create the root node
        Parent accountGroup = fxmlLoader.load();
        AccountGroupsController accountGroupsController = fxmlLoader.getController();
        accountGroupsController.setIFinanceController(this);

        NonAdminUserAdapter nonAdminUserAdapter = new NonAdminUserAdapter(conn, false);
        int userID = nonAdminUserAdapter.findRecord(getUserName()).getID();
        accountGroupsController.Adapters(new UserAccountAdapter(conn, false), nonAdminUserAdapter, new AccountCategoryAdapter(conn, false), new GroupAdapter(conn, userID, false));

        // create new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(accountGroup));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
        stage.setTitle("Manage Account Groups");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void displayAlert(String msg) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(IFinanceApplication.class.getResource("Alert.fxml"));
            // create the root node
            Parent alertWindow = fxmlLoader.load();
            AlertController alertController = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(alertWindow));
            stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/ifinance/WesternLogo.png"));
            alertController.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex1) {
            System.out.println("Error in Display Alert " + ex1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ImageView face = new ImageView(new Image("file:src/main/resources/se2203b/assignments/ifinance/UserIcon.png"));
        face.setFitWidth(20);
        face.setFitHeight(20);
        userMenuItem.setGraphic(face);
        disableMenuItems();

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iFinanceDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);
            // Create the admin account if it is not already in the database
            new AdministratorAdapter(conn, false);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }

}
