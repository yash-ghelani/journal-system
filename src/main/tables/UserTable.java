package main.tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTable {

    public static void CreateUserTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String jtable = "CREATE TABLE User " + //Creating the table "UserTable"
                        "(UserID                     INT     AUTO_INCREMENT, "+ //Creating the different fields
                        "Title                       INT, "+
                        "Name                        INT,"+
                        "Surname                    TEXT, "+
                        "Affiliation                TEXT, " +
                        "Email                       TEXT, " +
                        "Password                    TEXT," +
                        "UserType                   TEXT,"+
                        "PRIMARY KEY (UserID))";

                stmt.executeUpdate(jtable);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null)
                    stmt.close();
            }


            //=========================================================================================================
        }
        catch (Exception e) {
            //e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
        finally {
            if (con != null) con.close();
        }

    }
}
