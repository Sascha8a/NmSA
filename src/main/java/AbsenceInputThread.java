/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 26.05.2017
 * file: AbsenceInputThread
 * Class: 3CHIF
 */

public class AbsenceInputThread implements Runnable {
    private Database db;
    private String fname;
    private String lname;
    private String cause;
    private String dateTime; //Date + time of Absence
    private String dayOfWeek;
    private int minutes;

    AbsenceInputThread(Database db, String fname, String lname, String cause, String dateTime, String dayOfWeek, int minutes){
        this.db = db;
        this.fname = fname;
        this.lname = lname;
        this.cause = cause;
        this.dateTime = dateTime;
        this.dayOfWeek = dayOfWeek;
        this.minutes = minutes;
    }

    @Override
    public void run() {
        db.insertAbsence(this.fname, this.lname, this.cause, this.dateTime, this.dayOfWeek, this.minutes);
    }
}
