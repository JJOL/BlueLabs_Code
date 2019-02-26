import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FakeScrollLevel extends LevelController{

	
	private BufferedImage img;
	int scrollSpd;
	int b1_xOff;
	int b2_xOff;
	
	private Player player;
	
	
	public FakeScrollLevel(int PWIDTH, int PHEIGHT) {
		super(PWIDTH, PHEIGHT);
		
		player = new Player(100, 100);
		
		try {
			img = (BufferedImage)ImageIO.read(getClass().getClassLoader().getResource("./sback.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scrollSpd = 2;
		b1_xOff = 0;
		b2_xOff = b1_xOff + img.getWidth();
		
	}
	
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		super.render(g);
		
		g.drawImage(img, b1_xOff, 0, null);
		g.drawImage(img, b2_xOff, 0, null);
		
		
		player.draw(g);
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		b1_xOff -= scrollSpd;
		b2_xOff -= scrollSpd;
		
		if (b1_xOff <= (-img.getWidth())) {
			b1_xOff = img.getWidth();
		}
		if (b2_xOff <= (-img.getWidth())) {
			b2_xOff = img.getWidth();
		}
		
		player.update();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setSpd(-5);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setSpd(5);
		}
		
	}

	
}
