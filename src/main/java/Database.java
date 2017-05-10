import java.util.Date;

/**
 * name: Glavanits Marcel
 * matnr.: i14075
 * catnr.: 03
 * Created on 04.04.2017
 * file: Database
 * Class: 3CHIF
 */

public interface Database {
    void createTables();
    void createNewDatabase();
    void createAbsence();
    void insertAbsence(String fname, String lname, String cause, String date,  String dayOfWeek, int minutes);
    void createTimetable();
    void insertLesson(Date validFrom, Date validTo, String dayOfWeek, String timeBegin, String timeEnd, String subject);
    void createTest();
    void insertTest(String date, String kind, String desc, String timeBegin, String timeEnd, String subject);
    void select(String[] rows, String table);

}

