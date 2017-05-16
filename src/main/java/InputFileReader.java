import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class InputFileReader {

    private Database db;
    private LoggerSingleton logger;

    InputFileReader(Database db) {
        this.db = db;
        this.logger = LoggerSingleton.getInstance();
    }

    /**
     *
     * @param filename Filename where the data for the AbsenceSummary table is
     */
    void readInsertAbsence(String filename) {
        //read lines and insert into AbsenceSummary
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
            logger.error("InputFileReader", "AbsenceSummary File " + filename + " was not found");
        }catch ( Exception e1) {
            logger.error("InputFileReader", e1.getMessage());
        }
    }


    /**
     *
     * @param filename Filename where the data for the Tests table is
     */
    void readInsertTests(String filename) {
        //read lines and insert into AbsenceSummary
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
            logger.error("InputFileReader", "Tests File " + filename + " was not found");
        }catch ( Exception e1) {
            logger.error("InputFileReader", e1.getMessage());
        }
    }

}
