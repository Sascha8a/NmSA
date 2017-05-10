import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class InputFileReader {

    private Database db;

    InputFileReader(Database db) {
        this.db = db;
    }

    /**
     *
     * @param filename Filename where the Absence data is
     */
    void readInsertAbsence(String filename) {
        //read lines and insert into Absence
        try {
            Scanner sc = new Scanner(new FileReader(System.getProperty("user.dir") + "/src/main/resources/inputFiles/" + filename));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> data = Arrays.asList(line.split("\\t"));
                List<String> name = Arrays.asList(data.get(0).split(" "));
                db.insertAbsence(name.get(1), name.get(0), data.get(8), data.get(4), data.get(5), Integer.parseInt(data.get(7)));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void readInsertTests(String filename) {
        //read lines and insert into Absence
        try {
            Scanner sc = new Scanner(new FileReader(System.getProperty("user.dir") + "/src/main/resources/inputFiles/" + filename));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> data = Arrays.asList(line.split("\\t"));
                db.insertTest(data.get(4), data.get(0), data.get(1), data.get(5), data.get(6), data.get(8));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
