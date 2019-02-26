import java.awt.BorderLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		 GamePanel game=new GamePanel();
	 	JFrame app=new JFrame("Game Animation");
	 	app.setSize(600, 600);
	
		app.getContentPane().add(game,BorderLayout.CENTER);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		app.pack();
		app.setResizable(false);
		app.setVisible(true);	
		 ExecutorService pool = Executors.newFixedThreadPool(2);   
		
		     
	          
	          
	        // creates a thread pool with MAX_T no. of  
	        // threads as the fixed pool size(Step 2) 
	        
	         
	        // passes the Task objects to the pool to execute (Step 3) 
	        pool.execute(game);
	        game.addNotify();
	        
	        pool.shutdown();     
		
		

	}

}
