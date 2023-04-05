package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AccountCategoryAdapter {

    Connection connection;

    public AccountCategoryAdapter(Connection conn, boolean reset) throws SQLException {
        connection = conn;
        Statement statement = connection.createStatement();
        if (reset) {
            // Remove tables if database tables have been created.
            // This will throw an exception if the tables do not exist
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                statement.execute("DROP TABLE AccountGroups");
            } catch (Exception ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
            //Finally, create the table
            finally {
                // create 2 columns: name, type
                statement.execute("CREATE TABLE AccountCategory ("
                        + "name VARCHAR(50),"
                        + "type VARCHAR(50),"
                        + "PRIMARY KEY (name))");
                populateTable();
            }
        }
    }

    private void populateTable() {
        try {
            Scanner sc = new Scanner(new File("src/main/resources/se2203b/assignments/ifinance/AccountCategory.csv"));
            sc.useDelimiter(",");   //sets the delimiter pattern
            sc.nextLine();

            while (sc.hasNext()){
                //skip the first line
                String line = sc.nextLine();

                System.out.println(line);

                String[] values = line.split(",");
                this.insertAccount(
                        values[0],
                        values[1]
                        );
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertAccount(String name, String type) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO AccountCategory VALUES ('" + name + "', '" + type + "')");
        } catch (SQLException ex) {
            System.out.println("Error inserting account: " + ex.getMessage());
        }
    }

    //return all the name of the account in ObservableList
    public ObservableList<String> getAccountName() {
        ObservableList<String> accountName = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT name FROM AccountCategory");
            while (results.next()) {
                accountName.add(results.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("Error getting account name: " + ex.getMessage());
        }
        return accountName;
    }
    public TreeItem<String> getRoot() {
        TreeItem<String> root = new TreeItem<>("Account Category");
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT name, type FROM AccountCategory");
            while (results.next()) {
                String name = results.getString("name");
                String type = results.getString("type");
                TreeItem<String> item = new TreeItem<>(name);
                root.getChildren().add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Error getting account name: " + ex.getMessage());
        }
        return root;
    }
}
