package se2203b.lab6.tennisballgames;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.lab6.tennisballgames.DisplayAlert.displayAlert;

/**
 *
 * @author Abdelkader Ouda
 */
public class MatchesScheduleController implements Initializable {

    //TABLE VIEW AND DATA
    private MatchesAdapter matchesAdapter;
 
    @FXML
    private TableView<Matches> tableView;
    @FXML
    private TableColumn<Matches, Integer> matchNumberCol;
    @FXML
    private TableColumn<Matches, String> homeTeamCol;
    @FXML
    private TableColumn<Matches, String> visitorTeamCol;
    @FXML
    private TableColumn<Matches, Integer> homeScoreCol;
    @FXML
    private TableColumn<Matches, Integer> visitorScoreCol;

    final ObservableList<Matches> data = FXCollections.observableArrayList();
    
    public void setModel(MatchesAdapter match) {
        matchesAdapter = match;
        buildData();
    }

    public void buildData() {
        try {
            data.addAll(matchesAdapter.getMatchesList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matchNumberCol.setCellValueFactory(cellData -> cellData.getValue().matchNumberProperty().asObject());
        homeTeamCol.setCellValueFactory(cellData -> cellData.getValue().homeTeamProperty());
        visitorTeamCol.setCellValueFactory(cellData -> cellData.getValue().visitorTeamProperty());
        homeScoreCol.setCellValueFactory(cellData -> cellData.getValue().homeTeamScoreProperty().asObject());
        visitorScoreCol.setCellValueFactory(cellData -> cellData.getValue().visitorTeamScoreProperty().asObject());

        tableView.setItems(data);
    }    
    
}
