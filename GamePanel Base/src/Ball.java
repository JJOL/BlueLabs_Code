import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	private int x,y,size;
	private Color color;
	
	public Ball(int width, int height) {
		Random r=new Random();
		x=r.nextInt(width);
		y=r.nextInt(height);
		size=r.nextInt(20) + 15;
		color=new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, size, size);
	}
	
	public void move(int heigth) {
		if (y>=heigth) y=0;
		else y=y+1;

		
	}
	
	public boolean click(int mx,int my) {
		
		if (mx>x && my>y && mx<x+size && my<y+size) return true;
		else return false;
		
	}
	
}
