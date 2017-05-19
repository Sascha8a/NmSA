import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by UltraKnecht on 15.05.2017.
 */
public class LogEntry {

    public String message;
    public String caller;
    public String timestamp;
    public int level;

    public LogEntry(String msg, String caller, int level) {
        this.message = msg;
        this.caller = caller;
        this.level = level;
        this.timestamp = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss:SS z").format(new Date());
    }
}
