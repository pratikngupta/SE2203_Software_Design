package se2203b.assignments.ifinance;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserAccount {
    private final StringProperty uName;
    private final StringProperty encryptedPassword;
    private final IntegerProperty passwordExpiryTime;
    private final StringProperty passwordExpiryDate;
    private final StringProperty accountType;  // "admin" or "not-admin"

    public UserAccount() {
        this.uName = new SimpleStringProperty("");
        this.encryptedPassword = new SimpleStringProperty("");
        this.passwordExpiryTime = new SimpleIntegerProperty(0);
        this.passwordExpiryDate = new SimpleStringProperty("");
        this.accountType = new SimpleStringProperty("");
    }

    public UserAccount(String uName, String encryptedPassword, int passwordExpiryTime, String passwordExpiryDate, String accountType) {
        this.uName = new SimpleStringProperty(uName);
        this.encryptedPassword = new SimpleStringProperty(encryptedPassword);
        this.passwordExpiryTime = new SimpleIntegerProperty(passwordExpiryTime);
        this.passwordExpiryDate = new SimpleStringProperty(passwordExpiryDate);
        this.accountType = new SimpleStringProperty(accountType);
    }

    //set and get methods
    // userName property
    public void setuName(String uName) {
        this.uName.set(uName);
    }

    public StringProperty uNameProperty() {
        return this.uName;
    }

    public String getUName() {
        return this.uName.get();
    }

    public StringProperty encryptedPasswordProperty() {
        return this.encryptedPassword;
    }

    public String getEncryptedPassword() {
        return this.encryptedPassword.get();
    }

    // encryptedPassword property
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword.set(encryptedPassword);
    }

    public IntegerProperty passwordExpiryTimeProperty() {
        return this.passwordExpiryTime;
    }

    public int getPasswordExpiryTime() {
        return this.passwordExpiryTime.get();
    }

    // passwordExpiryTime Property
    public void setPasswordExpiryTime(int passwordExpiryTime) {
        this.passwordExpiryTime.set(passwordExpiryTime);
    }

    public StringProperty passwordExpiryDateProperty() {
        return this.passwordExpiryDate;
    }

    public String getPasswordExpiryDate() {
        return this.passwordExpiryDate.get();
    }

    // passwordExpiryDate property
    public void setPasswordExpiryDate(String passwordExpiryDate) {
        this.passwordExpiryDate.set(passwordExpiryDate);
    }

    public StringProperty accountTypeProperty() {
        return this.accountType;
    }

    public String getAccountType() {
        return this.accountType.get();
    }

    // userName property
    public void setAccountType(String accountType) {
        this.accountType.set(accountType);
    }
}
