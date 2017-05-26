/**
 * Created by UltraKnecht on 26.05.2017.
 */
public class TestInputThread implements Runnable{

    private Database db;
    private String date;
    private String kind;
    private String desc;
    private String timeBegin;
    private String timeEnd;
    private String subject;

    public TestInputThread(Database db, String date, String kind, String desc, String timeBegin, String timeEnd, String subject) {
        this.db = db;
        this.date = date;
        this.kind = kind;
        this.desc = desc;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.subject = subject;
    }

    @Override
    public void run() {
        db.insertTest(this.date, this.kind, this.desc, this.timeBegin, this.timeEnd, this.subject);
    }
}
