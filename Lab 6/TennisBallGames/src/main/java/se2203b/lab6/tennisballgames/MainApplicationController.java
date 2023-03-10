package se2203b.lab6.tennisballgames;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.lab6.tennisballgames.DisplayAlert.displayAlert;
/**
 *
 * @author Abdelkader Ouda
 */
public class MainApplicationController implements Initializable {

    private TeamsAdapter teams;
    private MatchesAdapter matches;
    private Connection conn;

    static boolean darkModeOn = false;

    @FXML
    private MenuBar mainMenu;

    @FXML
    public void showAbout() throws Exception {
        // Show About screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab5/tennisballgames/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void showTeamsStandings() throws Exception {
        // create Teams model
        teams = new TeamsAdapter(conn, false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TeamsStandings.fxml"));
        Parent standings = (Parent) fxmlLoader.load();

        TeamsStandingsController teamsStandingsController = (TeamsStandingsController) fxmlLoader.getController();
        teamsStandingsController.setModel(teams);

        Scene scene = new Scene(standings);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab6/tennisballgames/WesternLogo.png"));
        stage.setTitle("Current Teams Standings");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    public boolean getMode(){
        return darkModeOn;
    }

    @FXML
    public void showMatches() throws Exception {
        // create Matches model
        matches = new MatchesAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MatchesSchedule.fxml"));
        Parent matchesList = (Parent) fxmlLoader.load();
        MatchesScheduleController matchesScheduleController = (MatchesScheduleController) fxmlLoader.getController();
        matchesScheduleController.setModel(matches);

        Scene scene = new Scene(matchesList);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab5/tennisballgames/WesternLogo.png"));
        stage.setTitle("Current Matches Schedule");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addTeam() throws Exception {
        // create Teams model
        teams = new TeamsAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTeam.fxml"));
        Parent addTeam = (Parent) fxmlLoader.load();
        AddTeamController addTeamController = (AddTeamController) fxmlLoader.getController();
        addTeamController.setModel(teams);

        Scene scene = new Scene(addTeam);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab5/tennisballgames/WesternLogo.png"));
        stage.setTitle("Add New Team");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addMatch() throws Exception {

        // create Teams model
        teams = new TeamsAdapter(conn, false);
        // create matches model
        matches = new MatchesAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMatch.fxml"));
        Parent addMatch = (Parent) fxmlLoader.load();
        AddMatchController addMatchController = (AddMatchController) fxmlLoader.getController();
        addMatchController.setModel(matches, teams);

        Scene scene = new Scene(addMatch);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab5/tennisballgames/WesternLogo.png"));
        stage.setTitle("Add New Match");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void addScore() throws Exception {
        // create Teams model
        teams = new TeamsAdapter(conn, false);
        // create matches model
        matches = new MatchesAdapter(conn, false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddScore.fxml"));
        Parent addScore = (Parent) fxmlLoader.load();
        AddScoreController addScoreController = (AddScoreController) fxmlLoader.getController();
        addScoreController.setModel(matches, teams);

        Scene scene = new Scene(addScore);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab5/tennisballgames/WesternLogo.png"));
        stage.setTitle("Add Match Score");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    public void exit() throws SQLException {
        conn.close();
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void resetDB() {
        try {
            // create Teams model
            teams = new TeamsAdapter(conn, true);
            displayAlert("Teams table has been created");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
        try {
            // create Games model
            matches = new MatchesAdapter(conn, true);
            displayAlert("Matches table has been reset");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:TeamDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }

    }
}

