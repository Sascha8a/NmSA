import Logging.LogEntry;
import Logging.LoggerSingleton;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoggerSingletonTest {
    @Test
    void getInstance() {
        assertNotNull(LoggerSingleton.getInstance());

        assertEquals(LoggerSingleton.getInstance(), LoggerSingleton.getInstance());
    }

    @Test
    void setLogLevel() {
        LoggerSingleton instance = LoggerSingleton.getInstance();
        instance.setLogLevel(0);
        instance.warn("Unit Tests", "Test1");
        assertEquals(instance.getLogEntries(), new ArrayList<LogEntry>());

        instance.setLogLevel(1);
        assertNotEquals(instance.getLogEntries(), new ArrayList<LogEntry>());

    }

    @Test
    void getLogEntries() {
        LoggerSingleton instance = LoggerSingleton.getInstance();
        instance.setLogLevel(1);
        instance.warn("Unit Tests", "Test1");
        instance.warn("Unit Tests", "Test2");
        instance.warn("Unit Tests", "Test3");
        assertTrue(instance.getLogEntries().size() == 3);
    }
}