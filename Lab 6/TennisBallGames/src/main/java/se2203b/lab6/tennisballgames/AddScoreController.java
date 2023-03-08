package se2203b.lab6.tennisballgames;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddScoreController implements Initializable {

    public TextField homeTeamScore;
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
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        try {
            //get index of selected match
            int index = matchSelectorBox.getSelectionModel().getSelectedIndex() + 1;
            matchesAdapter.setTeamsScore(index, Integer.parseInt(homeTeamScore.getText()), Integer.parseInt(visitorTeamScore.getText()));

            // get Home Team Name
            String homeTeamName = matchesAdapter.getHomeTeamName(index, "home");
            // get Visitor Team Name
            String visitorTeamName = matchesAdapter.getHomeTeamName(index, "visitor");

            teamsAdapter.setStatus(homeTeamName, visitorTeamName, Integer.parseInt(homeTeamScore.getText()), Integer.parseInt(visitorTeamScore.getText()));

            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String msg) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab6/tennisballgames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

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
