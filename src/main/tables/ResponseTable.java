package main.tables;
import java.sql.*;

public class ResponseTable {

    public static void main (String args[]) throws SQLException {

        ResponseTable et = new ResponseTable();
        et.CreateResponseTable();
        //et.Insert(12345678, 2018);
    }

    public static void CreateResponseTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String jtable = "CREATE TABLE Response " + //Creating the table "UserTable"
                        "(ResponseID               INT     NOT NULL    AUTO_INCREMENT, "+ //Creating the different fields
                        "QuestionID                INT     NOT NULL, "+
                        "ResponseText                  TEXT    NOT NULL, " +
                        "PRIMARY KEY (ResponseID), " +
                        "FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID))";

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

    public static void Insert(int questionid, String response ) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String journal = "INSERT INTO Response (QuestionID, ResponseText) VALUES (?,?)";
                con.setAutoCommit(false);
                stmt = con.prepareStatement(journal);
                stmt.setInt(1, questionid);
                stmt.setString(2, response);
                stmt.execute();
                con.commit();
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

    public static void Delete(int id) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String journal = "DELETE FROM Response WHERE ResponseID = " + id;
                //System.out.println(journal);
                stmt.executeUpdate(journal);
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




    public static void UpdateQuestionID(int responseid, int questionid) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {


                String journal = "UPDATE Response SET QuestionID = " + questionid + " WHERE ResponseID= " + responseid;
                //System.out.println(journal);
                stmt.executeUpdate(journal);
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

    public static void UpdateResponse(int id, String response) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {


                String journal = "UPDATE Response SET ResponseText = '" + response + "' WHERE ResponseID= " + id;
                //System.out.println(journal);
                stmt.executeUpdate(journal);
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

    public int SelectQuestionID(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT QuestionID FROM Response WHERE ResponseID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("QuestionID");
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

    public static int SelectResponseID(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT ResponseID FROM Response WHERE QuestionID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("ResponseID");
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

    public static String SelectResponseText(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT ResponseText FROM Response WHERE QuestionID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("ResponseText");
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

    public static void DeleteTable() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            PreparedStatement stmt = null;
            try {

                String newEdition = "DROP TABLE Response";
                stmt = con.prepareStatement(newEdition); stmt.executeUpdate();
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

