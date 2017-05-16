import java.util.ArrayList;

/**
 * Created by UltraKnecht on 02.05.2017.
 */
public class Controller {

    private View view = new View();
    private Model model = new Model();

    public Controller() {
        this.view.startWS();
        this.view.showView();

        LoggerSingleton logger = LoggerSingleton.getInstance();

        logger.info("Controller", "Init finished.");
    }

    public ArrayList<AbsenceSummary> getAbsenceSummaries() {
        return this.model.getAbsenceSummaries();
    }

    public ArrayList<AbsenceDetail> getAbsenceDetails() {
        return this.model.getAbsenceDetails();
    }
}
