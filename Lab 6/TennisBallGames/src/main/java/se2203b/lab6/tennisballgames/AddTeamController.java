package se2203b.lab6.tennisballgames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.lab6.tennisballgames.DisplayAlert.displayAlert;

/**
 *
 * @author Abdelkader Ouda
 */
public class AddTeamController implements Initializable {

    @FXML
    Button cancelBtn;

    @FXML
    Button saveBtn;
    
    @FXML
    TextField teamName;

    private TeamsAdapter teamsAdapter;

    public void setModel(TeamsAdapter team) {
        teamsAdapter = team;
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void save() {
        try {
            teamsAdapter.insertTeam(teamName.getText());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
            
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
