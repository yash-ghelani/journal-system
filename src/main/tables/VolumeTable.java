package main.tables;
import java.sql.*;
import java.util.ArrayList;

public class VolumeTable {

    public static void main (String args[]) throws SQLException {

        VolumeTable vt = new VolumeTable();
        //vt.CreateVolumeTable();
//        vt.Insert(18361310, 2017);
//        vt.Insert(18361310, 2018);
//        vt.Insert(18361310, 2019);
//
//        vt.Insert(18387189, 2017);
//        vt.Insert(18387189, 2018);
//        vt.Insert(18387189, 2019);
//
//        vt.Insert(54362042, 2017);
//        vt.Insert(54362042, 2018);
//        vt.Insert(54362042, 2019);

    }

    public static void CreateVolumeTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            PreparedStatement stmt = null;
            try {

                String jtable = "CREATE TABLE Volume " + //Creating the table "UserTable"
                        "(VolumeID          INT     NOT NULL    AUTO_INCREMENT, "+ //Creating the different fields
                        "ISSN               INT     NOT NULL, "+
                        "PublicationYear    INT     NOT NULL, "+
                        "PRIMARY KEY (VolumeID), " +
                        "FOREIGN KEY (ISSN) REFERENCES Journal(ISSN))";

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

    public static void Insert(int issn, int year ) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {


                String journal = "INSERT INTO Volume (ISSN, PublicationYear) VALUES (" + issn +"," + year+ ")";
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

    public static void Delete(int volumeid) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String journal = "DELETE FROM Volume WHERE VolumeID = " + volumeid;
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




    public static void UpdateYear(int volumeid, int year) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {


                String journal = "UPDATE Volume SET PublicationYear = " + year + " WHERE VolumeID= " + volumeid;
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

    public static int SelectISSN(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT ISSN FROM Volume WHERE VolumeID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("ISSN");
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

    public static int SelectPublicationYear(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT PublicationYear FROM Volume WHERE VolumeID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("PublicationYear");
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

    public static int SelectVolID(int year) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT VolumeID FROM Volume WHERE PublicationYear = " + year;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("VolumeID");
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

    public static ArrayList<String> SelectVolumes(int issn) throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT PublicationYear FROM Volume WHERE ISSN = "+ issn;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    String fin =  String.valueOf(res.getInt("PublicationYear"));
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

    public static void DeleteTable() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            PreparedStatement stmt = null;
            try {

                String newEdition = "DROP TABLE Volume";
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

    public static int SelectPublicationYear(int issn, int year) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT PublicationYear FROM Volume WHERE (PublicationYear = '" + year + "' AND ISSN = '" + issn + "')";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("PublicationYear");
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }
    public static int SelectVolumeID(int issn,int year) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT VolumeID FROM Volume WHERE ISSN = "+ issn + " AND PublicationYear = "+year;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin =  res.getInt("VolumeID");

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
