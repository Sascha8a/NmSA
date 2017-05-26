
public class NmSA {
    public static void main(String[] args) {
        SoundThread thread = new SoundThread("SoundThread", new Sound(), "start.wav");
        thread.start();
        Controller c = new Controller();
        APIFacade apiFacade = new APIFacade(c);
        apiFacade.addObserver(new APIObserver());
    }
}
