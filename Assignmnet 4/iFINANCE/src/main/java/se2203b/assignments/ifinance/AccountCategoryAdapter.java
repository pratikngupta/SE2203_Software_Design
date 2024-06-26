package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AccountCategoryAdapter {

    Connection connection;

    public AccountCategoryAdapter(Connection conn, Boolean reset) throws SQLException, FileNotFoundException {
        this.connection = conn;
        Statement stm = conn.createStatement();

        if (reset) {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE AccountCategory");
        }

        try {
            stm.execute("CREATE TABLE AccountCategory ("
                    + "name VARCHAR(30) NOT NULL PRIMARY KEY,"
                    + "type VARCHAR(20) NOT NULL"
                    + ")");
            populateSample();
        } catch (SQLException ignored) {

        }
    }

    public void populateSample() throws SQLException, FileNotFoundException {

        Scanner sc = new Scanner(new File("src/main/resources/se2203b/assignments/ifinance/AccountCategory.csv"));
        sc.useDelimiter(",");   //sets the delimiter pattern
        sc.nextLine();

        while (sc.hasNext()) {
            //skip the first line
            String line = sc.nextLine();
            //remove ' from the string
            String[] values = line.split(",");
            this.insertAccountCat(
                    values[0],
                    values[1]);
        }

        sc.close();
    }


    public AccountCategory getAccountObject(String name) throws SQLException {
        AccountCategory account = new AccountCategory();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM AccountCategory WHERE name = '" + name + "'");

        while (rs.next()) {
            account.setName(rs.getString("name"));
            account.setType(rs.getString("type"));
        }

        return account;
    }

    public ObservableList<String> getAccountCatList() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM AccountCategory");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            list.add(resultSet.getString("name"));
        }
        return list;
    }


    public void insertAccountCat(String name, String type) throws SQLException {
        Statement stm = connection.createStatement();
        stm.execute("INSERT INTO AccountCategory VALUES ('" + name + "', '" + type + "')");
    }
}