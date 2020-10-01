package com.example.hivemanager;

import java.io.File;
import java.sql.*;

/**
 * This class is an object that connects to a database server to access a
 * database 'spike_exercise'. It will be initialized when the program requires
 * access to the server
 *
 * @author
 *
 */
public class SQLConnection {

    // Strings that is required to access the database server
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://spike-exercise.cpahsyvhsld2.us-east-2.rds.amazonaws.com/spike_exercise?autoReconnect=true&useSSL=false";

    // Authorized account in the databsase server
    private final String USERNAME = "admin";
    private final String PASSWORD = "administrator";

    private Connection conn; // Variable that connects to database
    private Statement stmt; // Execute query statement
    private String sql; // String that stores SQL query
    private ResultSet rs; // Gets the data from a query
    private String status; // Gets output parameter of stored procedures in the database
    private CallableStatement cstmt; // Execute query statement

    /**
     * Constructor that initializes class variables and connects to database
     */
    public SQLConnection() {
        try {
            // Initializes class variables
            sql = null;
            rs = null;
            status = null;
            cstmt = null;

            // Connects to the database in the server
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();

            System.out.println("Database is successfully Connected");
            System.out.println();

        } catch (ClassNotFoundException e) {
            System.out.println("SQLConnection: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLConnection: " + e.getMessage());
        }
    }

