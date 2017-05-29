import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 25.05.2017
 * file: Sound
 * Class: 3CHIF
 */

public class Sound {


    synchronized void playSound(String audio) {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("audio/" + audio));
        } catch (UnsupportedAudioFileException | IOException e) {
            LoggerSingleton.getInstance().error("Sound", e.getMessage());
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            LoggerSingleton.getInstance().error("Sound", e.getMessage());
        }
        try {
            if (clip != null) {
                clip.open(audioInputStream);
            }
        } catch (LineUnavailableException | IOException e) {
            LoggerSingleton.getInstance().error("Sound", e.getMessage());
        }

        //Starts the clip
        if (clip != null) {
            clip.start();
        }

        try{
            if(Objects.equals(audio, "start.wav")) {
                Thread.sleep(6000);
            }else{
                Thread.sleep(3000);
            }
        }catch(Exception e){LoggerSingleton.getInstance().error("Sound", e.getMessage());}
    }

}

