package se2203b.assignments.ifinance;

import javafx.scene.control.TreeView;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountGroupsController {
    private TreeView<String> mainView;

    private AccountCategoryAdapter accountCategoryAdapter;
    private GroupAdapter groupAdapter;

    Connection connection;


    public void setIFinanceController(IFinanceController iFinanceController) {
    }

    public void setAdapters(Connection connection) throws SQLException {
        this.connection = connection;

        accountCategoryAdapter = new AccountCategoryAdapter(connection, false);
        groupAdapter = new GroupAdapter(connection, false);
    }

    public void setMainView() {
        mainView.setRoot(accountCategoryAdapter.getRoot());
    }

    //initialize the tree view
    public void initialize() {
    }
}
