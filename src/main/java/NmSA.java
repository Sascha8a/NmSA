
public class NmSA {
    public static void main(String[] args) {
        Controller c = new Controller();
        API a = new API(c);

        Database db = new DatabaseFactory().getInstance();
        db.insertTeacher( "Markus", "Reis");
        db.insertTeacher("Harald", "Haberstroh");
        db.insertTeacher("Sabine", "Steiger-Lechner");
        db.insertStudent("i14085", "Alexander", "Lampalzer");
        db.insertStudent("i14076", "Matthias", "Grill");
        db.insertStudent("i14084", "Simon", "Königsreiter");

        String[] rows = { "fname", "lname"};
        db.select(rows, "Teacher ");
        String[] rows2 = {"Matnr", "fname", "lname"};
        db.select(rows2, "Student");
    }
}
