import java.util.ArrayList;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 02.05.2017
 * file: Model
 * Class: 3CHIF
 */

public class Model {
    private Database db;

    public Model() {
        this.db = new DatabaseFactory().getDatabase();
    }

    void updateAbsence(String path) {
        InputFileReader fileReader = new InputFileReader(db);

        fileReader.readInsertAbsence(path);
    }

    void updateTests(String path) {
        InputFileReader fileReader = new InputFileReader(db);

        fileReader.readInsertTests(path);
    }

    ArrayList<AbsenceDetail> getAbsenceDetails() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsenceDetails();
    }

    ArrayList<AbsenceDetail> getRanking() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getRanking();
    }

    int[] getAbsencePerDay(String name) {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsencePerDay(name);
    }

    public int getAmountTestPresent(String name) {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAmountTestPresent(name);
    }

    public int getTestAmount() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getTestAmount();
    }

    public int[] getMonthAverage() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getMonthAverage();
    }
}
