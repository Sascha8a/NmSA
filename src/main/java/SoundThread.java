/**
 * Created by UltraKnecht on 25.05.2017.
 */
public class SoundThread extends Thread {

    private Thread t;
    private String threadName;
    Sound sound;

    SoundThread( String name,  Sound sound) {
        this.threadName = name;
        this.sound = sound;
    }


    @Override
    public void run() {
        synchronized(this.sound) {
            this.sound.playSound();
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }


    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
