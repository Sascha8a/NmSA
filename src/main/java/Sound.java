import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Created by UltraKnecht on 25.05.2017.
 */
public class Sound {


    synchronized void playSound(String audio) {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("audio/" + audio));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Starts the clip
        clip.start();

        try{
            if(audio == "start.wav") {
                Thread.sleep(6000);
            }else{
                Thread.sleep(3000);
            }
        }catch(Exception e){System.out.println(e);}
    }

}

