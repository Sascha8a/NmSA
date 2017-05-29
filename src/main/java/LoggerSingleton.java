import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 30.04.2017
 * file: LoggerSingleton
 * Class: 3CHIF
 */

class LoggerSingleton {
    private static LoggerSingleton ourInstance = new LoggerSingleton();
    private ArrayList<LogEntry> logEntries = new ArrayList<>();
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

    void noConsole(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 2));
    }

    public void debug(String caller, String message) {
        this.logEntries.add(new LogEntry(message, caller, 3));
    }

    ArrayList<LogEntry> getLogEntries() {
        Iterator<LogEntry> iterator = this.logEntries.iterator();
        ArrayList<LogEntry> filteredLogEntries = new ArrayList<>();

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
    Interceptor(OutputStream out)
    {
        super(out, true);
    }
    @Override
    public void print(String s) {
        LoggerSingleton.getInstance().noConsole("Interceptor", s);
    }
}