import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class LoggerSingleton {
    private static LoggerSingleton ourInstance = new LoggerSingleton();
    private View view;

    static LoggerSingleton getInstance() {
        return ourInstance;
    }
    private LoggerSingleton() {}
    private int level = 0; // 0 = Error, 1 = Warn, 2 = Info, 3 = Debug
    private ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();


    void setView(View view) {
        this.view = view;
    }

    void setLogLevel(int level) {
        this.level = level;
    }

    private String getFormat() {
        return "[%CALLER] %LEVEL: %MESSAGE";
    }

    private String replaceFormat(String caller, String level, String message) {
        String logMessage = this.getFormat();
        logMessage = logMessage.replace("%LEVEL", level);
        logMessage = logMessage.replace("%CALLER", caller);
        logMessage = logMessage.replace("%MESSAGE", message);

        return logMessage;
    }

    private boolean isShown(int level) {
        return level <= this.level;
    }

    void error(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 0));
    }

    void warn(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 1));
    }

    void info(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 2));
    }

    void debug(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 3));
    }

    List<LogEntry> getLogEntries() {
        Iterator<LogEntry> iterator = this.logEntries.iterator();
        ArrayList<LogEntry> filteredLogEntries = new ArrayList<LogEntry>();

        while( iterator.hasNext() ) {
            LogEntry entry = iterator.next();

            if(isShown(entry.level)) {
                filteredLogEntries.add(entry);
            }
        }

        return filteredLogEntries;
    }
}
