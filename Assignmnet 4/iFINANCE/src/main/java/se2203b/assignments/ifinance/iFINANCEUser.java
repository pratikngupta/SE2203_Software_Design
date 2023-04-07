package se2203b.assignments.ifinance;

import javafx.beans.property.*;

public abstract class iFINANCEUser {

    private final ObjectProperty<UserAccount> uAccount
            = new SimpleObjectProperty(new UserAccount());
    private IntegerProperty id;
    private StringProperty fullName;

    public IntegerProperty idProperty() {
        return this.id;
    }

    public int getID() {
        return this.id.get();
    }

    //set and get methods
    // id property
    public void setID(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public StringProperty fullNameProperty() {
        return this.fullName;
    }

    public String getFullName() {
        return this.fullName.get();
    }

    // name property
    public void setFullName(String name) {
        this.fullName = new SimpleStringProperty(name);
    }

    public ObjectProperty<UserAccount> uAccountProperty() {
        return this.uAccount;
    }

    public UserAccount getuAccount() {
        return this.uAccount.get();
    }

    // userAccount Property
    public void setuAccount(UserAccount uAccount) {

        this.uAccount.set(uAccount);
    }

}
