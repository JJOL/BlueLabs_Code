package tec.gamelabs.GameTut;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	int x, y;
	int spd = 0;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 64);
	}
	
	public void update() {
		x += spd;
	}
	
	public void setSpd(int sdp) {
		this.spd = sdp;
	}
}
