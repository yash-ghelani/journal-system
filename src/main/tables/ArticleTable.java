package main.tables;
import java.sql.*;
import java.util.ArrayList;

public class ArticleTable {

    public static void main (String args[]) throws SQLException {

        ArticleTable at = new ArticleTable();
        at.CreateArticleTable();
    }

    public static void CreateArticleTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            PreparedStatement stmt = null;
            try {
                
                String initialise = "CREATE TABLE Articles " + //Creating the table
                                    "(ArticleID             INT    NOT NULL AUTO_INCREMENT, "+ //Creating the different fields
                                    "ISSN                   INT, "+
                                    "EditionID              INT, "+
                                    "Title                  TEXT, "+
                                    "Abstract               TEXT, " +
                                    "PDF                    TEXT," +
                                    "PageRange              TEXT, "+
                                    "Published              INT,"+
                                    "PRIMARY KEY (ArticleID), "+
                                    "FOREIGN KEY (ISSN) REFERENCES Journal(ISSN), "+
                                    "FOREIGN KEY (EditionID) REFERENCES Edition(EditionID))";

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

    public static void Insert(int issn, String title, String abstractText, String pdf, String pageRange,  int published) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String newEdition = "INSERT INTO Articles (ISSN, Title, Abstract, PDF, PageRange, Published) "+
                                    " VALUES ('"+ issn + "', '" + title + "',  '" + abstractText + "',  '" + pdf + "',  '"+ pageRange + "',  '" + published + "')";
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

    //====================================================================================================================

    public static void UpdateEdition(int articleID, int editionID ) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String newEdition = "UPDATE Articles SET EditionID = '"+editionID+"' WHERE ArticleID = " + articleID;
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

    public static void UpdateSubmission(int articleID, int submissionID) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String newEdition = "UPDATE Articles SET SubmissionID = '"+submissionID+"' WHERE ArticleID = " + articleID;
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

    public static void UpdatePDF(int articleID, String PDF) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String newEdition = "UPDATE Articles SET PDF = '"+PDF+"' WHERE ArticleID = " + articleID;
                System.out.println(newEdition);
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

    public static void UpdatePageRange(int articleID, int pageRange) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String newEdition = "UPDATE Articles SET PageRange = '"+pageRange+"' WHERE ArticleID = " + articleID;
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

    public static void UpdateAbstract(int articleID, int abstractText) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String newEdition = "UPDATE Articles SET Abstract = '"+abstractText+"' WHERE ArticleID = " + articleID;
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

    //==================================================================================================================

    public static void Delete(int articleID) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String newEdition = "DELETE FROM Articles WHERE ArticleID = " + articleID;
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

    public static int GetID() throws SQLException {
        int id = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT MAX(ArticleID) FROM Articles";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    id = res.getInt("MAX(ArticleID)");
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
        return id;
    }

    //=================================================================================================================

    public int SelectEditionID(int articleID) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT EditionID FROM Articles WHERE ArticleID = " + articleID;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("EditionID");
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

    public int SelectSubmissionID(int articleID) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT SubmissionID FROM Articles WHERE ArticleID = " + articleID;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
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

    public static String SelectTitle(int articleID) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT Title FROM Articles WHERE ArticleID = " + articleID;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("Title");
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

    public String SelectPageRange(int articleID) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT PageRange FROM Articles WHERE ArticleID = " + articleID;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("PageRange");
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

    public static String SelectPDF(int articleID) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT PDF FROM Articles WHERE ArticleID = " + articleID;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("PDF");
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

    public static String SelectAbstract(int articleID) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT Abstract FROM Articles WHERE ArticleID = " + articleID;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("Abstract");
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
                
                String newEdition = "DROP TABLE Articles";
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

    public static ArrayList<String> SelectAllArticleTitles() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT Title FROM Articles";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    String fin = res.getString("Title");
                    list.add(fin);
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
        return list;
    }

    public static ArrayList<String> SelectTitles(int id) throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT Title FROM Articles WHERE EditionID = '"+ id +"'";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    String fin = res.getString("Title");
                    list.add(fin);
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
        return list;
    }

    public static ArrayList<Integer> SelectAllArticleIDs() throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {
                
                String query = "SELECT ArticleID FROM Articles";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    int fin = res.getInt("ArticleID");
                    list.add(fin);
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
        return list;
    }

}

