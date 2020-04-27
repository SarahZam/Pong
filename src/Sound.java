import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.Clip;

import sun.audio.*;

public class Sound {
	
	//For ball sounds when it hits something
    public void popSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("ballsound.wav"));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception e)  {
            System.out.println(e);
        } 
    }
    
    //when paddle catches Fs or Ds OR HITS THE FLOOR
    public void shockSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("electricshock.wav"));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start(); 
        }
        catch(Exception e)  {
            System.out.println(e);
        } 
    }
    
    //When ball catches A,B,C or lives 
    public void pointSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("pointsound.wav"));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start(); 
        }
        catch(Exception e)  {
            System.out.println(e);
        } 
    }
   
        
}
