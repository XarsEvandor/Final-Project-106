//Imports sound related resources
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//The purpose of this class is to import all sounds for the game and enable their activation if needed.
public class Sounds {

    //This method is repeated for all sound effects. First a file object is created and correlated to the path of the sound file. This allows for the AudioImputStream
    //to recognise the sound files. Afterwards a clip object is created to allow more control over the sound bit. The sound is imported to the clip and then "played".
    //Trapping code is implemented for the cases of the system being unable to find the file or the path.
    public void correctEffect() {
        try {
            File CorrectSoundPath = new File("CorrectSound.wav");
            if (CorrectSoundPath.exists()) {
                AudioInputStream correctSound = AudioSystem.getAudioInputStream(CorrectSoundPath);
                Clip correctClip = AudioSystem.getClip();
                correctClip.open(correctSound);
                correctClip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void incorrectEffect() {
        try {
            File IncorrectSoundPath = new File("IncorrectSound.wav");
            if (IncorrectSoundPath.exists()) {
                AudioInputStream IncorrectSound = AudioSystem.getAudioInputStream(IncorrectSoundPath);
                Clip IncorrectClip = AudioSystem.getClip();
                IncorrectClip.open(IncorrectSound);
                IncorrectClip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void MainSongEffect() {
        try {
            File MainSongSoundPath = new File("MainSongSound.wav");
            if (MainSongSoundPath.exists()) {
                AudioInputStream MainSongSound = AudioSystem.getAudioInputStream(MainSongSoundPath);
                Clip MainSongClip = AudioSystem.getClip();
                MainSongClip.open(MainSongSound);
                MainSongClip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void ExtraLifeEffect() {
        try {
            File ExtraLifeSoundPath = new File("ExtraLifeSound.wav");
            if (ExtraLifeSoundPath.exists()) {
                AudioInputStream ExtraLifeSound = AudioSystem.getAudioInputStream(ExtraLifeSoundPath);
                Clip ExtraLifeClip = AudioSystem.getClip();
                ExtraLifeClip.open(ExtraLifeSound);
                ExtraLifeClip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void VictoryEffect() {
        try {
            File VictorySoundPath = new File("VictorySound.wav");
            if (VictorySoundPath.exists()) {
                AudioInputStream VictorySound = AudioSystem.getAudioInputStream(VictorySoundPath);
                Clip VictoryClip = AudioSystem.getClip();
                VictoryClip.open(VictorySound);
                VictoryClip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void DefeatEffect() {
        try
        {
            File DefeatSoundPath = new File("DefeatSound.wav");
            if (DefeatSoundPath.exists()) {
                AudioInputStream DefeatSound = AudioSystem.getAudioInputStream(DefeatSoundPath);
                Clip DefeatClip = AudioSystem.getClip();
                DefeatClip.open(DefeatSound);
                DefeatClip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }
}