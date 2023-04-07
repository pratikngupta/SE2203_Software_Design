package se2203b.assignments.ifinance;

import javafx.beans.property.*;

public class Group {
    private IntegerProperty id;
    private final StringProperty name;
    private final ObjectProperty<Group> parent;
    private final ObjectProperty<AccountCategory> element;

    // Constructors
    public Group() {
        this(0, "", null, null);
    }

    public Group(int id, String name, Group parent, AccountCategory category) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.parent = new SimpleObjectProperty(parent);
        this.element = new SimpleObjectProperty(category);
    }

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

    public StringProperty nameProperty() {
        return this.name;
    }

    public String getName() {
        return this.name.get();
    }

    // name property
    public void setName(String name) {
        this.name.set(name);
    }

    public ObjectProperty<Group> parentProperty() {
        return this.parent;
    }

    public Group getParent() {
        return this.parent.get();
    }

    // parent Property
    public void setParent(Group parent) {
        this.parent.set(parent);
    }

    public ObjectProperty<AccountCategory> elementProperty() {
        return this.element;
    }

    public AccountCategory getElement() {
        return this.element.get();
    }

    // element Property
    public void setElement(AccountCategory element) {
        this.element.set(element);
    }


}
