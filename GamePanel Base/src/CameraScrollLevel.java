import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CameraScrollLevel extends LevelController {

	
	private BufferedImage img;
	int scrollSpd;
	int camX;
	
	Player player;
	
	public CameraScrollLevel(int PWIDTH, int PHEIGHT) {
		super(PWIDTH, PHEIGHT);
		try {
			img = (BufferedImage)ImageIO.read(getClass().getClassLoader().getResource("./sback.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player = new Player(100, 100);
		
		
		camX = 0;
		scrollSpd = 0;
	}
	
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		g.translate(-camX, 0);
		
		for (int i = 0; i < 3; i++) {
			g.drawImage(img, i*img.getWidth(), 0, null);
		}
		
		player.draw(g);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		camX += scrollSpd;
		player.update();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setSpd(-5);
			scrollSpd = -5;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setSpd(5);
			scrollSpd = 5;
		}
		
	}

}
