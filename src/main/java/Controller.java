import java.util.ArrayList;
import java.util.HashMap;

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
        this.view.showView();

        LoggerSingleton logger = LoggerSingleton.getInstance();

        logger.info("Controller", "Init finished.");
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
