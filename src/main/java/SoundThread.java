/**
 * Created by UltraKnecht on 25.05.2017.
 */
public class SoundThread extends Thread {

    private Thread t;
    private String threadName;
    private String audioName;
    Sound sound;

    SoundThread( String name,  Sound sound, String audioName) {
        this.threadName = name;
        this.sound = sound;
        this.audioName = audioName;
    }


    @Override
    public void run() {
        this.sound.playSound(audioName);
        System.out.println("Thread " +  threadName + " exiting.");
    }

}
