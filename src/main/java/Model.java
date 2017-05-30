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

    /**
     * Reads the Absence file
     * @param path path to the file
     */
    void updateAbsence(String path) {
        InputFileReader fileReader = new InputFileReader(db);

        fileReader.readInsertAbsence(path);
    }

    /**
     * Reads the Test file
     * @param path path to the file
     */
    void updateTests(String path) {
        InputFileReader fileReader = new InputFileReader(db);

        fileReader.readInsertTests(path);
    }

    /**
     * Get the Absence details
     */
    ArrayList<AbsenceDetail> getAbsenceDetails() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsenceDetails();
    }

    /**
     * Gets the Ranking
     */
    ArrayList<AbsenceDetail> getRanking() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getRanking();
    }

    /**
     * Gets the Absence per day
     */
    int[] getAbsencePerDay(String name) {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsencePerDay(name);
    }

    /**
     * Gets the amount of tests present
     */
    public int getAmountTestPresent(String name) {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAmountTestPresent(name);
    }

    /**
     * Gets the total amount of tests
     */
    public int getTestAmount() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getTestAmount();
    }

    /**
     * Gets the monthly average of absence
     */
    public int[] getMonthAverage() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getMonthAverage();
    }
}
