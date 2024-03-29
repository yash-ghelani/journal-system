package main.tables;
import java.sql.*;

public class EditorTable {

    public static void main(String args[]) throws SQLException {

        EditorTable et = new EditorTable();
        et.CreateEditorTable();
    }

    public static void CreateEditorTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String jtable = "CREATE TABLE Editor " + //Creating the table "UserTable"
                        "(EditorID               INT             AUTO_INCREMENT, " +
                        "UserID                  INT          NOT NULL, " + //Creating the different fields
                        "Temp                    INT          NOT NULL," +
                        "PRIMARY KEY (EditorID)," +
                        "FOREIGN KEY (UserID) REFERENCES User(UserID))";

                stmt.executeUpdate(jtable);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }


            //=========================================================================================================
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        } finally {
            if (con != null) con.close();
        }

    }

    public static void Insert(int userid, int temp) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String insert = "INSERT INTO Editor (UserID, Temp) "+
                        " VALUES (?,?)";
                //System.out.println(journal);
                con.setAutoCommit(false);
                stmt = con.prepareStatement(insert);
                stmt.setInt(1, userid);
                stmt.setInt(2, temp);

                stmt.execute();
                con.commit();
            } catch (SQLException e) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Selection failed");
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Selection failed");
        } finally {
            if (con != null) {
                con.close();
            }

        }
    }

    public static void Delete(int id) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "DELETE FROM Editor WHERE EditorID = " + id;
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

    public static void UpdateTitle(int id, String title) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Editor SET Title = '" + title + "' WHERE EditorID = " + id;
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

    public static void UpdateName(int id, String name) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Editor SET Name = '" + name + "' WHERE EditorID = " + id;
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
    public static void UpdateSurname(int id, String surname) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String journal = "UPDATE Editor SET Surname = '" + surname + "' WHERE EditorID = " + id;
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

    public static void UpdateAffiliation(int id, String affiliation) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Editor SET Affiliation = '" + affiliation + "' WHERE EditorID = " + id;
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

    public static void UpdateEmail(int id, String email) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String journal = "UPDATE Editor SET Email = '" + email + "' WHERE EditorID = " + id;
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

    public static void UpdatePassword(int id, String password) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String journal = "UPDATE Editor SET Password = '" + password + "' WHERE EditorID = " + id;
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

    public static void UpdateTemp(int id, int temp) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String journal = "UPDATE Editor SET Temp = "+ temp +" WHERE EditorID=" + id;
                //System.out.println(journal);
                stmt.executeUpdate(journal);
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
    }

    public String SelectTitle(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Title FROM Editor WHERE EditorID = " + id;
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
    public static int SelectUserID(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT UserID FROM Editor WHERE EditorID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getInt("UserID");
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

    public static String SelectSurname(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Surname FROM Editor WHERE EditorID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("Surname");
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

    public String SelectAffiliation(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Affiliation FROM Editor WHERE EditorID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("Affiliation");
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

    public String SelectEmail(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Email FROM Editor WHERE EditorID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("Email");
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

    public String SelectPassword(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Password FROM Editor WHERE EditorID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getString("Password");
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

    public int SelectTemp(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Temp FROM Editor WHERE EditorID = " + id;
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    fin = res.getInt("Temp");
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

    public static boolean ValidateEmailAndPassword(String email, String password) throws SQLException {
        int id = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT EditorID FROM Editor WHERE Email = '" + email + "' AND Password = '" + password + "'";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    id = res.getInt("EditorID");
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

        if (id == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int GetID(int id) throws SQLException {
        int fin = -1;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT EditorID FROM Editor WHERE UserID = '" +id+ "'";
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getInt("EditorID");
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null) stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }

    public static void DeleteTable() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "DROP TABLE Editor";
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

    public static void DeleteEditor(int editorID) throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "DELETE FROM Editor WHERE EditorID = '"+editorID+"'";
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
}