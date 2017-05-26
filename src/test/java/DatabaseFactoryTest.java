import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Sascha on 26.05.2017.
 */
class DatabaseFactoryTest {
    @Test
    void getDatabase() {
        assertNotNull(new DatabaseFactory());
    }
}