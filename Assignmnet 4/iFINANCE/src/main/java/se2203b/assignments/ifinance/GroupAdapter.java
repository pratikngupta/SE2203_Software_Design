package se2203b.assignments.ifinance;

import javafx.scene.control.TreeItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GroupAdapter {

    Connection connection;

    public GroupAdapter(Connection conn, boolean reset) throws SQLException {
        connection = conn;
        Statement statement = connection.createStatement();
        if (reset) {
            // Remove tables if database tables have been created.
            // This will throw an exception if the tables do not exist
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                statement.execute("DROP TABLE Groups");
            } catch (Exception ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
            //Finally, create the table
            finally {
                // create 4 columns: ID	NAME, PARENT, ELEMENT
                statement.execute("CREATE TABLE Groups ("
                        + "ID INTEGER,"
                        + "NAME VARCHAR(50),"
                        + "PARENT INTEGER,"
                        + "ELEMENT VARCHAR(50),"
                        + "PRIMARY KEY (ID))");
                populateTable();
            }
        }
    }

    private void populateTable() {
        try {
            Scanner sc = new Scanner(new File("src/main/resources/se2203b/assignments/ifinance/AccountGroup.csv"));
            sc.useDelimiter(",");   //sets the delimiter pattern
            sc.nextLine();

            while (sc.hasNext()){
                //skip the first line
                String line = sc.nextLine();
                //remove ' from the string
                System.out.println(line);

                String[] values = line.split(",");
                this.insertGroup(
                        Integer.parseInt(values[0]),
                        values[1],
                        Integer.parseInt(values[2]),
                        values[3]
                );
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertGroup(int i, String income, int i1, String credit) throws SQLException {
        //store int as int in database and string as string
        Statement statement = connection.createStatement();

        statement.execute("INSERT INTO Groups VALUES (" + i + ", '" + income + "', " + i1 + ", '" + credit + "')");


    }


}
