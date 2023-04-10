package se2203b.assignments.ifinance;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccountCategory {
    private final StringProperty name;
    private final StringProperty type;

    // Constructors
    public AccountCategory() {
        this("", "");
    }

    public AccountCategory(String name, String type) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public String getName() {
        return this.name.get();
    }

    //set and get methods
    // name property
    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty typeProperty() {
        return this.type;
    }

    public String getType() {
        return this.type.get();
    }

    // type property
    public void setType(String type) {
        this.type.set(type);
    }
}
