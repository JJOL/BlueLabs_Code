package tec.gamelabs.GameTut;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class VolumeController {

	public void draw(Graphics2D g) {
		
		
		g.drawString("Music Volume: " + AudioPlayer.get().getMusicVolume(), 20, 20);
	}
	
	public void act(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			AudioPlayer.get().setMusicVolume(AudioPlayer.get().getMusicVolume()+0.1f);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			AudioPlayer.get().setMusicVolume(AudioPlayer.get().getMusicVolume()-0.1f);
		}
	}
	
}
