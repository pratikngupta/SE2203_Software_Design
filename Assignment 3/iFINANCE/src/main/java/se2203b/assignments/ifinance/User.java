package se2203b.assignments.ifinance;

import javafx.beans.property.*;


// this class is used to store the user data in the table


public class User {

    private StringProperty username;
    private StringProperty password;
    private BooleanProperty isAdmin;
    private BooleanProperty isLogged;

    public User(String username, String password, boolean isAdmin, boolean isLogged) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
        this.isLogged = new SimpleBooleanProperty(isLogged);
    }

    public User() {
        this(null, null, false, false);
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
}
