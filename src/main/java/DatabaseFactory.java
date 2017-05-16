/**
 * name: Glavanits Marcel
 * matnr.: i14075
 * catnr.: 03
 * Created on 04.04.2017
 * file: DatabaseFactor
 * Class: 3CHIF
 */

public class DatabaseFactory {

    static sqliteDB instance = new sqliteDB();

    public Database getDatabase() {
        return instance;
    }
}

