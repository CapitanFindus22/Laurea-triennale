package audio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * An audio player used for the pokemon's cry
 */
public class SoundPlayer {

	/**
	 * The audio stream
	 */
	private AudioInputStream audio;

	/**
	 * The audio clip
	 */
	private Clip clip;

	/**
	 * The file path
	 */
	private Path audio_path;

	/**
	 * Set the file to play
	 * 
	 * @param name the name of the pokemon
	 */
	public void setFile(String name) {

		audio_path = Paths.get("resource", "audio", name + ".wav");
		
		try {

			audio = AudioSystem.getAudioInputStream(audio_path.toFile());

			DataLine.Info info = new DataLine.Info(Clip.class, audio.getFormat());

			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audio);

		}

		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Play the set sound
	 */
	public void play() {
		
		clip.start();
		
		try {Thread.sleep((int)(clip.getMicrosecondLength()*0.001-100));} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		clip.stop();
		clip.close();
	}

	/**
	 * Check if the sound is set
	 * 
	 * @return true if it's set
	 */
	public boolean isSet() {
		return (audio != null) ? true : false;
	}

	/**
	 * Close everything
	 */
	public void close() {

		try {

			audio.close();
			clip.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
