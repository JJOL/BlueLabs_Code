import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable{
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int PWIDTH=500;
    private static final int PHEIGTH=400;  //size of panel
    
    private Thread animator;
    private volatile boolean running=false;
    private volatile boolean gameOver=false;
    
    private Graphics dbg;
    private Image dbImage=null;
    
    private LevelController level;
    //Ball myBall=new Ball(400,500);

    
    //ManyBalls many;  
    
    public GamePanel(){
        
        this.setBackground(Color.WHITE); //WHITE BACKGROUND
        this.setPreferredSize(new Dimension(PWIDTH,PHEIGTH));
        readyForTermination();
        setFocusable(true); //JPanle now receives keyEvents
        requestFocus();
        
        //level = new CameraScrollLevel(PWIDTH, PHEIGTH);
        level = new FakeScrollLevel(PWIDTH, PHEIGTH);
        
        //many=new ManyBalls();
    
         //listen for mouse presses
        addMouseListener(level);
        addKeyListener(level );
        /*addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent m) {
                click(m.getX(),m.getY());
            }
        
    
        });*/
    }
    public void addNotify() {
        super.addNotify();
        startGame();
    }
    
    public void startGame() {
        if (animator==null || !running) {
            animator=new Thread(this);
            animator.start();
            this.setVisible(true);
            
        }
    }
    
    public void stopGame() { //called by the user to stop execution
        running=false;
        
    }
    
    public void run() {
        /*repeated update render sleep*/
        running=true;
        
        while (running) {
        if (gameOver) running=false;
           gameUpdate(); //game state is updated
           gameRender();//render to the buffer
           paintScreen();
        
        
          try {
              
              Thread.sleep(20); //sleep a bit
          } catch (InterruptedException ex) {
          System.exit(0);
          }
        }
    
    }
    public void gameUpdate() {
        if (!gameOver) {
            
        	level.update();
            //many.move(PHEIGTH);
    //        many.click(mx, my);
            //myBall.move(400);
            
            
            
        }
    }
        
    public void gameRender() {
        
        if (dbImage==null ) {
            dbImage=createImage(PWIDTH,PHEIGTH);
            if (dbImage==null) {
                System.out.println("dbImage is null");
                return;
                
            }
        }
                
            dbg=dbImage.getGraphics();
            //clear background
            dbg.setColor(Color.WHITE);
            dbg.fillRect(0, 0, PWIDTH, PHEIGTH);
            
            
            //draw game elements
            level.render((Graphics2D)dbg);
            //many.paint(dbg);
            
            
            if  (gameOver) {
                gameOverMsg(dbg);
            }
        
    }
    
    public void gameOverMsg(Graphics dbg) {
        System.out.println("GAME OVER");
    }
    
    private void paintScreen() {
        Graphics g;
        try {
            g=this.getGraphics();
            if (g!=null && dbImage!=null)
                 g.drawImage(dbImage,0,0,null);
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
        catch(Exception e) {
            System.out.println("Graphic content error "+e);
        }
    }

     
    
    public void readyForTermination() {
        
    }    
    
    public void click(int mx,int my) {
        //many.click(mx, my);
        //gameOver=many.empty();
    }
}
