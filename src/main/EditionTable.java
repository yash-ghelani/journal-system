package main;
import java.sql.*;

public class EditionTable {

    public static void main (String args[]) throws SQLException {

        EditionTable et = new EditionTable();
        et.CreateEditionTable();
    }

    public static void CreateEditionTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String initialise =
                        "CREATE TABLE Edition " + //Creating the table
                        "(EditionID             INT    NOT NULL AUTO_INCREMENT, "+ //Creating the different fields
                        "VolumeID               INT    NOT NULL, "+
                        "PublicationMonth       INT    NOT NULL, "+
                        "PRIMARY KEY (EditionID), "+
                        "FOREIGN KEY (VolumeID) REFERENCES Volume(VolumeID))";

                stmt.executeUpdate(initialise);
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

    public static void Insert(int volumeID, int month ) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                if (month<12 || month>0) {
                    String newEdition = "INSERT INTO Edition (VolumeID, PublicationMonth) VALUES ('" + volumeID + "',  '" + month + "')";
                    stmt.executeUpdate(newEdition);
                } else {
                    System.out.println("Invalid Month");
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null)
                    stmt.close();
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (con != null) con.close();
        }

    }

    public static void UpdateMonth(int editionID, int month ) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                if (month<12 || month>0) {
                    String newEdition = "UPDATE Edition SET PublicationMonth = '"+month+"' WHERE EditionID = " + editionID;
                    stmt.executeUpdate(newEdition);
                } else {
                    System.out.println("Invalid Month");
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null)
                    stmt.close();
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            if (con != null) con.close();
        }

    }
}
