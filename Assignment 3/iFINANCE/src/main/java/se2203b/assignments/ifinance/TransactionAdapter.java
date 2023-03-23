package se2203b.assignments.ifinance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionAdapter {
    static Connection connection;
    public TransactionAdapter(Connection connection, boolean reset) throws SQLException {

        //create table if not exists with the following columns: groupID, groupName, groupElement, groupParent
        //if reset is true, drop the table and recreate it

        if (reset) {
            // create the table
            Statement stmt = connection.createStatement();

            try {
                // remove the table if it exists
                stmt.execute("DROP TABLE Transactions");
            } catch (SQLException ex) {
                // no need to report an error
                // the table simply did not exist
            } finally {
                // create the table with userName, Password, isAdmin, isLogged

                //olumns: transactionID, transactionName, transactionAmount, transactionDate, transactionType
                stmt.execute("CREATE TABLE Transactions (transactionID int, transactionName varchar(255), transactionAmount double, transactionDate date, transactionType varchar(255))");

                // populate the table with an admin user
                populateSample();
            }

        }

    }

    private void populateSample() {

    }

    public void addTransaction(int transactionID, String transactionName, double transactionAmount, String transactionDate, String transactionType) throws SQLException {
        Statement stmt = connection.createStatement();

        //columns: transactionID, transactionName, transactionAmount, transactionDate, transactionType
        stmt.execute("INSERT INTO Transactions (transactionID, transactionName, transactionAmount, transactionDate, transactionType) VALUES (" + transactionID + ", " + transactionName + ", " + transactionAmount + ", " + transactionDate + ", " + transactionType + ")");
    }

    public void deleteTransaction(int transactionID) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("DELETE FROM Transactions WHERE transactionID = " + transactionID);
    }

    public void updateTransaction(int transactionID, String transactionName, double transactionAmount, String transactionDate, String transactionType) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("UPDATE Transactions SET transactionName = " + transactionName + ", transactionAmount = " + transactionAmount + ", transactionDate = " + transactionDate + ", transactionType = " + transactionType + " WHERE transactionID = " + transactionID);
    }

    public void getTransaction(int transactionID) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Transactions FROM Transactions WHERE transactionID = " + transactionID);
    }

    public void getTransactions() throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Transactions FROM Transactions");
    }

    public void getTransactionsByAccount(int accountID) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Transactions FROM Transactions WHERE accountID = " + accountID);
    }

    public void getTransactionsByDate(String transactionDate) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Transactions FROM Transactions WHERE transactionDate = " + transactionDate);
    }

    public void getTransactionsByType(String transactionType) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Transactions FROM Transactions WHERE transactionType = " + transactionType);
    }

    public void getTransactionsByAmount(double transactionAmount) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Transactions FROM Transactions WHERE transactionAmount = " + transactionAmount);
    }

    public void getTransactionsByAccountAndDate(int accountID, String transactionDate) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.execute("SELECT Transactions FROM Transactions WHERE accountID = " + accountID + " AND transactionDate = " + transactionDate);
    }
}
