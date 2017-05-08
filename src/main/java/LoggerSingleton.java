import java.util.ArrayList;

class LoggerSingleton {
    private static LoggerSingleton ourInstance = new LoggerSingleton();
    private View view;

    static LoggerSingleton getInstance() {
        return ourInstance;
    }
    private LoggerSingleton() {}
    private int level = 2;


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
        if (this.isShown(0)) {
            this.view.log(replaceFormat(caller, "ERROR", message));
        }
    }

    void warn(String caller, String message) {
        if (this.isShown(1)) {
            this.view.log(replaceFormat(caller, "WARN", message));
        }
    }

    void info(String caller, String message) {
        if (this.isShown(2)) {
            this.view.log(replaceFormat(caller, "INFO", message));
        }
    }

    void debug(String caller, String message) {
        if (this.isShown(3)) {
            this.view.log(replaceFormat(caller, "DEBUG", message));
        }
    }

    ArrayList<String> getLog() {
        ArrayList<String> test = new ArrayList<>();
        test.add("Test1");
        test.add("Test2");

        return test;
    }
}
