package main.tables;

import java.sql.*;

public class UserTable {

    public static void main(String args[]) throws SQLException {

        UserTable rt = new UserTable();
        rt.CreateUserTable();
        //vt.Insert(12345678, 2018);
    }

    public static void CreateUserTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "CREATE TABLE User " + //Creating the table "UserTable"
                        "(UserID                     INT     AUTO_INCREMENT, "+ //Creating the different fields
                        "Title                       INT, "+
                        "Name                        INT,"+
                        "Surname                    TEXT, "+
                        "Affiliation                TEXT, " +
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
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "INSERT INTO User (Title, Name, Surname, Affiliation, Email, Password, UserType) "+
                        " VALUES ('" + title + "', '" + name + "', '" + surname + "','" + affiliation + "','" + email + "','" + password + "')";
                //System.out.println(journal);
                stmt.executeUpdate(query);
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
            if (con != null) con.close();
        }
    }

    public static void Update (String title, String name, String surname, String affiliation, String email, String password) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "UPDATE User SET Title = '"+title+"', Name = '"+name+"', Surname = '"+surname+"', Affiliation = '"+affiliation+"', Email = '"+email+"', Password = '" + password +"'";
                stmt.executeUpdate(query);
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
            if (con != null) con.close();
        }
    }

    public static int ValidateEmailAndPassword(String email, String password) throws SQLException {
        int id = -1;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT UserID FROM User WHERE Email = '" + email + "' AND Password = '" + password + "'";
                ResultSet res = stmt.executeQuery(query);
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
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Affiliation FROM User WHERE UserID = " + id;
                ResultSet res = stmt.executeQuery(query);
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
}