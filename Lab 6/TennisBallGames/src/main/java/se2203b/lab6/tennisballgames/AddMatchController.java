package se2203b.lab6.tennisballgames;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.lab6.tennisballgames.DisplayAlert.displayAlert;

public class AddMatchController implements Initializable {

    public Button cancelBtn;
    @FXML
    private ComboBox<String> homeTeamBox;

    @FXML
    private ComboBox<String> visitorTeamBox;

    // The data variable is used to populate the ComboBoxs
    final ObservableList<String> data = FXCollections.observableArrayList();
    private MatchesAdapter matchesAdapter;
    private TeamsAdapter teamsAdapter;
    public void setModel(MatchesAdapter match, TeamsAdapter team) {
        matchesAdapter = match; teamsAdapter = team; buildComboBoxData();
    }

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        try {
            if (homeTeamBox.getValue().equals(visitorTeamBox.getValue())) {
                //call displayAlert method from DisplayAlert class to display error message
                displayAlert("ERROR: Home team and visitor team cannot be the same");
                return;
            }
            matchesAdapter.insertMatch(MatchesAdapter.getMax(), homeTeamBox.getValue(), visitorTeamBox.getValue());
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    public void buildComboBoxData() {
        try {
        data.addAll(teamsAdapter.getTeamsNames());
            System.out.println(data);
        }
        catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeTeamBox.setItems(data);
        visitorTeamBox.setItems(data);
    }

}
