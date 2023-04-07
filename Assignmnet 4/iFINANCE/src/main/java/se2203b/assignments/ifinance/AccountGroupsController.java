package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;

public class AccountGroupsController implements Initializable {

    @FXML
    public TextField GroupField;
    @FXML
    public TreeView<String> tree;
    @FXML
    public Button saveBtn;
    @FXML
    public TreeItem<String> rootItem = new TreeItem<>("Account Groups");
    public ContextMenu Menu = new ContextMenu();
    private final MenuItem add = new MenuItem("Add New Group");
    private final MenuItem change = new MenuItem("Change Group Name");
    private final MenuItem delete = new MenuItem("Delete Group");
    public AccountCategoryAdapter accountCategoryAdapter;
    public IFinanceController iFinanceController;
    public UserAccountAdapter userAccountAdapter;
    public NonAdminUserAdapter nonAdminUserAdapter;
    public ObservableList<Group> groupList = FXCollections.observableArrayList();
    public GroupAdapter groupAdapter;
    public String MenuCheck = "";

    public void setIFinanceController(IFinanceController controller) {
        iFinanceController = controller;
    }

    public void Adapters(UserAccountAdapter userAcc, NonAdminUserAdapter userProfile, AccountCategoryAdapter accountCategory, GroupAdapter group, String userName) throws SQLException {
        userAccountAdapter = userAcc;
        nonAdminUserAdapter = userProfile;
        accountCategoryAdapter = accountCategory;
        groupAdapter = group;

        groupList = groupAdapter.getGroupList(accountCategoryAdapter);

        rootItem.setExpanded(true);
        ObservableList<String> accounts = accountCategoryAdapter.getAccountCatList();
        accounts.forEach(category -> rootItem.getChildren().add(new TreeItem<>(category)));

        groupList.forEach(item -> {
            if (item.getParent() == null) {
                addToTable(rootItem, item.getName(), item.getElement().getName());
            } else {
                try {
                    addToTable(rootItem, item.getName(), groupAdapter.getGroupName(item.getParent().getID()));
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


    }

    @FXML
    void save() throws SQLException {
        groupList = groupAdapter.getGroupList(accountCategoryAdapter);
        if (!GroupField.getText().isEmpty()) {
            String inputText = GroupField.getText();
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();

            if (MenuCheck.equals("add")) {
                TreeItem<String> newGroupTreeItem = new TreeItem<>(inputText);
                selectedItem.getChildren().add(newGroupTreeItem);
                boolean toggle = true;
                for (Group group1 : groupList) {
                    if (group1.getName().equals(selectedItem.getValue())) {
                        Group newGroup = new Group((groupAdapter.getMaxId() + 1), inputText, group1, group1.getElement());
                        groupAdapter.insertGroup(newGroup, newGroup.getName());
                    }
                    if (group1.getElement().getName().equals(selectedItem.getValue()) && toggle) {
                        Group newGroup = new Group(groupAdapter.getMaxId() + 1, inputText, null, group1.getElement());
                        groupAdapter.insertGroup(newGroup, inputText);
                        toggle = false;
                    }
                }
            } else if (MenuCheck.equals("change")) {//System.out.println("Change Group Name: " + inputText);
                String toEditStr = selectedItem.getValue();
                for (Group list : groupList) {
                    if (list.getName().equals(toEditStr)) {
                        groupAdapter.updateRecord(list, inputText);
                    }
                }
                // Update the value of the selected TreeItem
                selectedItem.setValue(inputText);
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
                for (Group list : groupList) {
                    if (list.getName().equals(delete)) {
                        try {
                            groupAdapter.deleteGroup(list);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
                selectedItem.getParent().getChildren().remove(selectedItem);
            }
        });
    }

}
