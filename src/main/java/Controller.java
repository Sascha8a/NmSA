import java.util.ArrayList;
import java.util.HashMap;

import static spark.Spark.stop;

/**
 * Created by UltraKnecht on 02.05.2017.
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

    public void shutdown() {
        SoundThread thread = new SoundThread("SoundThread", new Sound(), "end.wav");
        thread.start();
        stop();
    }

    public void updateAbsence(String path) {
        this.model.updateAbsence(path);
    }

    public void updateTests(String path) {
        this.model.updateTests(path);
    }

    public ArrayList<AbsenceSummary> getAbsenceSummaries() {
        return this.model.getAbsenceSummaries();
    }

    public ArrayList<AbsenceDetail> getAbsenceDetails() {
        return this.model.getAbsenceDetails();
    }

    public ArrayList<AbsenceDetail> getRanking() {
        return this.model.getRanking();
    }

    public int[] getAbsencePerDay(String name) {
        return  this.model.getAbsencePerDay(name);
    }
}
