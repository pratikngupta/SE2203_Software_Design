package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class GroupAdapter {
    Connection connection;
    String tableName = "";

    public GroupAdapter(Connection conn, Boolean reset, int userID) throws SQLException, FileNotFoundException {
        connection = conn;
        Statement stmt = connection.createStatement();
        tableName = "Groups" + userID;

        reset = true;
        if (reset) {
            dropTable(tableName);
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

    public void dropTable(String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        try {
            stmt.execute("DROP TABLE " + tableName);
        } catch (SQLException ex) {

        }
    }

    public void insertGroupDetails(int ID, String groupName, int parentGroup, String accountElement) throws SQLException {
        Statement statement = connection.createStatement();

        if (parentGroup != 0) {
            statement.execute("INSERT INTO " + tableName + " VALUES (" + ID + ", '" + groupName + "', " + parentGroup + ", '" + accountElement + "')");
            return;
        }

        statement.execute("INSERT INTO " + tableName + " VALUES (" + ID + ", '" + groupName + "', " + null + ", '" + accountElement + "')");
    }

    public void populateTable() throws SQLException, FileNotFoundException {

        Scanner sc = new Scanner(new File("src/main/resources/se2203b/assignments/ifinance/AccountGroup.csv"));
        sc.useDelimiter(",");   //sets the delimiter pattern
        sc.nextLine();

        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] values = line.split(",");
            this.insertGroupDetails(
                    Integer.parseInt(values[0]),
                    values[1],
                    Integer.parseInt(values[2]),
                    values[3]);
        }

        sc.close();
    }

    public void insertGroup(Group group, String text) throws SQLException {
        Statement statement = connection.createStatement();
        if (group.getParent() == null) {
            statement.executeUpdate("INSERT INTO " + tableName + " VALUES (" + group.getID() + ", '" + text + "', NULL, '" + group.getElement().getName() + "')");
        } else {
            statement.executeUpdate("INSERT INTO " + tableName + " VALUES (" + group.getID() + ", '" + text + "', " + group.getParent().getID() + ", '" + group.getElement().getName() + "')");
        }
    }

    public void deleteGroup(Group group) throws SQLException {
        Statement statement = connection.createStatement();
        //first delete the connection from the parent group
        statement.executeUpdate("UPDATE " + tableName + " SET parentId = NULL WHERE parentId = " + group.getID());
        //then delete the group
        statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + group.getID());
    }

    public int getMaxId() throws SQLException {
        //get the max id from the table
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM " + tableName);
        int maxId = 0;
        if (rs.next()) {
            maxId = rs.getInt(1);
        }
        return maxId;
    }

    public void updateRecord(Group group, String text) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE " + tableName + " SET name = '" + text + "' WHERE id = " + group.getID());
    }

    public Group findRecord(int id, AccountCategoryAdapter accountCategoryAdapter) throws SQLException {
        Group group = new Group();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id);

        while (rs.next()) {
            group.setID(rs.getInt("id"));
            group.setName(rs.getString("name"));

            int parentId = rs.getInt("parentId");
            if (!rs.wasNull()) {
                Group parentGroup = findRecord(parentId, accountCategoryAdapter);
                group.setParent(parentGroup);
            } else {
                group.setParent(null);
            }

            String elementId = rs.getString("element");
            if (!rs.wasNull()) {
                AccountCategory element = accountCategoryAdapter.checkList(elementId);
                group.setElement(element);
            } else {
                group.setElement(null);
            }
        }
        return group;
    }


    public String getGroupName(int id) throws SQLException {
        String name = "";
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT name FROM " + tableName + " WHERE id = " + id);
        if (rs.next()) {
            name = rs.getString("name");
        }
        return name;
    }

    public ObservableList<Group> getGroupList(AccountCategoryAdapter adapter) throws SQLException {
        ObservableList<Group> groupsList = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            Group group = new Group();
            group.setID(rs.getInt("id"));
            group.setName(rs.getString("name"));

            int parentId = rs.getInt("parentId");
            if (!rs.wasNull()) {
                Group parentGroup = findRecord(parentId, adapter);
                group.setParent(parentGroup);

            } else {
                group.setParent(null);
            }

            String elementId = rs.getString("element");
            if (!rs.wasNull()) {
                AccountCategory element = adapter.checkList(elementId);
                group.setElement(element);
            } else {
                group.setElement(null);
            }

            groupsList.add(group);
        }

        return groupsList;
    }
}
