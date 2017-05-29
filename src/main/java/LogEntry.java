import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 15.05.2017
 * file: LogEntry
 * Class: 3CHIF
 */

public class LogEntry {

    public String message;
    public String caller;
    public String timestamp;
    public int level;

    LogEntry(String msg, String caller, int level) {
        this.message = msg;
        this.caller = caller;
        this.level = level;
        this.timestamp = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss:SS z").format(new Date());
    }
}
