package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AccountGroupsAdapter {
    Connection connection;
    String tableName = "";

    public AccountGroupsAdapter(Connection conn) throws SQLException {
        connection = conn;
    }

    public AccountGroupsAdapter(Connection conn, Boolean reset, int userID) throws SQLException {
        connection = conn;
        Statement stmt = connection.createStatement();
        tableName = "Groups" + userID;


        if (reset) {
            deleteTable(tableName);
        }

        try {
            stmt.execute("CREATE TABLE " + tableName + " ("
                    + "id INT NOT NULL PRIMARY KEY,"
                    + "name VARCHAR(60) NOT NULL,"
                    + "parentId INT REFERENCES " + tableName + "(id),"
                    + "element VARCHAR(30) REFERENCES AccountCategory(name)"
                    + ")");

        } catch (SQLException ex) {

        }
        if (isTableEmpty()) {
            populateTable();
        }
    }

    private boolean isTableEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName;
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return true;
    }

    public void deleteTable(String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        try {
            stmt.execute("DROP TABLE " + tableName);
        } catch (SQLException ex) {

        }
    }


    public void populateRecord(int ID, String groupName, int parentGroup, String accountElement) throws SQLException {
        Statement stmt = connection.createStatement();

        if (parentGroup == 0) {
            try {
                stmt.execute("INSERT INTO " + tableName + " VALUES (" + ID + ", '" + groupName + "', " + null + ", '" + accountElement + "')");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                stmt.execute("INSERT INTO " + tableName + " VALUES (" + ID + ", '" + groupName + "', " + parentGroup + ", '" + accountElement + "')");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public void populateTable() throws SQLException {
        populateRecord(1, "Fixed assets", 0, "Assets");
        populateRecord(2, "Investments", 0, "Assets");
        populateRecord(3, "Branch/divisions", 0, "Assets");
        populateRecord(4, "Cash in hand", 0, "Assets");
        populateRecord(5, "Bank accounts", 0, "Assets");
        populateRecord(6, "Deposits (assets)", 0, "Assets");
        populateRecord(7, "Advances (assets)", 0, "Assets");
        populateRecord(8, "Capital account", 0, "Liabilities");
        populateRecord(9, "Long term loans", 0, "Liabilities");
        populateRecord(10, "Current liabilities", 0, "Liabilities");
        populateRecord(11, "Reserves and surplus", 0, "Liabilities");
        populateRecord(12, "Sales account", 0, "Income");
        populateRecord(13, "Purchase account", 0, "Expenses");
        populateRecord(14, "Expenses (direct)", 0, "Expenses");
        populateRecord(15, "Expenses (indirect)", 0, "Expenses");
        populateRecord(16, "Secured loans", 9, "Liabilities");
        populateRecord(17, "Unsecured loans", 9, "Liabilities");
        populateRecord(18, "Duties taxes payable", 10, "Liabilities");
        populateRecord(19, "Provisions", 10, "Liabilities");
        populateRecord(20, "Sundry creditors", 10, "Liabilities");
        populateRecord(21, "Bank od & limits", 10, "Liabilities");
    }


    public void insertRecord(Group group, String text) throws SQLException {
        if (group.getParent() == null) {
            String query = "INSERT INTO " + tableName + " VALUES (?, ?, NULL, ?)";
            //The ? placeholders are used for the values that need to be inserted into the query, and the corresponding values are set using the setInt() and setString()
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, group.getID());
                stmt.setString(2, text);
                stmt.setString(3, group.getElement().getName());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("ERROR3: " + ex.getMessage());
            }
        } else {
            String query = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?)";
            //The ? placeholders are used for the values that need to be inserted into the query, and the corresponding values are set using the setInt() and setString()
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, group.getID());
                stmt.setString(2, text);
                stmt.setInt(3, group.getParent().getID());
                stmt.setString(4, group.getElement().getName());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("ERROR4: " + ex.getMessage());
            }
        }
    }


    public void deleteRecord(Group group) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + group.getID());
    }


    public int getMaxId() throws SQLException {
        int num = 0;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM " + tableName);
        if (rs.next()) num = rs.getInt(1);
        return num;
    }

    public void updateRecord(Group group, String text) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE " + tableName + " SET name = '" + text + "' WHERE id = " + group.getID());
    }


    //Should be recursive
    public Group findRecord(int id, AccountCategoryAdapter aca) throws SQLException {
        Group group = new Group();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id);

        while (rs.next()) {
            group.setID(rs.getInt("id"));
            group.setName(rs.getString("name"));

            int parentId = rs.getInt("parentId");
            if (!rs.wasNull()) {
                Group parentGroup = findRecord(parentId, aca);
                group.setParent(parentGroup);
            } else {
                group.setParent(null);
            }

            String elementId = rs.getString("element");
            if (!rs.wasNull()) {
                AccountCategory element = aca.checkList(elementId);
                group.setElement(element);
            } else {
                group.setElement(null);
            }
        }
        return group;
    }


    public String groupName(int id) throws SQLException {
        String name = "";
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT name FROM " + tableName + " WHERE id = " + id);
        if (rs.next()) {
            name = rs.getString("name");
        }
        return name;
    }

    public ObservableList<Group> groupList(AccountCategoryAdapter aca) {
        ObservableList<Group> groupsList = FXCollections.observableArrayList();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                Group group = new Group();
                group.setID(rs.getInt("id"));
                group.setName(rs.getString("name"));

                int parentId = rs.getInt("parentId");
                if (!rs.wasNull()) {
                    Group parentGroup = findRecord(parentId, aca);
                    group.setParent(parentGroup);
                } else {
                    group.setParent(null);
                }

                String elementId = rs.getString("element");
                if (!rs.wasNull()) {

                    AccountCategory element = aca.checkList(elementId);
                    group.setElement(element);
                } else {
                    group.setElement(null);
                }

                groupsList.add(group);
            }
        } catch (SQLException ex) {
            System.out.println("GroupAdapter/getGroupsList: ERROR -->" + ex.getMessage());
        }
        return groupsList;
    }
}
