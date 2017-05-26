
public class NmSA {
    public static void main(String[] args) {
        new Thread(new SoundThread("SoundThread", new Sound(), "start.wav")).start();
        Controller c = new Controller();
        APIFacade apiFacade = new APIFacade(c);
        apiFacade.addObserver(new APIObserver());
    }
}
