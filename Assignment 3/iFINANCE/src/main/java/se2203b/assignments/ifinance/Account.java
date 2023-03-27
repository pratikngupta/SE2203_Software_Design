package se2203b.assignments.ifinance;

import javafx.beans.property.*;

public class Account {


    //stmt.execute("CREATE TABLE Accounts (accountID int, accountName varchar(255), closingBalance double, openingBalance double, accountType varchar(255))");

    //use  javafx.beans.property.*;

    private IntegerProperty accountID;
    private StringProperty accountName;
    private DoubleProperty closingBalance;
    private DoubleProperty openingBalance;
    private StringProperty accountType;

    public Account(int accountID, String accountName, double closingBalance, double openingBalance, String accountType) {
        this.accountID = new SimpleIntegerProperty(accountID);
        this.accountName = new SimpleStringProperty(accountName);
        this.closingBalance = new SimpleDoubleProperty(closingBalance);
        this.openingBalance = new SimpleDoubleProperty(openingBalance);
        this.accountType = new SimpleStringProperty(accountType);
    }

    public int getAccountID() {
        return accountID.get();
    }

    public IntegerProperty accountIDProperty() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID.set(accountID);
    }

    public String getAccountName() {
        return accountName.get();
    }

    public StringProperty accountNameProperty() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }

    public double getClosingBalance() {
        return closingBalance.get();
    }

    public DoubleProperty closingBalanceProperty() {
        return closingBalance;
    }

    public void setClosingBalance(double closingBalance) {
        this.closingBalance.set(closingBalance);
    }

    public double getOpeningBalance() {
        return openingBalance.get();
    }

    public DoubleProperty openingBalanceProperty() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance.set(openingBalance);
    }

    public String getAccountType() {
        return accountType.get();
    }

    public StringProperty accountTypeProperty() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType.set(accountType);
    }
}
