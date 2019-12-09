package main.tables;

import java.sql.*;

public class UserTable {

    public static void CreateUserTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            PreparedStatement stmt = null;
            try {

                String query = "CREATE TABLE User " + //Creating the table "UserTable"
                        "(UserID                     INT     AUTO_INCREMENT, "+ //Creating the different fields
                        "Title                       TEXT, "+
                        "Name                        TEXT,"+
                        "Surname                     TEXT, "+
                        "Affiliation                 TEXT, " +
                        "Email                       TEXT, " +
                        "Password                    TEXT," +
                        "PRIMARY KEY (UserID))";

                stmt.executeUpdate(query);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                if (stmt != null)
                    stmt.close();
            }
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        finally {
            if (con != null) con.close();
        }

    }

    public static void Insert(String title, String name, String surname, String affiliation, String email, String password) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "INSERT INTO User (Title, Name, Surname, Affiliation, Email, Password) "+
                        " VALUES ('" + title + "', '" + name + "', '" + surname + "', '" + affiliation + "', '" + email + "', '" + password + "')";
                //System.out.println(journal);
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Insert failed");
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Something else failed");
        } finally {
            if (con != null) con.close();
        }
    }

    public static void Update (int id, String title, String name, String surname, String affiliation, String email, String password) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "UPDATE User SET Title = '"+title+"', Name = '"+name+"', Surname = '"+surname+"', Affiliation = '"+affiliation+"', Email = '"+email+"', Password = '" + password +"' WHERE UserID = " + id;
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.out.println("Selection failed");
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.out.println("Something else failed");
        } finally {
            if (con != null) con.close();
        }
    }

    public static int ValidateEmailAndPassword(String email, String password) throws SQLException {
        int id = -1;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT UserID FROM User WHERE Email = '" + email + "' AND Password = '" + password + "'";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    id = res.getInt("UserID");
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }

        return id;
    }

    public static String SelectAffiliation(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Affiliation FROM User WHERE UserID = " + id;
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

    public static void DeleteTable() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            PreparedStatement stmt = null;
            try {

                String newEdition = "DROP TABLE User";
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

                String query = "SELECT last_insert_id()";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    id = res.getInt("last_insert_id()");
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

    public static int GetID(String name,String surname,String email) throws SQLException {
        int id = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT UserID FROM User WHERE (Name = '"+name+"' AND Surname = '"+surname+"' AND Email = '"+email+"')";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    id = res.getInt("UserID");
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

    public static int GetID(String name,String surname) throws SQLException {
        int id = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT UserID FROM User WHERE (Name = '"+name+"' AND Surname = '"+surname+"')";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    id = res.getInt("UserID");
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


    public static String SelectName(int id) throws SQLException {
        String name = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT Name FROM User WHERE UserID = '"+id+"'";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    name = res.getString("Name");
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
        return name;
    }

    public static String SelectSurName(int id) throws SQLException {
        String name = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            PreparedStatement stmt = null;
            try {

                String query = "SELECT SurName FROM User WHERE UserID = '"+id+"'";
                stmt = con.prepareStatement(query); ResultSet res = stmt.executeQuery();
                while (res.next()) {
                    name = res.getString("SurName");
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
        return name;
    }

    public static void DeleteUser(String name,String surname) throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            PreparedStatement stmt = null;
            try {

                String newEdition = "DELETE FROM User WHERE (Name = '"+name+"' AND SurName = '"+surname+"')";
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