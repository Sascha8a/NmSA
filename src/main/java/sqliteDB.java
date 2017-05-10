import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * name: Glavanits Marcel
 * matnr.: i14075
 * catnr.: 03
 * Created on 04.04.2017
 * file: sqliteDB
 * Class: 3CHIF
 */

public class sqliteDB implements Database {

    private static Connection conn;
    private LoggerSingleton logger = LoggerSingleton.getInstance();

    sqliteDB() {

        String DBurl = "jdbc:sqlite:src/main/resources/db/NmSA.db";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DBurl);
            createNewDatabase();
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void printErrorIfRelevant(SQLException e) {
        if (e.getErrorCode() != 19) {
            logger.debug("sqliteDB", e.getMessage());
        }
    }

    /**
     * Create all tables
     */
    public void createTables() {
        createAbsence();
        createTimetable();
        createTest();
    }


    /**
     * Connect to a sample database
     */
    public void createNewDatabase() {

        try {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                LoggerSingleton.getInstance().info("sqliteDB", "The driver name is " + meta.getDriverName());
                LoggerSingleton.getInstance().info("sqliteDB", "A new database has been created.");
            }

        } catch (SQLException e) {
            this.printErrorIfRelevant(e);
        }
    }


    /**
     * Create table Absence in the database
     */
    public void createAbsence() {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Absence (\n" +
                " ANR  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " fname VARCHAR(30) NOT NULL,\n" +
                " lname VARCHAR(30) NOT NULL,\n" +
                " cause VARCHAR(30) NOT NULL,\n" +
                " dateAbsence DATE NOT NULL,\n" +
                "dayOfWeek VARCHAR(2) NOT NULL,\n" +
                "minutes INTEGER NOT NULL\n" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create table Absence");
        }
    }

    /**
     * Insert a new row into the Absence table
     *
     * @param fname     First name
     * @param lname     Last name
     * @param cause     Cause of Absence
     * @param date      Date of Absence
     * @param dayOfWeek Day in a week where a person is absent
     * @param minutes   Absence in minutes
     */
    public void insertAbsence( String fname, String lname, String cause, String date, String dayOfWeek, int minutes) {
        String sql = "INSERT INTO Absence(fname,lname,cause,dateAbsence,dayOfWeek,minutes) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, cause);
            pstmt.setString(4, date);
            pstmt.setObject(5, dayOfWeek);
            pstmt.setObject(6, minutes);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            this.printErrorIfRelevant(e);
        }
    }

    /**
     * Create table Lesson in the database
     */
    public void createTimetable() {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Lesson (\n" +
                " ID integer primary key autoincrement,\n" +
                " validFrom date not null,\n" +
                " validTo date not null,\n" +
                " dayOfWeek VARCHAR(10),\n" +
                " TimeBegin VARCHAR(15),\n" +
                " TimeEnd VARCHAR(15),\n" +
                " subject VARCHAR(5)\n" +
                " );";

        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create table Lesson");
        }
    }

    /**
     * Insert a new row into the Lesson table
     *
     * @param validFrom Date, the Entry is valid from
     * @param validTo  Date, the Entry is valid until
     * @param dayOfWeek Day of the week
     * @param timeBegin Begin of lesson
     * @param timeEnd   End of lesson
     * @param subject   Subject
     */
    public void insertLesson(Date validFrom, Date validTo, String dayOfWeek, String timeBegin, String timeEnd, String subject) {
        String sql = "INSERT INTO Lesson VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dayOfWeek);
            pstmt.setString(2, timeBegin);
            pstmt.setString(3, timeEnd);
            pstmt.setString(4, subject);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            this.printErrorIfRelevant(e);
    }
    }

    /**
     * Create table Test in the database
     */
    public void createTest() {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Test (\n"
                + "	TNR INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " DateOfTest DATE NOT NULL, \n"
                + " Description VARCHAR(15) NOT NULL, \n"
                + "	TimeBegin VARCHAR(15),\n"
                + "	TimeEnd VARCHAR(15),\n"
                + "	Subject VARCHAR(5) REFERENCES Subject"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Failed to create table Test");
        }
    }

    /**
     * Insert a new row into the Test table
     *
     * @param TNR       Autoincrement
     * @param date      Date of Test
     * @param desc      Testdescription
     * @param timeBegin Begin of lesson
     * @param timeEnd   End of lesson
     * @param subject   Subject
     */
    public void insertTest(int TNR, String date, String desc, String timeBegin, String timeEnd, String subject) {
        String sql = "INSERT INTO Test VALUES(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, TNR);
            pstmt.setObject(2, date);
            pstmt.setString(3, desc);
            pstmt.setString(4, timeBegin);
            pstmt.setString(5, timeEnd);
            pstmt.setString(6, subject);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            this.printErrorIfRelevant(e);
        }
    }

    /**
     * select rows in a table
     */
    public void select(String[] rows, String table) {
        String sql = "SELECT * FROM ?";
        StringBuilder select = new StringBuilder();
        for (String row : rows) {
            select.append(row + ",");
        }

        String temp = select.substring(0, select.length() - 1);
        sql = sql.replace("*", temp);
        sql = sql.replace("?", table);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // loop through the result set
            System.out.println("------------------------------------------------------------");
            System.out.println("Result: Table " + table);
            for (String row : rows) {
                System.out.printf(row + "|");
            }
            System.out.println();
            while (rs.next()) {
                for (String row : rows) {
                    System.out.printf(rs.getObject(row) + "|");
                }
                System.out.println();
            }
            System.out.println("------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

