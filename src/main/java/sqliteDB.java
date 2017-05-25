import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public sqliteDB() {
        // jdbc:sqlite::resource:db/NmSA.db
        String DBurl = "jdbc:sqlite::resource:db/NmSA.db";
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DBurl);
            createNewDatabase();
            dropTables();
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


    public void dropTables() {
        String sql = "DROP TABLE IF EXISTS Absence;\n" +
                "DROP TABLE IF EXISTS Lesson;\n" +
                "DROP TABLE IF EXISTS Test;";

        try (Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Failed to drop tables");
        }
    }

    /**
     *
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
        String sql = "CREATE TABLE IF NOT EXISTS Absence (\n"
                + "	ANR INTEGER PRIMARY KEY,\n"
                + "	fname VARCHAR(30) NOT NULL,\n"
                + "	lname VARCHAR(30) NOT NULL,\n"
                + "	cause String NOT NULL,\n"
                + " dateTime VARCHAR(30) NOT NULL,\n"
                + "	dayOfWeek VARCHAR(2) NOT NULL,\n"
                + "	minutes INTEGER NOT NULL\n"
                + ");";

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
     * @param fname     First name of the student
     * @param lname     Last name of the student
     * @param cause     Cause of Absence
     * @param dateTime      Date of Absence
     * @param dayOfWeek Day in a week where a person is absent
     * @param minutes   Absence in minutes
     */
    public void insertAbsence( String fname, String lname, String cause, String dateTime, String dayOfWeek, int minutes) {
        String sql = "INSERT INTO Absence(fname,lname,cause,dateTime,dayOfWeek,minutes) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, cause);
            pstmt.setString(4, dateTime);
            pstmt.setString(5, dayOfWeek);
            pstmt.setInt(6, minutes);
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
                + " Kind VARCHAR(15) NOT NULL, \n"
                + " Name VARCHAR(15) NOT NULL, \n"
                + "	TimeBegin VARCHAR(15),\n"
                + "	TimeEnd VARCHAR(15),\n"
                + "	Subject VARCHAR(5)"
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
     * @param date      Date of Test
     * @param kind      Kind of Test
     * @param desc      Testdescription
     * @param timeBegin Begin of lesson
     * @param timeEnd   End of lesson
     * @param subject   Subject
     */
    public void insertTest(String date, String kind, String desc, String timeBegin, String timeEnd, String subject) {
        String sql = "INSERT INTO Test(DateOfTest,Kind,Name,TimeBegin,TimeEnd,Subject) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, date);
            pstmt.setObject(2, kind);
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
    public void selectRows(String[] rows, String table) {
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

    public ArrayList<AbsenceSummary> getAbsenceSummaries() {
        String query = "select * from Absence";
        ArrayList<AbsenceSummary> list = new ArrayList<AbsenceSummary>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                String name = res.getString("fname") + res.getString("lname");
                int time =  res.getInt("minutes") * 60;

                AbsenceSummary absenceSummary = new AbsenceSummary(name, time);

                list.add(absenceSummary);
            }

            return list;
        } catch (SQLException e) {
            LoggerSingleton.getInstance().error("sqliteDB", e.getMessage());
        }

        return null;
    }

    public ArrayList<AbsenceDetail> getAbsenceDetails() {
        String query = "select * from Absence";
        ArrayList<AbsenceDetail> list = new ArrayList<AbsenceDetail>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                String name = res.getString("fname") + " " + res.getString("lname");
                int time =  res.getInt("minutes") * 60;
                Date date = new SimpleDateFormat("dd.mm.yy hh:mm").parse(res.getString("dateTime"));

                AbsenceDetail absenceDetail = new AbsenceDetail(name, time, date);

                list.add(absenceDetail);
            }

            return list;
        } catch (SQLException e) {
            LoggerSingleton.getInstance().error("sqliteDB", e.getMessage());
        } catch (ParseException e) {
            LoggerSingleton.getInstance().error("sqliteDB", e.getMessage());
        }

        return null;
    }

    public ArrayList<AbsenceDetail> getRanking() {
        String query = "SELECT sum(minutes), fname, lname\n" +
                "FROM Absence\n" +
                "GROUP BY lname, fname\n" +
                "ORDER BY sum(minutes) desc";

        ArrayList<AbsenceDetail> list = new ArrayList<AbsenceDetail>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
                String name = res.getString("fname") + " " + res.getString("lname");
                int time =  res.getInt("sum(minutes)") / 60;

                AbsenceDetail absenceDetail = new AbsenceDetail(name, time, null);

                list.add(absenceDetail);
            }

            return list;
        } catch (SQLException e) {
            LoggerSingleton.getInstance().error("sqliteDB", e.getMessage());
        }

        return null;
    }

    public int[] getAbsencePerDay(String name) {
        String fname = name.split(" ")[0];
        String lname = name.split(" ")[1];

        String query = "SELECT SUM(minutes), dayOfWeek\n" +
                "from ABSENCE\n" +
                "WHERE fname = \"" + fname + "\" AND lname = \"" + lname + "\"\n" +
                "GROUP BY dayOfWeek";

        int[] arr = new int[6];

        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()) {
               switch (res.getString("dayOfWeek")) {
                   case "Mo":
                       arr[0] = res.getInt("sum(minutes)");
                       break;
                   case "Di":
                       arr[1] = res.getInt("sum(minutes)");
                       break;
                   case "Mi":
                       arr[2] = res.getInt("sum(minutes)");
                       break;
                   case "Do":
                       arr[3] = res.getInt("sum(minutes)");
                       break;
                   case "Fr":
                       arr[4] = res.getInt("sum(minutes)");
                       break;
                   case "Sa":
                       arr[5] = res.getInt("sum(minutes)");
                       break;
               }
            }

            return arr;
        } catch (SQLException e) {
            LoggerSingleton.getInstance().error("sqliteDB", e.getMessage());
        }

        return null;
    }
}