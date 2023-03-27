package se2203b.assignments.ifinance;
import javafx.beans.property.*;
public class Transaction {
    //this is model class for TransactionAdapter class

    //stmt.execute("CREATE TABLE Transactions (transactionID int, transactionDate date, transactionAmount double, transactionDescription varchar(255), transactionAccount int, transactionGroup int)");

    private IntegerProperty transactionID;
    private ObjectProperty transactionDate;
    private DoubleProperty transactionAmount;
    private StringProperty transactionDescription;
    private IntegerProperty transactionAccount;
    private IntegerProperty transactionGroup;

    public Transaction(int transactionID, Object transactionDate, double transactionAmount, String transactionDescription, int transactionAccount, int transactionGroup) {
        this.transactionID = new SimpleIntegerProperty(transactionID);
        this.transactionDate = new SimpleObjectProperty(transactionDate);
        this.transactionAmount = new SimpleDoubleProperty(transactionAmount);
        this.transactionDescription = new SimpleStringProperty(transactionDescription);
        this.transactionAccount = new SimpleIntegerProperty(transactionAccount);
        this.transactionGroup = new SimpleIntegerProperty(transactionGroup);
    }

    public int getTransactionID() {
        return transactionID.get();
    }

    public IntegerProperty transactionIDProperty() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID.set(transactionID);
    }

    public Object getTransactionDate() {
        return transactionDate.get();
    }

    public ObjectProperty transactionDateProperty() {
        return transactionDate;
    }

    public void setTransactionDate(Object transactionDate) {
        this.transactionDate.set(transactionDate);
    }

    public double getTransactionAmount() {
        return transactionAmount.get();
    }

    public DoubleProperty transactionAmountProperty() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount.set(transactionAmount);
    }

    public String getTransactionDescription() {
        return transactionDescription.get();
    }

    public StringProperty transactionDescriptionProperty() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription.set(transactionDescription);
    }

    public int getTransactionAccount() {
        return transactionAccount.get();
    }

    public IntegerProperty transactionAccountProperty() {
        return transactionAccount;
    }

    public void setTransactionAccount(int transactionAccount) {
        this.transactionAccount.set(transactionAccount);
    }

    public int getTransactionGroup() {
        return transactionGroup.get();
    }

    public IntegerProperty transactionGroupProperty() {
        return transactionGroup;
    }

    public void setTransactionGroup(int transactionGroup) {
        this.transactionGroup.set(transactionGroup);
    }
}
