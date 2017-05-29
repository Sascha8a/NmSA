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

    public void shutdown() {
        new Thread(new SoundThread("SoundThread", new Sound(), "end.wav")).start();
        stop();
    }

    void updateAbsence(String path) {
        this.model.updateAbsence(path);
    }

    void updateTests(String path) {
        this.model.updateTests(path);
    }

    ArrayList<AbsenceDetail> getAbsenceDetails() {
        return this.model.getAbsenceDetails();
    }

    ArrayList<AbsenceDetail> getRanking() {
        return this.model.getRanking();
    }

    int[] getAbsencePerDay(String name) {
        return  this.model.getAbsencePerDay(name);
    }
}
