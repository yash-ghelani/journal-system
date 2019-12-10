package main.tables;
import java.sql.*;

public class AuthorTable {

    public static void main(String args[]) throws SQLException {

        AuthorTable rt = new AuthorTable();
       // rt.CreateAuthorTable();
        //vt.Insert(12345678, 2018);
    }

    public static void CreateAuthorTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String jtable = "CREATE TABLE Author " + //Creating the table "UserTable"
                        "(AuthorID               INT                 AUTO_INCREMENT, " +
                        "UserID                  INT," + //Creating the different fields
                        "Temp                    INT," +
                        "PRIMARY KEY (AuthorID)," +
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
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String insert = "INSERT INTO Author (UserID, Temp) "+
                                " VALUES ('" + userid + "','"+ temp+"')";
                //System.out.println(journal);
                stmt.executeUpdate(insert);
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
            if (con != null) {con.close();}
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
                String journal = "DELETE FROM Author WHERE AuthorID = " + id;
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

    public static void UpdateTitle(int id, String title) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Author SET Title = '" + title + "'  WHERE AuthorID = " + id;
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

    public static void UpdateName(int id, String name) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Author SET Name = '" + name + "'  WHERE AuthorID = " + id;
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

    public static void UpdateSurname(int id, String surname) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Author SET Surname = '" + surname + "' WHERE AuthorID = " + id;
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

    public static void UpdateAffiliation(int id, String affiliation) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Author SET Affiliation = '" + affiliation + "' WHERE AuthorID = " + id;
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

    public static void UpdateEmail(int id, String email) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Author SET Email = '" + email + "' WHERE AuthorID=" + id;
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

    public static void UpdatePassword(int id, String password) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Author SET Password = '" + password + "'WHERE AuthorID=" + id;
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

    public static void UpdateTemp(int id, boolean temp) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Author SET Temp = '"+ temp +"' WHERE AuthorID=" + id;
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
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Title FROM Author WHERE AuthorID = " + id;
                ResultSet res = stmt.executeQuery(query);
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
    public String SelectName(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Name FROM Author WHERE AuthorID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getString("Name");
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

    public String SelectSurname(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Surname FROM Author WHERE AuthorID = " + id;
                ResultSet res = stmt.executeQuery(query);
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

    public static String SelectAffiliation(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Affiliation FROM Author WHERE AuthorID = " + id;
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

    public static String SelectEmail(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Email FROM Author WHERE AuthorID = " + id;
                ResultSet res = stmt.executeQuery(query);
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
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Password FROM Author WHERE AuthorID = " + id;
                ResultSet res = stmt.executeQuery(query);
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

    public boolean SelectTemp(int id) throws SQLException {
        boolean fin = true;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Temp FROM Author WHERE AuthorID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getBoolean("Temp");
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
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT AuthorID FROM Author WHERE Email = '" + email + "' AND Password = '" + password + "'";
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    id = res.getInt("AuthorID");
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
                String query = "SELECT AuthorID FROM Author WHERE UserID = '" +id+ "'";
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

    public static int GetUserID(int id) throws SQLException {
        int fin = -1;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT UserID FROM Author WHERE AuthorID = '" +id+ "'";
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

    public static void DeleteTable() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "DROP TABLE Author";
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

    public static int SelectUserID(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT UserID FROM Author WHERE AuthorID = " + id;
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

}