/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 04.04.2017
 * file: DatabaseFactory
 * Class: 3CHIF
 */

public class DatabaseFactory {

    private static sqliteDB instance = new sqliteDB();

    public Database getDatabase() {
        return instance;
    }
}

