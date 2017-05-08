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
    void createTeachers();
    void insertTeacher(String fname, String lname);
    void createSubject();
    void insertSubject(String token, String desc, String fname, String lname);
    void createStudent();
    void insertStudent(String matnr, String fname, String lname);
    void createAbsence();
    void insertAbsence(int ANR, String fname, String lname, String cause, String date,  String dayOfWeek, int minutes);
    void createTimetable();
    void insertLesson(String dayOfWeek, String timeBegin, String timeEnd, String subject);
    void createTest();
    void insertTest(int TNR, String date, String desc, String timeBegin, String timeEnd, String subject);
    void select(String[] rows, String table);

}

