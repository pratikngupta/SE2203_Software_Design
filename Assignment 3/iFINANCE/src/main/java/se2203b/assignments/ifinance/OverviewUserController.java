package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static se2203b.assignments.ifinance.DisplayAlert.displayAlert;

public class OverviewUserController implements Initializable {

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> addressCol, emailCol, fullnameCol, usernameCol;
    @FXML
    private TableColumn<User,Integer> idCol;

    final ObservableList<User> data = FXCollections.observableArrayList();

    UserAdapter userAdapter;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        fullnameCol.setCellValueFactory(cellData -> cellData.getValue().fullnameProperty());
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        usernameCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        tableView.setItems(data);
    }

    public void buildData() {
        try {
            data.addAll(userAdapter.getUserList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    public void setModel(UserAdapter users) {
        this.userAdapter = users;
        buildData();
    }
}
