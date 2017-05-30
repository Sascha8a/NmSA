import Logging.LoggerSingleton;

import java.util.ArrayList;
import static spark.Spark.stop;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 02.05.2017
 * file: Controller
 * Class: 3CHIF
 */

public class Controller {

    private View view;
    private Model model;

    public Controller() {
        this.view = new View();
        this.model = new Model();

        this.view.startWS();

        LoggerSingleton logger = LoggerSingleton.getInstance();

        logger.info("Controller", "Init finished.");
    }

    /**
     * Terminates the program
     */
    public void shutdown() {
        new Thread(new SoundThread("SoundThread", new Sound(), "end.wav")).start();
        stop();
        System.exit(0);
    }

    /**
     * Updates Absence data
     * @param path  path to file
     */
    void updateAbsence(String path) {
        this.model.updateAbsence(path);
    }

    /**
     * Updates Test data
     * @param path  path to file
     */
    void updateTests(String path) {
        this.model.updateTests(path);
    }

    /**
     * Gets Absence details
     */
    ArrayList<AbsenceDetail> getAbsenceDetails() {
        return this.model.getAbsenceDetails();
    }

    /**
     * Gets Ranking
     */
    ArrayList<AbsenceDetail> getRanking() {
        return this.model.getRanking();
    }

    /**
     * Gets Absence per day for a specific student
     * @param name name of student
     */
    int[] getAbsencePerDay(String name) {
        return  this.model.getAbsencePerDay(name);
    }

    /**
     * Gets amount of tests present for a specific student
     * @param name name of student
     */
    public int getAmountTestPresent(String name) {
        return  this.model.getAmountTestPresent(name);
    }

    /**
     * Gets total amount of tests
     */
    public int getTestAmount() {
        return  this.model.getTestAmount();
    }

    /**
     * Gets monthly average of absence
     */
    public int[] getMonthAverage() {
        return this.model.getMonthAverage();
    }
}
