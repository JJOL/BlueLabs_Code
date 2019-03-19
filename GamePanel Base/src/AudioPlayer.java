

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioPlayer {

	private static AudioPlayer instance;
	
	private Map<String, Clip> audios;
	
	public static AudioPlayer get() {
		if (instance == null) {
			instance = new AudioPlayer();
		}
		
		return instance;
	}
	
	private AudioPlayer() {
		audios = new HashMap<>();
	}
	
	public void playSound(String audio) {
		Clip soundClip = getSoundClip(audio);
		soundClip.setFramePosition(0);
		soundClip.loop(Clip.LOOP_CONTINUOUSLY);
		//soundClip.start();
	}
	
	private Clip getSoundClip(String name) {
		if (audios.containsKey(name)) {
			return audios.get(name);
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		AudioInputStream sample = null;
		try {
			//sample = AudioSystem.getAudioInputStream(new File(d));
			sample = AudioSystem.getAudioInputStream(getClass().getResource(name+".wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(sample);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		audios.put(name, clip);
		
		return clip;
	}
}
