package se2203b.assignments.ifinance;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Transaction {
    private final StringProperty id;
    private final StringProperty date;
    private final StringProperty description;
    private final ObjectProperty<NonAdminUser> author;
    private final ArrayList<ObjectProperty<TransactionLine>> lines;

    // Constructors
    public Transaction() {
        this("", null, "");
    }

    public Transaction(String id, String date, String description) {
        this.id = new SimpleStringProperty(id);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
        this.author = new SimpleObjectProperty(new NonAdminUser());
        lines = new ArrayList<ObjectProperty<TransactionLine>>();
    }

    public StringProperty idProperty() {
        return this.id;
    }

    public String getID() {
        return this.id.get();
    }

    //set and get methods
    // id property
    public void setID(String id) {
        this.date.set(id);
    }

    public StringProperty dateProperty() {
        return this.date;
    }

    public String getDate() {
        return this.date.get();
    }

    // date property
    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty descriptionProperty() {
        return this.description;
    }

    public String getDescription() {
        return this.description.get();
    }

    // description property
    public void setDescription(String description) {
        this.description.set(description);
    }

    public ObjectProperty<NonAdminUser> authorProperty() {
        return this.author;
    }

    public NonAdminUser getAuthor() {
        return this.author.get();
    }

    // author Property
    public void setAuthor(NonAdminUser author) {
        this.author.set(author);
    }
}
