import Logging.LoggerSingleton;

import java.util.Observable;
import java.util.Observer;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 20.05.2017
 * file: APIObserver
 * Class: 3CHIF
 */

public class APIObserver implements Observer {
    private LoggerSingleton logger;

    APIObserver() {
        this.logger = LoggerSingleton.getInstance();
    }

    public void update(Observable obs, Object o) {
        this.logger.debug("APIObserver", o.toString());
    }
}
