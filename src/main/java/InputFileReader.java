import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
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

    private String convertPairToDate(int hour, String day) throws ParseException {
        String startTime = "";
        switch (hour) {
            case 1:
                startTime = "7:50";
                break;
            case 2:
                startTime = "8:40";
                break;
            case 3:
                startTime = "9:35";
                break;
            case 4:
                startTime = "10:35";
                break;
            case 5:
                startTime = "11:30";
                break;
            case 6:
                startTime = "12:25";
                break;
            case 7:
                startTime = "13:25";
                break;
            case 8:
                startTime = "14:20";
                break;
            case 9:
                startTime = "15:15";
                break;
            case 10:
                startTime = "16:10";
                break;
        }

        return day + " " + startTime;
    }

    /**
     *
     * @param path Filename where the data for the Absence table is
     */
    void readInsertAbsence(String path) {
        //read lines and insert into AbsenceSummary
        try {
            Scanner sc = new Scanner(new FileReader(path));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> data = Arrays.asList(line.split("\\t"));
                List<String> name = Arrays.asList(data.get(0).split(" "));
                db.insertAbsence(name.get(1), name.get(0), data.get(10),  this.convertPairToDate(Integer.parseInt(data.get(16)), data.get(4)), data.get(5), Integer.parseInt(data.get(7)));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.error("InputFileReader", "Absence File " + path + " was not found");
        }catch ( Exception e1) {
            logger.error("InputFileReader", e1.getMessage());
        }
    }


    /**
     *
     * @param path Filename where the data for the Tests table is
     */
    void readInsertTests(String path) {
        //read lines and insert into AbsenceSummary
        try {

            Scanner sc = new Scanner(new FileReader(path));
            sc.nextLine();
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                List<String> data = Arrays.asList(line.split("\\t"));
                db.insertTest(data.get(4), data.get(0), data.get(1), data.get(5), data.get(6), data.get(8));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.error("InputFileReader", "Tests File " + path + " was not found");
        }catch ( Exception e1) {
            logger.error("InputFileReader", e1.getMessage());
        }
    }

}
