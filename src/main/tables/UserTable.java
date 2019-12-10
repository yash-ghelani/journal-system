package main.tables;

import java.sql.*;

public class UserTable {

    public static void main (String args[]) throws SQLException {
        String pw = String.valueOf("kingjames".hashCode());
        //Insert("Dr.", "Yash", "Ghelani", "Sheffield", "yash@a.com", pw);
        //Update(1,"Mr.", "Yash", "Ghelani", "Cambridge", "yash@a.com", pw);
        System.out.println(ValidateEmailAndPassword("as@gmail.com", pw));
    }

    public static void CreateUserTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            PreparedStatement stmt = null;
            try {

                String query = "CREATE TABLE User " + //Creating the table "UserTable"
                        "(UserID                     INT     AUTO_INCREMENT, "+ //Creating the different fields
                        "Title                       TEXT, "+
                        "Name                        TEXT, "+
                        "Surname                     TEXT, "+
                        "Affiliation                 TEXT, " +
                        "Email                       TEXT, " +
                        "Password                    TEXT, " +
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

        Connection con = null; // a Connection object
        PreparedStatement insertString = null;
        String insert = "INSERT INTO User (Title, Name, Surname, Affiliation, Email, Password) "+ " VALUES (?,?,?,?,?,?)";

        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            con.setAutoCommit(false);
            insertString = con.prepareStatement(insert);
            insertString.setString(1,title);
            insertString.setString(2,name);
            insertString.setString(3,surname);
            insertString.setString(4,affiliation);
            insertString.setString(5,email);
            insertString.setString(6,password);
            insertString.execute();
            con.commit();

        } catch (SQLException e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch(SQLException excep) {
                    e.printStackTrace();
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                }
            }
        } finally {
            if (insertString != null) {
                insertString.close();
            }
            con.setAutoCommit(true);
        }
    }

    public static void Update(int id, String title, String name, String surname, String affiliation, String email, String password) throws SQLException {

        Connection con = null; // a Connection object
        PreparedStatement updateString = null;
        String update = "UPDATE User SET Title = ?, Name = ?, Surname = ?, Affiliation = ?, Email = ?, Password = ? WHERE UserID = ?";

        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            con.setAutoCommit(false);
            updateString = con.prepareStatement(update);
            updateString.setString(1,title);
            updateString.setString(2,name);
            updateString.setString(3,surname);
            updateString.setString(4,affiliation);
            updateString.setString(5,email);
            updateString.setString(6,password);
            updateString.setInt(7,id);
            updateString.execute();
            con.commit();

        } catch (SQLException e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch(SQLException excep) {
                    e.printStackTrace();
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                }
            }
        } finally {
            if (updateString != null) {
                updateString.close();
            }
            con.setAutoCommit(true);
        }
    }

    public static int ValidateEmailAndPassword (String email, String password) throws SQLException {
        int id = -1;
        Connection con = null; // a Connection object
        PreparedStatement selectString = null;
        String query = "SELECT UserID FROM User WHERE Email = ? AND Password = ?";

        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            con.setAutoCommit(false);
            selectString = con.prepareStatement(query);
            selectString.setString(1,email);
            selectString.setString(2,password);
            ResultSet res = selectString.executeQuery();
            while (res.next()) {
                id = res.getInt("UserID");
            }
            res.close();
            con.commit();

        } catch (SQLException e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    con.rollback();
                } catch(SQLException excep) {
                    e.printStackTrace();
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                }
            }
        } finally {
            if (selectString != null) {
                selectString.close();
            }
            con.setAutoCommit(true);
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

                String newEdition = "DELETE FROM User WHERE (Name = '"+name+"' AND Surname = '"+surname+"')";
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