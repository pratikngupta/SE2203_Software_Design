package se2203b.assignments.ifinance;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// this class is responsible for creating the connection between the database and the table
public class UserAdapter {

    static Connection connection;

    PasswordHash hash = new PasswordHash();

    static User currentUser = null;

    public UserAdapter(Connection connection, boolean reset) throws SQLException {
        this.connection = connection;

        if (reset) {
            // create the table
            Statement stmt = connection.createStatement();

            try {
                // remove the table if it exists
                stmt.execute("DROP TABLE Users");
            } catch (SQLException ex) {
                // no need to report an error
                // the table simply did not exist
            } finally {
                // create the table with userName, Password, isAdmin, isLogged

                // create a table with 7 columns: id, username, password, address, email, isAdmin, isLogged
                stmt.execute("CREATE TABLE Users (" +
                        "id INT NOT NULL, " +
                        "username VARCHAR(30), " +
                        "fullname VARCHAR(30), " +
                        "password VARCHAR(30), " +
                        "address VARCHAR(30), " +
                        "email VARCHAR(30), " +
                        "isAdmin BOOLEAN, " +
                        "isLogged BOOLEAN, " +
                        "PRIMARY KEY (id))");

                //stmt.execute("CREATE TABLE Users (username VARCHAR(30), password VARCHAR(30), isAdmin BOOLEAN, isLogged BOOLEAN)");

                // populate the table with an admin user
                populateSample();
            }
        }
    }

    // Start table with admin user
    public void populateSample() throws SQLException {
        String hashed = hash.hashPassword("admin", "admin");
        System.out.println("reach here");
        this.insertUser(0,"admin", hashed, true, false);
        System.out.println("Added admin user");
        hashed = (hash.hashPassword("user", "user"));
        this.insertUser(getMaxId(),"user","Pratik" ,hashed, "1030 Oakcrossing Gate","pratikngutpa@outlook.com",false, false);
        printUsers();
    }

    public void insertUser(int id, String username, String  password, Boolean admin, boolean logged) throws SQLException {
        Statement stmt = connection.createStatement();
        // ID is integer, username is string, password is string, isAdmin is boolean, isLogged is boolean
        stmt.executeUpdate("INSERT INTO Users (id, username, password, isAdmin, isLogged) VALUES (" + id + ", '" + username + "', '" + password + "', '" + admin + "', '" + logged + "')");
    }

    public void insertUser(int id,String username, String fullName, String password, String email, String address, Boolean admin, boolean logged) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Users (id, username, fullname, password, email, address, isAdmin, isLogged) VALUES (" + id + ", '" + username + "', '" + fullName + "', '" + password + "', '" + email + "', '" + address + "', '" + admin + "', '" + logged + "')");
        System.out.println("Summery: " + id + " " + username + " " + password + " " + admin + " " + logged);
    }

    public void deleteUser(String username) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Users WHERE username = '" + username + "'");
    }

    public void updateUser(String username, String password) throws SQLException {
        Statement stmt = connection.createStatement();
        String hashed = hash.hashPassword(password, username);
        stmt.executeUpdate("UPDATE Users SET password = '" + hashed + "' WHERE username = '" + username + "'");
    }

    public void updateUser(String username, boolean logged) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE Users SET isLogged = '" + logged + "' WHERE username = '" + username + "'");
    }

    public boolean checkUser(String username, String password) throws SQLException {
        Statement stmt = connection.createStatement();
        String pass = hash.hashPassword(password, username);
        return stmt.executeQuery("SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + pass + "'").next();
    }

    public boolean checkUser(String username) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery("SELECT * FROM Users WHERE username = '" + username + "'").next();
    }

    public static User getLoggedUser() throws SQLException{
        //find the user that is logged in
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM Users WHERE isLogged = true");
        while (rs.next()) {
            currentUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
        }
        return currentUser;
    }

    public static User getUserInfo(String username) throws SQLException{
        //find the user that is logged in
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM Users WHERE username = '" + username + "'");
        while (rs.next()) {
            currentUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
        }

        return currentUser;
    }

    public void addUser(User user) throws SQLException {
        Statement stmt = connection.createStatement();
        int id = getMaxId();
        String username = user.getUsername();
        String fullName = user.getFullname();
        String password = user.getPassword();
        String email = user.getEmail();
        String address = user.getAddress();
        String hashed = hash.hashPassword(password, username);
        stmt.executeUpdate("INSERT INTO Users (id,username, fullname, password, email, address, isAdmin, isLogged) VALUES (" + id + ", '" + username + "', '" + fullName + "', '" + hashed + "', '" + email + "', '" + address + "', false, false)");
    }

    // print all users in the database with their passwords to console
    public void printUsers() throws SQLException {
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM Users");
        //print header for table
        System.out.println("id username fullname password address email isAdmin isLogged");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getBoolean(7) + " " + rs.getBoolean(8));
        }
    }

    public ObservableList<String> getUsersList() throws SQLException {
        // this will return a list of users from the database excluding the admin user
        Statement stmt = connection.createStatement();

        var rs = stmt.executeQuery("SELECT * FROM Users WHERE isAdmin = false");

        ObservableList<String> users = FXCollections.observableArrayList();;

        while (rs.next()) {
            //only add username to list
            users.add(rs.getString(2));
        }

        return users;
    }

    public int getMaxId() throws SQLException {
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT MAX(id) FROM Users");
        int maxId = 0;
        while (rs.next()) {
            maxId = rs.getInt(1);
        }
        System.out.println("Max id: " + maxId);
        return maxId + 1;
    }
}
