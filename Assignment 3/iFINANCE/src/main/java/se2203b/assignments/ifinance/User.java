package se2203b.assignments.ifinance;

import javafx.beans.property.*;

// this class is used to store the user data in the table

public class User {


    private final IntegerProperty id;
    private final StringProperty username;
    private final StringProperty fullname;
    private final StringProperty password;
    private final StringProperty address;
    private final StringProperty email;
    private final BooleanProperty isAdmin;
    private final BooleanProperty isLogged;

    public User(int id, String username, String fullname, String password, String address, String email, boolean isAdmin, boolean isLogged) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.fullname = new SimpleStringProperty(fullname);
        this.password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
        this.isLogged = new SimpleBooleanProperty(isLogged);
    }

    public User() {
        this(-1, null, null, null, null, null, false, false);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin.get();
    }

    public void setAdmin(boolean admin) {
        isAdmin.set(admin);
    }

    public BooleanProperty adminProperty() {
        return isAdmin;
    }

    public boolean isLogged() {
        return isLogged.get();
    }

    public void setLogged(boolean logged) {
        isLogged.set(logged);
    }

    public BooleanProperty loggedProperty() {
        return isLogged;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFullname() {
        return fullname.get();
    }

    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }

    public StringProperty fullnameProperty() {
        return fullname;
    }


}
