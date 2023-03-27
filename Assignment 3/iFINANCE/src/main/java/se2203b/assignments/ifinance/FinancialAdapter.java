package se2203b.assignments.ifinance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FinancialAdapter {
    static Connection connection;
    public FinancialAdapter(Connection connection, boolean reset) throws SQLException {
        //create table if not exists with the following columns: groupID, groupName, groupElement, groupParent
        //if reset is true, drop the table and recreate it

        if (reset) {
            // create the table
            Statement stmt = connection.createStatement();

            try {
                // remove the table if it exists
                stmt.execute("DROP TABLE Financial");
            } catch (SQLException ex) {
                // no need to report an error
                // the table simply did not exist
            } finally {
                // create the table with userName, Password, isAdmin, isLogged

                //this is finical statement table columns: accountID, accountName, closingBalance, openingBalance, accountType
                stmt.execute("CREATE TABLE Financial (accountID int, accountName varchar(255), closingBalance double, openingBalance double, accountType varchar(255))");

                // populate the table with an admin user
                populateSample();
            }

        }

    }

    private void populateSample() {
    }

    public void addAccount(int accountID, String accountName, double closingBalance, double openingBalance, String accountType) throws SQLException {
        Statement stmt = connection.createStatement();

        //columns: accountID, accountName, closingBalance, openingBalance, accountType
        stmt.execute("INSERT INTO Financial (accountID, accountName, closingBalance, openingBalance, accountType) VALUES (" + accountID + ", " + accountName + ", " + closingBalance + ", " + openingBalance + ", " + accountType + ")");
    }

    public void deleteAccount(int accountID) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("DELETE FROM Financial WHERE accountID = " + accountID);
    }

    public void updateAccount(int accountID, String accountName, double closingBalance, double openingBalance, String accountType) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("UPDATE Financial SET accountName = " + accountName + ", closingBalance = " + closingBalance + ", openingBalance = " + openingBalance + ", accountType = " + accountType + " WHERE accountID = " + accountID);
    }

    public void getAccount(int accountID) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Financial FROM Financial WHERE accountID = " + accountID);
    }

    public void getAccount(String accountName) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Financial FROM Financial WHERE accountName = " + accountName);
    }

    public void getAccount(double closingBalance) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Financial FROM Financial WHERE closingBalance = " + closingBalance);
    }




}