    /**
     * This method is called at the end of the app program to terminate connection
     * to the database
     */
    protected void closeConnection() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Close Connection: " + e.getMessage());
        }
    }

    /**
     * This method is called when the user creates an account. It calls
     * createAccount stored procedure from the database to create the account.
     *
     * @param username String for the user account
     * @param password String for the password
     * @param picture  Blob that refers to the picture uploaded by the user
     * @param apiary   String for the initial apiary address
     * @param email    String for the user's email
     * @param phone    String for the user's phone
     */
    protected void createAccount(String username, String password, Blob picture, String apiary, String email,
                                 String phone) {

        try {
            // Query to initialize createAccount stored procedure in the database
            cstmt = conn.prepareCall("call createAccount(?,?,?,?,?,?,?);");
            cstmt.setString(1, username);
            cstmt.setString(2, password);
            cstmt.setBlob(3, picture);
            cstmt.setString(4, apiary);
            cstmt.setString(5, email);
            cstmt.setString(6, phone);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            cstmt.executeUpdate();

            // Get output parameter from the procedure
            status = cstmt.getString(7);

            // Checks if the query is executed successfully by output parameter
            if (status.equals("Success")) {
                System.out.println("Successfully created account");
            } else {
                System.out.println("The acount already exists");
            }
        } catch (SQLException e) {
            System.out.println("createAccount: " + e.getMessage());
        }

    }

    /**
     * This method is called when the user tries to log in in the app. It calls
     * searchAccount stored procedure from the database to check if the user account
     * with the password exists.
     *
     * @param username String variable for the user account
     * @param password String variable for the password
     */
    protected void searchAccount(String username, String password) {
        try {
            // Execute query to call searchAccount procedure
            cstmt = conn.prepareCall("call searchAccount(?,?,?)");
            cstmt.setString(1, username);
            cstmt.setString(2, password);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.executeUpdate();

            // Gets output parameter of the procedure
            status = cstmt.getString(3);

            // Checks if the query is executed successfully by output parameter
            if (status.equals("Username")) {
                System.out.println("The account does not exist");
            } else if (status.equals("Password")) {
                System.out.println("The password is wrong");
            } else {
                System.out.println("Successfully logged in");
            }
        } catch (SQLException e) {
            System.out.println("searchAccount: " + e.getMessage());
        }
    }

    /**
     * This method is called when the user updates the user's profile. It will
     * execute updateProfile stored procedure from the database to update the
     * profile of the user's account
     *
     * @param username String for the user's account
     * @param picture  Blob format for the picture uploaded by the user
     * @param email    String for the user's email
     * @param phone    String for the user's phone
     */
    protected void updateProfile(String username, Blob picture, String email, String phone) {

        try {
            // Execute query to call updateProfile procedure
            cstmt = conn.prepareCall("call updateProfile(?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setBlob(2, picture);
            cstmt.setString(3, email);
            cstmt.setString(4, phone);
            cstmt.execute();
            System.out.println("Successfully updated profile");
        } catch (SQLException e) {
            System.out.println("updateProfile: " + e.getMessage());
        }
    }

    /**
     * This method is called to display information about the user's profile by
     * executing displayProfile stored procedure from the database
     *
     * @param username String for the user's account
     * @return rs ResultSet that stores the data from the query.
     */
    protected ResultSet displayProfile(String username) {
        try {
            // Execute query to call displayProfile stored procedure
            sql = "call displayProfile(\"" + username + "\");";
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("displayProfile: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method is called when the user creates a hive into the database. It
     * calls createHive stored procedure to store the new information of new hive
     * into the database
     *
     * @param username        String for the user account
     * @param apiary          String for the apiary address of hive
     * @param hive            String for the name of hive
     * @param inspection      String for inspection result
     * @param health          String for health
     * @param honey           String for honey stores
     * @param queenproduction String for queen production
     * @param equiphive       String for equipment on the hive
     * @param equipinven      String for equipment in inventory
     * @param loss            int for losses
     * @param gain            int for gains
     */
    protected void createHive(String username, String apiary, String hive, String inspection, String health,
                              String honey, String queenproduction, String equiphive, String equipinven, int loss, int gain) {

        // Execute query to call createHive stored procedure
        try {
            cstmt = conn.prepareCall("call createHive(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.setString(3, hive);
            cstmt.setString(4, inspection);
            cstmt.setString(5, health);
            cstmt.setString(6, honey);
            cstmt.setString(7, queenproduction);
            cstmt.setString(8, equiphive);
            cstmt.setString(9, equipinven);
            cstmt.setInt(10, loss);
            cstmt.setInt(11, gain);
            cstmt.registerOutParameter(12, Types.VARCHAR);
            cstmt.executeUpdate();

            // Get output parameter from the stored procedure
            status = cstmt.getString(12);

            // Checks if the query is executed successfully by output parameter
            if (status.equals("Exists")) {
                System.out.println("The hive already exists");
            } else if (status.equals("DNEApiary")) {
                System.out.println("No user with the apiary adress exists");
            } else {
                System.out.println("The hive is successfully created");
            }
        } catch (SQLException e) {
            System.out.println("createHive: " + e.getMessage());
        }

    }

    /**
     * This method is called to display information of hives owned by the user. It
     * executes displayHive stored procedure from the database.
     *
     * @param username String for the user's account
     *
     * @return rs ResultSet that stores the data from the database
     */
    protected ResultSet displayHive(String username) {
        try {
            sql = "call displayHive(\"" + username + "\");";
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("displayHive: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method is called to update a hive that is specified by the user account,
     * the apiary address and the name of the hive. It executes updateHive stored
     * procedure. It restricts the user to change the apiary adress and the name of
     * hive.
     *
     * @param username        String for the user account
     * @param apiary          String for the apiary address of hive
     * @param hive            String for the name of hive
     * @param inspection      String for inspection result
     * @param health          String for health
     * @param honey           String for honey stores
     * @param queenproduction String for queen production
     * @param equiphive       String for equipment on the hive
     * @param equipinven      String for equipment in inventory
     * @param loss            int for losses
     * @param gain            int for gains
     */
    protected void updateHive(String username, String apiary, String hive, String inspection, String health,
                              String honey, String queenproduction, String equiphive, String equipinven, int loss, int gain) {

        // Execute query to call updateHive stored procedure
        try {
            cstmt = conn.prepareCall("call updateHive(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.setString(3, hive);
            cstmt.setString(4, inspection);
            cstmt.setString(5, health);
            cstmt.setString(6, honey);
            cstmt.setString(7, queenproduction);
            cstmt.setString(8, equiphive);
            cstmt.setString(9, equipinven);
            cstmt.setInt(10, loss);
            cstmt.setInt(11, gain);
            cstmt.executeUpdate();

            System.out.println("Successfully updated Hive");
        } catch (SQLException e) {
            System.out.println("updateHive: " + e.getMessage());
        }

    }

    /**
     * This method is called to delete a hive specified by the username, apiary
     * address, and the name of hive by executing a delteHive stored procedures in
     * the database.
     *
     * @param username String for the user's account
     * @param apiary   String for the user's apiary address
     * @param hive     String for the name of hive
     */
    protected void deleteHive(String username, String apiary, String hive) {
        try {
            // Execute query to call deleteHive stored procedure
            cstmt = conn.prepareCall("call deleteHive(?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.setString(3, hive);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.executeUpdate();

            status = cstmt.getString(4);

            // Checks if the query is executed successfully by the output parameter
            if (status.equals("DNE")) {
                System.out.println("Such hive does not exist");
            } else {
                System.out.println("Successfully deleted the hive");
            }
        } catch (SQLException e) {
            System.out.println("Error in deleteHive: " + e.getMessage());
        }
    }

    /**
     * This method is called when the user creates a new apiary by calling
     * createApiary stored procedure. If there is a apiary with the same name, it
     * does not create the new apiary. If the user creates a apiary that is not
     * belong to the user, it does not create the new apiary.
     *
     * @param username String for the user's account
     * @param apiary   String for the new apiary address
     */
    protected void createApiary(String username, String apiary) {
        try {
            // Execute createApiary stored procedure
            cstmt = conn.prepareCall("call createApiary(?,?,?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.executeUpdate();
            status = cstmt.getString(3);

            // Check if the query executed successfully by output parameter
            if (status.equals("Exist")) {
                System.out.println("The appiary already exists");
            } else if (status.equals("NoUser")) {
                System.out.println("Such account does not exist");
            } else {
                System.out.println("Successfully created apiary");
            }
        } catch (SQLException e) {
            System.out.println("createApiary: " + e.getMessage());
        }
    }

    /**
     * This method is called when the user edits the name of the appiary. It
     * executes updateApiary stored procedure. It does not allow the user to change
     * non-existing apiary and to update a apiary to another apiary's name that
     * already exists.
     *
     * @param username  String for the user account
     * @param oldpiary  String for the original name of apiary
     * @param newapiary String for the new name of apiary
     */
    protected void updateApiary(String username, String oldpiary, String newapiary) {
        try {
            // Execute updateApiary stored procedure
            cstmt = conn.prepareCall("call updateApiary(?, ?, ?, ?);");

            cstmt.setString(1, username);
            cstmt.setString(2, oldpiary);
            cstmt.setString(3, newapiary);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.executeUpdate();
            status = cstmt.getString(4);

            // Check if the query executed successfully by output parameter
            if (status.equals("DNE")) {
                System.out.println("No appiary exist");
            } else if (status.equals("Exist")) {
                System.out.println("Appiary exist with the name");
            } else {
                System.out.println("Successfully updated apiary");
            }
        } catch (SQLException e) {
            System.out.println("updateApiary: " + e.getMessage());
        }
    }

    /**
     * This method is called when the user deletes the user's apiary.
     *
     * @param username String for the user account
     * @param apiary   String for the user apiary
     */
    protected void deleteApiary(String username, String apiary) {
        try {
            // Execute delteApiary stored procedure
            cstmt = conn.prepareCall("call deleteApiary(?, ?, ?);");

            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            cstmt.executeUpdate();
            status = cstmt.getString(3);

            // Check if the query executed successfully by output parameter
            if (status.equals("DNE")) {
                System.out.println("Such apiary does not exist");
            } else {
                System.out.println("Successfully deleted apiary");
            }

        } catch (SQLException e) {
            System.out.println("deleteApiary: " + e.getMessage());
        }

    }

    protected ResultSet displayApiary(String username) {
        try {
            cstmt = conn.prepareCall("call displayApiary(?);");
            cstmt.setString(1, username);
            rs = cstmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            System.out.println("displayApiuary: " + e.getMessage());
            return null;
        }
    }
    /**
     * This method is to display selected information of hives owned by the user
     *
     * @param username        String for the user account
     * @param inspection      boolean to display the data for inspection
     * @param health          boolean to display the data for health
     * @param honey           boolean to display the data for honey
     * @param queenproduction boolean to display queen production
     * @param equiphive       boolean to display equipment on hive
     * @param equipinven      boolean to display euipmentin inventory
     * @param loss            boolean to display loss
     * @param gain            boolean to display gain
     */
    protected ResultSet hiveList(String username, boolean inspection, boolean health, boolean honey, boolean queenproduction,
                                 boolean equiphive, boolean equipinven, boolean loss, boolean gain) {

        // Create sql statement
        sql = "select uh.username, uh.apiary, uh.hive";

        // Continue writing sql statement based on the boolean values
        if (inspection == true) {
            sql = sql.concat(", uh.inspection");
        }
        if (health == true) {
            sql = sql.concat(", uh.health");
        }
        if (honey == true) {
            sql = sql.concat(", uh.honey");
        }
        if (queenproduction == true) {
            sql = sql.concat(", uh.queenproduction");
        }
        if (equiphive == true) {
            sql = sql.concat(", uh.equiphive");
        }
        if (equipinven == true) {
            sql = sql.concat(", uh.equipinven");
        }
        if (loss == true) {
            sql = sql.concat(", uh.loss");
        }
        if (gain == true) {
            sql = sql.concat(", uh.gain");
        }

        // Finalize the sql statement
        sql = sql.concat(" from userhive uh where uh.username = \"" + username + "\";");

        try {
            // Execute the query
            rs = stmt.executeQuery(sql);

            return rs;
            // Print data received from the query

        } catch (SQLException e) {
            System.out.println("listHive: " + e.getMessage());
            return null;
        }
    }

}
