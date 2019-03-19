package tec.gamelabs.GameTut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayerGen {

	private static AudioPlayerGen instance;
	
	private Map<String, Clip> audios;
	private Map<String, Integer> audioToChannel;
	private Map<Integer, ArrayList<String>> channelToAudios;
	private Map<Integer, Float> channelToVolume;
	
	public static AudioPlayerGen get() {
		if (instance == null) {
			instance = new AudioPlayerGen();
		}
		return instance;
	}
	
	private AudioPlayerGen() {
		audios = new HashMap<>();
		audioToChannel = new HashMap<String, Integer>();
		channelToAudios = new HashMap<Integer, ArrayList<String>>();
		channelToVolume = new HashMap<Integer, Float>();
	}
	
	
	private void prepareChannel(int channel) {
		if (!channelToAudios.containsKey(channel)) {
			channelToAudios.put(channel, new ArrayList<String>());
		}
		
		if (!channelToVolume.containsKey(channel)) {
			channelToVolume.put(channel, 1f);
		}
	}
	
	public void playSound(int channel, String audio) {
		Clip soundClip = getSoundClip(audio);
		soundClip.setFramePosition(0);
		
		prepareChannel(channel);
		
		if (channel == 0) {
			if (channelToAudios.get(channel).size() > 0) {
				String lastAudio = channelToAudios.get(channel).get(0);
				Clip prevClip = audios.get(lastAudio);
				prevClip.stop();
				audioToChannel.put(lastAudio, -1);
				
				channelToAudios.get(channel).clear();
				channelToAudios.get(channel).add(audio);
			}
			
			soundClip.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			soundClip.start();
			
		}
		
		
		audioToChannel.put(audio, channel);
		
		setVolume(soundClip, channelToVolume.get(channel));
		
	
		
	}
	
	public void setVolume(int channel, float volume) {
		
		prepareChannel(channel);
		
		channelToVolume.put(channel, volume);
		
		// Set the volume to every clip being played in the channel
		ArrayList<String> audiosForChannel =  channelToAudios.get(channel);
		for (int i = 0; i < audiosForChannel.size(); i++) {
			Clip soundClip = audios.get(audiosForChannel.get(i));
			setVolume(soundClip, volume);
		}
	}
	
	public void setVolume(Clip soundClip, float volume) {
		FloatControl gainControl = (FloatControl)soundClip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = range*volume + gainControl.getMinimum();
		gainControl.setValue(gain);
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
			sample = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("./"+name+".wav"));
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
		
		// Add Listener for removing audio from a channel when it is no longer playing
		clip.addLineListener(new LineListener() {
			
			@Override
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.STOP) {
					
					// Get Channel Where Audio Clip was Playing
					int playingChannel = audioToChannel.get(name);
					
					// Set Playing Channel for now Stopped Audio to -1
					audioToChannel.put(name, -1);
					
					// Find Audio in the Channel List and remove it
					ArrayList<String> audiosForChannel = channelToAudios.get(playingChannel);
					for (int i=0; i < audiosForChannel.size(); i++) {
						if (audiosForChannel.get(i).equalsIgnoreCase(name)) {
							audiosForChannel.remove(i);
							break;
						}
					}
					
					
				}
				
			}
		});
		
		return clip;
	}

	
}
