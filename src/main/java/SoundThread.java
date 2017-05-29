/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 16.05.2017
 * file: SoundThread
 * Class: 3CHIF
 */

public class SoundThread implements Runnable {

    private String threadName;
    private String audioName;
    private Sound sound;

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
