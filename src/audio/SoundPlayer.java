package audio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	private AudioInputStream audio;
	private AudioFormat format;
	private Clip clip;
	
	private Path audio_path;
	
	public void setFile(String name) {
		
		audio_path = Paths.get("resource","audio",name+".wav");
		
		try {
		
			audio = AudioSystem.getAudioInputStream(audio_path.toFile());
			
			format = audio.getFormat();
       
			DataLine.Info info = new DataLine.Info(Clip.class, format);

			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audio);
			
		
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		
		clip.start();
		
	}
	
}
