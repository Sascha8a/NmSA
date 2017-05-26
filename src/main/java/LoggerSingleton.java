import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

class LoggerSingleton {
    public static LoggerSingleton ourInstance = new LoggerSingleton();
    public ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
    private int level = 4; // 0 = Error, 1 = Warn, 2 = Info, 3 = Debug

    static LoggerSingleton getInstance() {
        PrintStream origOut = System.out;
        PrintStream interceptor = new Interceptor(origOut);
        System.setOut(interceptor);
        return LoggerSingleton.ourInstance;
    }

    private LoggerSingleton() {}

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

    public void error(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 0));
    }

    public void warn(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 1));
    }

    public void info(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 2));
    }

    public void noConsole(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 2));
    }

    public void debug(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 3));
    }

    ArrayList<LogEntry> getLogEntries() {
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

class Interceptor extends PrintStream {
    public Interceptor(OutputStream out)
    {
        super(out, true);
    }
    @Override
    public void print(String s) {
        LoggerSingleton.getInstance().noConsole("Interceptor", s);
    }
}