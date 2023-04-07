package se2203b.assignments.ifinance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountCategoryAdapter {

    Connection connection;

    public AccountCategoryAdapter(Connection conn, Boolean reset) throws SQLException {
        this.connection = conn;
        Statement stm = conn.createStatement();
        if (reset) {
            try {
                Statement stmt = conn.createStatement();
                stmt.execute("DROP TABLE AccountCategory");
            } catch (SQLException ex) {

            }

        }
        try {

            stm.execute("CREATE TABLE AccountCategory ("
                    + "name VARCHAR(30) NOT NULL PRIMARY KEY,"
                    + "type VARCHAR(20) NOT NULL"
                    + ")");
            populateSample();
        } catch (SQLException ex) {

        }
    }

    public void populateSample() throws SQLException {
        addAccCategory("Assets", "Debit");
        addAccCategory("Liabilities", "Credit");
        addAccCategory("Income", "Credit");
        addAccCategory("Expenses", "Debit");
    }


    public AccountCategory checkList(String name) throws SQLException {
        AccountCategory account = new AccountCategory();
        Statement stmt = connection.createStatement();


        ResultSet rs = stmt.executeQuery("SELECT * FROM AccountCategory WHERE name = '" + name + "'");


        while (rs.next()) {

            account.setName(rs.getString("name"));
            account.setType(rs.getString("type"));
        }
        return account;
    }

    public ObservableList<String> getAccountCatList() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM AccountCategory");
        ObservableList<String> s = FXCollections.observableArrayList();
        while (rs.next()) {
            s.add(rs.getString("name"));
        }
        return s;
    }


    public void addAccCategory(String name, String type) throws SQLException {
        Statement stm = connection.createStatement();
        stm.execute("INSERT INTO AccountCategory VALUES ('" + name + "', '" + type + "')");
    }


}