import java.util.ArrayList;

/**
 * Created by UltraKnecht on 02.05.2017.
 */

public class Model {
    private Database db;

    public Model() {
        this.db = new DatabaseFactory().getDatabase();
    }

    public void updateAbsence(String path) {
        InputFileReader fileReader = new InputFileReader(db);

        fileReader.readInsertAbsence(path);
    }

    public void updateTests(String path) {
        InputFileReader fileReader = new InputFileReader(db);

        fileReader.readInsertTests(path);
    }

    ArrayList<AbsenceDetail> getAbsenceDetails() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsenceDetails();
    }

    ArrayList<AbsenceSummary> getAbsenceSummaries() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsenceSummaries();
    }

    ArrayList<AbsenceDetail> getRanking() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getRanking();
    }

    public int[] getAbsencePerDay(String name) {
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
