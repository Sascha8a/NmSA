
public class NmSA {
    public static void main(String[] args) {
        Controller c = new Controller();
        API a = new API(c);
        Database db = new DatabaseFactory().getInstance();
        InputFileReader filereader = new InputFileReader(db);
        //filereader.readInsertAbsence("AbsencePerKlasse (1).txt");
        //filereader.readInsertTests("Examinations.txt");
        String[] rows = {"ANR", "fname", "lname", "cause", "dateAbsence", "dayOfWeek", "minutes"};
        db.select(rows, "Absence");
        String[] rows1 = {"TNR", "DateOfTest","Kind","Name","TimeBegin","TimeEnd","Subject"};
        db.select(rows1, "Test");

    }
}
