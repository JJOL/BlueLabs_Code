import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

public class ManyBalls {
	LinkedList <Ball>many;
	
	public ManyBalls() {
		many=new LinkedList <Ball>();
		for (int i=0;i<3;i++)
			many.add(new Ball(500,400));
		}
   public void paint(Graphics g) {
	   for (Ball b:many)
	      b.paint(g);
   }


   public void move(int height) {
	   for (Ball b:many)
	      b.move(height);
   }
   
   public void click(int mx,int my) {

	   ListIterator <Ball> itr=many.listIterator();
	   if (itr.hasNext()) {
	   
		   while (itr.hasNext()) {
		     Ball b=itr.next();
		     if (b.click(mx,my)) itr.remove();
	       }
	   }
	   
   }
   public boolean empty() {
	   return many.isEmpty();
   }

   
}




