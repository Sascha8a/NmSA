import java.util.Date;

/**
 * Created by UltraKnecht on 15.05.2017.
 */
public class LogEntry {

    public String msg;
    public String caller;
    public long timestamp;
    public int level;

    public LogEntry(String msg, String caller, int level) {

        this.msg = msg;
        this.caller = caller;
        this.level = level;
        timestamp = new Date().getTime();
    }
}
