/**
 * name: Glavanits Marcel
 * matnr.: i14075
 * catnr.: 03
 * Created on 04.04.2017
 * file: DatabaseFactor
 * Class: 3CHIF
 */

public class DatabaseFactory {

    Database instance = null;

    public Database getInstance() {

        if(instance == null) {
            instance = new sqliteDB();
        }
        return instance;
    }
}

