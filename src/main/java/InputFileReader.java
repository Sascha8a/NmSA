import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 04.04.2017
 * file: InputFileReader
 * Class: 3CHIF
 */

class InputFileReader{

    private Database db;
    private LoggerSingleton logger;
    private ExecutorService pool;

    InputFileReader(Database db) {
        this.pool = Executors.newCachedThreadPool();
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

    private boolean checkAbsenceHeader(String header){
        List<String> data = Arrays.asList(header.split("\\t"));
        return Objects.equals(data.get(0), "Schüler") &&
                (Objects.equals(data.get(10), "Abwesenheitsgrund")) &&
                (Objects.equals(data.get(16), "Stundennr.")) &&
                (Objects.equals(data.get(4), "Datum")) &&
                (Objects.equals(data.get(5), "Wochentag")) &&
                (Objects.equals(data.get(7), "Fehlmin."));
    }

    private boolean checkTestsHeader(String header){
        List<String> data = Arrays.asList(header.split("\\t"));
        return Objects.equals(data.get(4), "Datum") &&
                (Objects.equals(data.get(0), "Art")) &&
                (Objects.equals(data.get(1), "Name")) &&
                (Objects.equals(data.get(5), "Von")) &&
                (Objects.equals(data.get(6), "Bis")) &&
                (Objects.equals(data.get(8), "Fach"));
    }

    /**
     *
     * @param path Filename where the data for the Absence table is
     */
    void readInsertAbsence(String path) {
        //read lines and insert into AbsenceSummary
        try {
            Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(path), "UTF8"));
            String header = sc.nextLine();
            if(checkAbsenceHeader(header)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    List<String> data = Arrays.asList(line.split("\\t"));
                    List<String> name = Arrays.asList(data.get(0).split(" "));
                    pool.execute(new AbsenceInputThread(db, name.get(1), name.get(0), data.get(10), this.convertPairToDate(Integer.parseInt(data.get(16)), data.get(4)), data.get(5), Integer.parseInt(data.get(7))));
                }

                pool.shutdown();
                try {
                    pool.awaitTermination(60000, TimeUnit.SECONDS);
                } catch (InterruptedException e) {

                }
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
            String header = sc.nextLine();
            if(checkTestsHeader(header)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    List<String> data = Arrays.asList(line.split("\\t"));
                    pool.execute(new TestInputThread(db, data.get(4), data.get(0), data.get(1), data.get(5), data.get(6), data.get(8)));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.error("InputFileReader", "Tests File " + path + " was not found");
        }catch ( Exception e1) {
            logger.error("InputFileReader", e1.getMessage());
        }
    }

}
