package render_views;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

/***
 * A JFrame that wraps the render canvas
 * 
 * Boilerplate code mostly copied from examples on moodle
 * 
 * @author Callistus
 */
@SuppressWarnings("serial")
public class RenderView extends JFrame {
	
	   private static String TITLE = "PlyObject Renderer";
	   private static final int CANVAS_WIDTH = 640;
	   private static final int CANVAS_HEIGHT = 480;
	   private static final int FPS = 60;
	 
	   public RenderView() {
		   // Create the OpenGL rendering canvas
		   GLCanvas canvas = new RenderViewCanvas();
		   canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	      
		   // Create a animator that drives canvas' display() at the specified FPS.
		   final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
	      
		   // Create the top-level container frame
		   this.getContentPane().add(canvas);
	      
		   this.addWindowListener(new WindowAdapter() {
			   @Override
			   public void windowClosing(WindowEvent e) {
				   // Use a dedicate thread to run the stop() to ensure that the
				   // animator stops before program exits.
				   new Thread() {
					   @Override
					   public void run() {
						   if (animator.isStarted()) animator.stop();
						   System.exit(0);
						   }
					   }.start();
					   }
			   }
		   );
		   
		   // set window properties
		   this.setTitle(TITLE);
		   this.pack();
		   this.setLocationRelativeTo(null); 
		   this.setVisible(true);
		   animator.start(); // start the animation loop
	   }

}
