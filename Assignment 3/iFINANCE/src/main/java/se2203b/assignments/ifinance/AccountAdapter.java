package se2203b.assignments.ifinance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountAdapter {
    static Connection connection;
    public AccountAdapter(Connection connection, boolean reset) throws SQLException {
        //create table if not exists with the following columns: groupID, groupName, groupElement, groupParent
        //if reset is true, drop the table and recreate it

        if (reset) {
            // create the table
            Statement stmt = connection.createStatement();

            try {
                // remove the table if it exists
                stmt.execute("DROP TABLE Users");
            } catch (SQLException ex) {
                // no need to report an error
                // the table simply did not exist
            } finally {
                // create the table with userName, Password, isAdmin, isLogged

                //olumns: accountID, accountName, closingBalance, openingBalance, accountType
                stmt.execute("CREATE TABLE Accounts (accountID int, accountName varchar(255), closingBalance double, openingBalance double, accountType varchar(255))");

                // populate the table with an admin user
                populateSample();
            }

        }
    }

    public void populateSample() throws SQLException {
        Statement stmt = connection.createStatement();

    }

    public void addAccount(int accountID, String accountName, double closingBalance, double openingBalance, String accountType) throws SQLException {
        Statement stmt = connection.createStatement();

        //columns: accountID, accountName, closingBalance, openingBalance, accountType
        stmt.execute("INSERT INTO Accounts (accountID, accountName, closingBalance, openingBalance, accountType) VALUES (" + accountID + ", " + accountName + ", " + closingBalance + ", " + openingBalance + ", " + accountType + ")");
    }

    public void deleteAccount(int accountID) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("DELETE FROM Accounts WHERE accountID = " + accountID);
    }

    public void updateAccount(int accountID, String accountName, double closingBalance, double openingBalance, String accountType) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("UPDATE Accounts SET accountName = " + accountName + ", closingBalance = " + closingBalance + ", openingBalance = " + openingBalance + ", accountType = " + accountType + " WHERE accountID = " + accountID);
    }

    public void getAccount(int accountID) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT * FROM Accounts WHERE accountID = " + accountID);
    }

    public void getAccounts() throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT * FROM Accounts");
    }

    public void getAccountsByType(String accountType) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT * FROM Accounts WHERE accountType = " + accountType);
    }

    public void getAccountsByBalance(double balance) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT * FROM Accounts WHERE closingBalance = " + balance);
    }

    public void getAccountsByBalanceRange(double min, double max) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT * FROM Accounts WHERE closingBalance BETWEEN " + min + " AND " + max);
    }
}
