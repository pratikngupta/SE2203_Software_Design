package se2203b.lab6.tennisballgames;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abdelkader Ouda
 */
public class TeamsAdapter {

    Connection connection;

    public TeamsAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE Matches");
                stmt.execute("DROP TABLE Teams");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of teams
                stmt.execute("CREATE TABLE Teams ("
                        + "TeamName CHAR(15) NOT NULL PRIMARY KEY, "
                        + "Wins INT, Losses INT, Ties INT)");
                populateSampls();
            }
        }
    }

    private void populateSampls() throws SQLException {
        // Add some teams
        this.insertTeam("Astros");
        this.insertTeam("Marlins");
        this.insertTeam("Brewers");
        this.insertTeam("Cubs");
    }

    public void insertTeam(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Teams (TeamName, Wins, Losses, Ties) VALUES ('" + name + "', 0, 0, 0)");
    }

    // Get all teams Data
    public ObservableList<Teams> getTeamsList() throws SQLException {
        ObservableList<Teams> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Teams";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        while (rs.next()) {
            list.add(new Teams(rs.getString("TeamName"),
                    rs.getInt("Wins"),
                    rs.getInt("Losses"),
                    rs.getInt("Ties")));
        }
        return list;
    }

    // Get all teams names to populate the ComboBoxs used in Task #3.
    public ObservableList<String> getTeamsNames() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT TeamName FROM Teams";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update list
        while (rs.next()) {
            list.add(rs.getString("TeamName"));
        }
        
        return list;
    }

    public void setStatus(String hTeam, String vTeam, int hScore, int vScore) throws SQLException {
        Statement stmt = connection.createStatement();
        // Update the home team
        if (hScore > vScore) {
            stmt.executeUpdate("UPDATE Teams SET Wins = Wins + 1 WHERE TeamName = '" + hTeam + "'");
            stmt.executeUpdate("UPDATE Teams SET Losses = Losses + 1 WHERE TeamName = '" + vTeam + "'");
        } else if (hScore < vScore) {
            stmt.executeUpdate("UPDATE Teams SET Wins = Wins + 1 WHERE TeamName = '" + vTeam + "'");
            stmt.executeUpdate("UPDATE Teams SET Losses = Losses + 1 WHERE TeamName = '" + hTeam + "'");
        } else {
            stmt.executeUpdate("UPDATE Teams SET Ties = Ties + 1 WHERE TeamName = '" + hTeam + "'");
            stmt.executeUpdate("UPDATE Teams SET Ties = Ties + 1 WHERE TeamName = '" + vTeam + "'");
        }

        // move the team base on the score
        // get the teams data
        ObservableList<Teams> teams = getTeamsList();
        // loop for the all teams
        while (teams.size() > 0) {
            // get the first team
            Teams team = teams.get(0);
            // remove the first team
            teams.remove(0);
            // loop for the all teams
            for (int i = 0; i < teams.size(); i++) {
                // get the current team
                Teams currentTeam = teams.get(i);
                // check if the current team is better than the team
                if (team.compareTo(currentTeam)) {
                    // remove the current team
                    teams.remove(i);
                    // add the team before the current team
                    teams.add(i, team);
                    // add the current team after the team
                    teams.add(i + 1, currentTeam);
                    // break the loop
                    break;
                }
            }
        }
    }
}
