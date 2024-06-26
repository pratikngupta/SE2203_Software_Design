package se2203b.lab6.tennisballgames;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.lab6.tennisballgames.DisplayAlert.displayAlert;

public class AddScoreController implements Initializable {

    public TextField homeTeamScore;
    public AnchorPane scorePlane;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField visitorTeamScore;
    @FXML
    private ComboBox<String> matchSelectorBox;
    ObservableList<String> data = FXCollections.observableArrayList();
    private MatchesAdapter matchesAdapter;
    private TeamsAdapter teamsAdapter;

    public void setModel(MatchesAdapter match, TeamsAdapter team) throws SQLException {
        matchesAdapter = match;
        teamsAdapter = team;
        buildComboBoxData();
    }

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        try {
            if (matchSelectorBox.getSelectionModel().isEmpty()) {
                //call displayAlert method from DisplayAlert class to display error message
                displayAlert("ERROR: Please select a match");
                return;
            }
            else if (homeTeamScore.getText().isEmpty() || visitorTeamScore.getText().isEmpty()) {
                //call displayAlert method from DisplayAlert class to display error message
                if (homeTeamScore.getText().isEmpty()) {
                    displayAlert("ERROR: Home team score is empty");
                } else {
                    displayAlert("ERROR: Visitor team score is empty");
                }
                return;
            }
            else if (Integer.parseInt(homeTeamScore.getText()) < 0 || Integer.parseInt(visitorTeamScore.getText()) < 0) {
                //call displayAlert method from DisplayAlert class to display error message
                if (Integer.parseInt(homeTeamScore.getText()) < 0) {
                    displayAlert("ERROR: Home team score cannot be negative");
                } else {
                    displayAlert("ERROR: Visitor team score cannot be negative");
                }
                return;
            }

            //get index of selected match
            int index = matchSelectorBox.getSelectionModel().getSelectedIndex();
            matchesAdapter.setTeamsScore(index + 1, Integer.parseInt(homeTeamScore.getText()), Integer.parseInt(visitorTeamScore.getText()));

            //copy the array returned by getTeamsNamesList() to a local array
            String[] teams = matchesAdapter.getTeamsNamesList(index);

            teamsAdapter.setStatus(teams[0], teams[1], Integer.parseInt(homeTeamScore.getText()), Integer.parseInt(visitorTeamScore.getText()));

            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
        catch (NumberFormatException ex) {
            displayAlert("ERROR: SCORE MUST BE A NUMBER");
        }
    }

    private void buildComboBoxData() throws SQLException {
        //set the data for the combo box
        //Write an SQL statement to select all columns from the Matches table.
        data = MatchesAdapter.getMatchesNamesList();
        matchSelectorBox.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchSelectorBox.setItems(data);
    }

}
