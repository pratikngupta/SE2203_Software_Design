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

                // create a table with 7 columns: id, username, password, address, email, isAdmin, isLogged
                stmt.execute("CREATE TABLE Users (" +
                        "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1), " +
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
        this.insertUser("admin", hashed, true, false);
        hashed = (hash.hashPassword("user", "user"));
        this.insertUser("user","Pratik" ,hashed, "1030 Oakcrossing Gate","pratikngutpa@outlook.com",false, false);
        printUsers();
    }

    public void insertUser(String username, String  password, Boolean admin, boolean logged) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Users (username, password, isAdmin, isLogged) VALUES ('" + username + "', '" + password + "', '" + admin + "', '" + logged + "')");
    }

    public void insertUser(String username, String fullName, String password, String email, String address, Boolean admin, boolean logged) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Users (username, fullname, password, email, address, isAdmin, isLogged) VALUES ('" + username + "', '" + fullName + "', '" + password + "', '" + email + "', '" + address + "', '" + admin + "', '" + logged + "')");
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

    public static User setCurrentUser() throws SQLException{
        //find the user that is logged in
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery("SELECT * FROM Users WHERE isLogged = true");
        while (rs.next()) {
            currentUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getBoolean(8));
        }
        return currentUser;
    }

    public void addUser(User user) throws SQLException {
        Statement stmt = connection.createStatement();
        String username = user.getUsername();
        String fullName = user.getFullname();
        String password = user.getPassword();
        String email = user.getEmail();
        String address = user.getAddress();
        String hashed = hash.hashPassword(password, username);
        stmt.executeUpdate("INSERT INTO Users (username, fullname, password, email, address, isAdmin, isLogged) VALUES ('" + username + "', '" + fullName + "', '" + hashed + "', '" + email + "', '" + address + "', false, false)");
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
}
