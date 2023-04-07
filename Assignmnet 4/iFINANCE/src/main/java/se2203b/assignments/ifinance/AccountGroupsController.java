package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

public class AccountGroupsController implements Initializable {

    private final Map<TreeItem<String>, Group> treeGroup = new HashMap<>();
    @FXML
    public TextField GroupField;
    @FXML
    public TreeView<String> tree;
    @FXML
    public Button saveBtn;
    @FXML
    public TreeItem<String> rootItem = new TreeItem<>("Account Groups");
    public ContextMenu Menu = new ContextMenu();
    public MenuItem add = new MenuItem("Add New Group");
    public MenuItem change = new MenuItem("Change Group Name");
    public MenuItem delete = new MenuItem("Delete Group");
    public AccountCategoryAdapter accountCategoryAdapter;
    public IFinanceController iFinanceController;
    public UserAccountAdapter userAccountAdapter;
    public NonAdminUserAdapter nonAdminUserAdapter;
    public ObservableList<Group> glist = FXCollections.observableArrayList();
    public GroupAdapter gAdapter;
    public String MenuCheck = "";

    public void setIFinanceController(IFinanceController controller) {
        iFinanceController = controller;
    }

    public void Adapters(UserAccountAdapter userAcc, NonAdminUserAdapter userProfile, AccountCategoryAdapter accountCategory, GroupAdapter group, String userName) throws SQLException {
        userAccountAdapter = userAcc;
        nonAdminUserAdapter = userProfile;
        accountCategoryAdapter = accountCategory;
        gAdapter = group;

        String userLog = userName;
        int userID = nonAdminUserAdapter.findRecord(userLog).getID();

        glist = gAdapter.getGroupList(accountCategoryAdapter);

        rootItem.setExpanded(true);
        ObservableList<String> accounts = accountCategoryAdapter.getAccountCatList();
        accounts.forEach(s -> rootItem.getChildren().add(new TreeItem<>(s)));

        glist.forEach(g -> {
            if (g.getParent() == null) {
                addToTable(rootItem, g.getName(), g.getElement().getName());
            } else {
                try {
                    addToTable(rootItem, g.getName(), gAdapter.getGroupName(g.getParent().getID()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        tree.setRoot(rootItem);
    }//initializes the adapter and the variables to be used in other classes

    @FXML
    void close() {

        Stage stage = (Stage) tree.getScene().getWindow();

        stage.close();
    }//closes


    public void addToTable(TreeItem<String> parent, String childName, String itemName) {
        if (parent == null) {
            return;
        }

        Stack<TreeItem<String>> stack = new Stack<>();
        stack.push(parent);

        while (!stack.isEmpty()) {
            TreeItem<String> current = stack.pop();
            if (current.getValue().equals(itemName)) {
                current.getChildren().add(new TreeItem<>(childName));
                return;
            }
            for (TreeItem<String> child : current.getChildren()) {
                stack.push(child);
            }
        }


    }//populates the treeview table

    @FXML
    void save(ActionEvent event) throws SQLException {
        glist = gAdapter.getGroupList(accountCategoryAdapter);
        if (!GroupField.getText().isEmpty()) {
            String inputText = GroupField.getText();
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            Group parentGroup = treeGroup.get(selectedItem);
            switch (MenuCheck) {
                case "add":
                    TreeItem<String> newGroupTreeItem = new TreeItem<>(inputText);
                    selectedItem.getChildren().add(newGroupTreeItem);
                    Boolean toggle = true;
                    for (Group group1 : glist) {
                        if (group1.getName().equals(selectedItem.getValue())) {

                            try {
                                Group newGroup = new Group((gAdapter.getMaxId() + 1), inputText, group1, group1.getElement());
                                gAdapter.insertGroup(newGroup, newGroup.getName());
                            } catch (SQLException e) {
                                System.out.println("Error1: " + e.getMessage());
                            }
                        }
                        if (group1.getElement().getName().equals(selectedItem.getValue()) && toggle) {
                            try {
                                Group newGroup = new Group(gAdapter.getMaxId() + 1, inputText, null, group1.getElement());
                                gAdapter.insertGroup(newGroup, inputText);
                            } catch (SQLException e) {
                                System.out.println("Error2: " + e.getMessage());
                            }
                            toggle = false;
                        }
                    }
                    break;
                case "change":
                    //System.out.println("Change Group Name: " + inputText);
                    String toEditStr = selectedItem.getValue();
                    for (Group group1 : glist) {
                        if (group1.getName().equals(toEditStr)) {
                            try {
                                gAdapter.updateRecord(group1, inputText);
                            } catch (SQLException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                    }
                    // Update the value of the selected TreeItem
                    selectedItem.setValue(inputText);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Menu.getItems().addAll(add, change, delete);

        tree.setContextMenu(Menu);

        tree.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Menu.hide();
            }
            delete.setDisable(false);
            change.setDisable(false);
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                delete.setDisable(selectedItem.getChildren().size() > 0);
                change.setDisable(selectedItem.getParent() == rootItem || selectedItem.getChildren().size() > 0);
            }
        });

        add.setOnAction(event -> {
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                GroupField.setDisable(false);
                GroupField.requestFocus();
                GroupField.clear();
                MenuCheck = "add";
            }
        });

        change.setOnAction(event -> {
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                GroupField.setDisable(false);
                GroupField.requestFocus();
                GroupField.setText(selectedItem.getValue());
                MenuCheck = "change";
            }
        });

        delete.setOnAction(event -> {
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            if (selectedItem != null && selectedItem.isLeaf()) {
                String delete = selectedItem.getValue();
                for (Group g : glist) {
                    if (g.getName().equals(delete)) {
                        try {
                            gAdapter.deleteGroup(g);
                        } catch (SQLException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                }
                selectedItem.getParent().getChildren().remove(selectedItem);
            }
        });
    }

}
