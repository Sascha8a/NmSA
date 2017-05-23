import java.util.ArrayList;

/**
 * Created by UltraKnecht on 02.05.2017.
 */
public class Model {
    public Model() {
        Database db = new DatabaseFactory().getDatabase();
        InputFileReader fileReader = new InputFileReader(db);

        fileReader.readInsertAbsence("AbsencePerStudent.txt");
        fileReader.readInsertTests("Examinations.txt");
    }

    ArrayList<AbsenceDetail> getAbsenceDetails() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsenceDetails();
    }

    ArrayList<AbsenceSummary> getAbsenceSummaries() {
        Database db = new DatabaseFactory().getDatabase();
        return db.getAbsenceSummaries();
    }
}
