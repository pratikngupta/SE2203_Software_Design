package se2203b.assignments.ifinance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountGroupAdapter {
    static Connection connection;

    public AccountGroupAdapter(Connection connection, boolean reset) throws SQLException {
        //create table if not exists with the following columns: groupID, groupName, groupElement, groupParent
        //if reset is true, drop the table and recreate it

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

                //olumns: groupID, groupName, groupElement, groupParent
                stmt.execute("CREATE TABLE AccountGroups (groupID int, groupName varchar(255), groupElement int, groupParent int)");

                // populate the table with an admin user
                populateSample();
            }

        }
    }

    private void populateSample() {
        // insert into Account
    }

    public void addAccountGroup(int groupID, String groupName, int groupElement, int groupParent) throws  SQLException{
        // insert into AccountGroups (groupID, groupName, groupElement, groupParent) values (groupID, groupName, groupElement, groupParent);
        Statement stmt = connection.createStatement();

        stmt.execute("INSERT INTO AccountGroups (groupID, groupName, groupElement, groupParent) VALUES (" + groupID + ", " + groupName + ", " + groupElement + ", " + groupParent + ")");
    }

    public void deleteAccountGroup(int groupID) throws SQLException {
        // delete from AccountGroups where groupID = groupID;
        Statement stmt = connection.createStatement();

        stmt.execute("DELETE FROM AccountGroups WHERE groupID = " + groupID);
    }

    public void updateAccountGroup(int groupID, String groupName, int groupElement, int groupParent) throws SQLException {
        // update AccountGroups set groupName = groupName, groupElement = groupElement, groupParent = groupParent where groupID = groupID;

        Statement stmt = connection.createStatement();
        stmt.execute("UPDATE AccountGroups SET groupName = " + groupName + ", groupElement = " + groupElement + ", groupParent = " + groupParent + " WHERE groupID = " + groupID);
    }

}
