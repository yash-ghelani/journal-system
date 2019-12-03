package main.tables;
import java.sql.*;

public class SubmissionInfoTable {

    public static void main (String args[]) throws SQLException {

        SubmissionInfoTable sit = new SubmissionInfoTable();
        sit.CreateSubmissionInfoTable();
    }

    public static void CreateSubmissionInfoTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String initialise = "CREATE TABLE SubmissionInfo " + //Creating the table
                                    "(SubmissionInfoID      INT             NOT NULL AUTO_INCREMENT, "+ //Creating the different fields
                                    "SubmissionID           INT             NOT NULL, "+ //Creating the different fields
                                    "AuthorID               INT             NOT NULL, "+
                                    "AuthorType             BOOLEAN         NOT NULL, "+
                                    "PRIMARY KEY (SubmissionInfoID), "+
                                    "FOREIGN KEY (SubmissionID) REFERENCES Submissions(SubmissionID), "+
                                    "FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID))";

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

    public static void Insert(int submissionID, int authorID, boolean type) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "INSERT INTO SubmissionInfo (SubmissionID, AuthorID, AuthorType) "+
                                    " VALUES ('" + submissionID + "',  '" + authorID + "',  '" +  type + "')";
                stmt.executeUpdate(newEdition);
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

    //====================================================================================================================

    public static void UpdateSubmission(int submissionInfoID, int submissionID) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "UPDATE SubmissionInfo SET SubmissionID = '" + submissionID +
                                    "' WHERE SubmissionInfoID = " + submissionInfoID;
                stmt.executeUpdate(newEdition);
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

    public static void UpdateAuthor(int submissionInfoID, int authorID) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "UPDATE SubmissionInfo SET AuthorID = '" + authorID +
                        "' WHERE SubmissionInfoID = " + submissionInfoID;
                stmt.executeUpdate(newEdition);
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

    public static void UpdateAuthorType(int submissionInfoID, int type) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "UPDATE SubmissionInfo SET AuthorType = '" + type +
                        "' WHERE SubmissionInfoID = " + submissionInfoID;
                stmt.executeUpdate(newEdition);
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

    //==================================================================================================================

    public static void Delete(int submissionInfoID) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "DELETE FROM SubmissionInfo WHERE SubmissionInfoID = " + submissionInfoID;
                stmt.executeUpdate(newEdition);
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

    //=================================================================================================================

    public int SelectSubmissionID(int submissionInfoID) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT SubmissionID FROM SubmissionInfo WHERE SubmissionInfoID = " + submissionInfoID;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getInt("SubmissionID");
                }
                res.close();
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
        return fin;
    }

    public int SelectAuthorID(int submissionInfoID) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT AuthorID FROM SubmissionInfo WHERE SubmissionInfoID = " + submissionInfoID;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getInt("AuthorID");
                }
                res.close();
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
        return fin;
    }

    public boolean SelectAuthorType(int submissionInfoID) throws SQLException {
        boolean fin = false;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT AuthorType FROM SubmissionInfo WHERE SubmissionInfoID = " + submissionInfoID;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getBoolean("AuthorType");
                }
                res.close();
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
        return fin;
    }
}


