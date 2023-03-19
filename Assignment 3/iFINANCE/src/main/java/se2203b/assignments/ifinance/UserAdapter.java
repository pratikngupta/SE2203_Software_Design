package se2203b.assignments.ifinance;


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
                stmt.execute("CREATE TABLE Users (username VARCHAR(30), password VARCHAR(30), isAdmin BOOLEAN, isLogged BOOLEAN)");

                // populate the table with an admin user
                populateSample();
            }
        }
    }

    // Start table with admin user
    public void populateSample() throws SQLException {
        String hashed = hash.hashPassword("admin", "admin");
        this.insertUser("admin", hashed, true, false);
        printUsers();
    }

    public void insertUser(String username, String  password, Boolean admin, boolean logged) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Users (username, password, isAdmin, isLogged) VALUES ('" + username + "', '" + password + "', '" + admin + "', '" + logged + "')");
    }

    public void deleteUser(String username) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Users WHERE username = '" + username + "'");
    }

    public void updateUser(String username, String password) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE Users SET password = '" + hash + "' WHERE username = '" + username + "'");
    }

    public void updateUser(String username, boolean logged) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE Users SET isLogged = '" + logged + "' WHERE username = '" + username + "'");
        printUsers();
    }

    public boolean checkUser(String username, String password) throws SQLException {
        Statement stmt = connection.createStatement();

        String pass = hash.hashPassword(password, username);
        printUsers();
        return stmt.executeQuery("SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + pass + "'").next();
    }

    public static User setCurrentUser() throws SQLException{
        //find the user that is logged in
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM Users WHERE isLogged = true");
        while (rs.next()) {
            currentUser = new User(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getBoolean(4));
        }
        return currentUser;
    }

    // print all users in the database with their passwords to console
    public void printUsers() throws SQLException {
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM Users");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getBoolean(3) + " " + rs.getBoolean(4));
        }
    }
}
