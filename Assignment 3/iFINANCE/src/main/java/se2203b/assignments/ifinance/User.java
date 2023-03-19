package se2203b.assignments.ifinance;

import javafx.beans.property.*;


// this class is used to store the user data in the table


public class User {


    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty address;
    private StringProperty email;
    private BooleanProperty isAdmin;
    private BooleanProperty isLogged;

    public User(int id,String username, String password, String address, String email, boolean isAdmin, boolean isLogged) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
        this.isLogged = new SimpleBooleanProperty(isLogged);
    }

    public User() {
        this(-1, null, null, null, null, false, false);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isAdmin() {
        return isAdmin.get();
    }

    public BooleanProperty adminProperty() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin.set(admin);
    }

    public boolean isLogged() {
        return isLogged.get();
    }

    public BooleanProperty loggedProperty() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged.set(logged);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }
}
