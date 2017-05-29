/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 04.04.2017
 * file: NmSA
 * Class: 3CHIF
 */

public class NmSA {
    public static void main(String[] args) {
        new Thread(new SoundThread("SoundThread", new Sound(), "start.wav")).start();
        Controller c = new Controller();
        APIFacade apiFacade = new APIFacade(c);
        apiFacade.addObserver(new APIObserver());
    }
}
