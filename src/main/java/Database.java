import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    void insertAbsence(String fname, String lname, String cause, String dateTime,  String dayOfWeek, int minutes);
    void createTest();
    void insertTest(String date, String kind, String desc, String timeBegin, String timeEnd, String subject);
    ArrayList<AbsenceDetail> getRanking();
    ArrayList<AbsenceSummary> getAbsenceSummaries();
    ArrayList<AbsenceDetail> getAbsenceDetails();
    int[] getAbsencePerDay(String name);
}

