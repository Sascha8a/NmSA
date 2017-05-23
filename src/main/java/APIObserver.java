import java.util.Observable;
import java.util.Observer;

public class APIObserver implements Observer {
    private LoggerSingleton logger;

    public APIObserver() {
        this.logger = LoggerSingleton.getInstance();
    }

    public void update(Observable obs, Object o) {
        this.logger.debug("APIObserver", o.toString());
    }
}
