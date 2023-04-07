package se2203b.assignments.ifinance;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Administrator extends iFINANCEUser {
    private final StringProperty dateCreated;

    // Constructors
    public Administrator() {
        this("", "", new UserAccount());
        setID(0);
        setFullName("");
    }

    public Administrator(String fullName, String dateCreated, UserAccount account) {
        setID(1); // We have only one admin
        setFullName(fullName);
        this.dateCreated = new SimpleStringProperty(dateCreated);
        setuAccount(account);
    }

    public StringProperty dateCreatedProperty() {
        return this.dateCreated;
    }

    public String getDateCreated() {
        return this.dateCreated.get();
    }

    //set and get methods
    // dateHired property
    public void setDateCreated(String dateCreated) {
        this.dateCreated.set(dateCreated);
    }

}
