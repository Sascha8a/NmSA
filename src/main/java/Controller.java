
/**
 * Created by UltraKnecht on 02.05.2017.
 */
public class Controller {

    View view;
    private Model model;

    public Controller() {

        this.view = new View();
        this.model = new Model();
        this.view.startWS();
        this.view.showView();

        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.setView(this.view);

        logger.error("Debug test guy", "DEASDASDADAS");
    }
}
