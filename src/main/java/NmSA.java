
public class NmSA {
    public static void main(String[] args) {
        Controller c = new Controller();
        LoggerSingleton logger = LoggerSingleton.getInstance();
        API a = new API(c);
        Database db = new DatabaseFactory().getInstance();
        InputFileReader filereader = new InputFileReader(db, logger);
        //filereader.readInsertAbsence("AbsencePerKlasse.txt");
        //filereader.readInsertTests("Examinations.txt");
        String[] rows = {"ANR", "fname", "lname", "cause", "dateAbsence", "dayOfWeek", "minutes"};
        db.selectRows(rows, "Absence");
        String[] rows1 = {"TNR", "DateOfTest","Kind","Name","TimeBegin","TimeEnd","Subject"};
        db.selectRows(rows1, "Test");

    }
}
