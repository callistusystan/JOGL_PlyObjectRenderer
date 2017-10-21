import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import options_views.OptionsView;
import render_views.RenderView;

/***
 * The main driver for the application
 * 
 * @author Callistus
 */
public class Main {

	public static void main(String[] args) {
	      // Run the GUI codes in the event-dispatching thread for thread safety
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            JFrame renderView = new RenderView();
	            JFrame optionsView = new OptionsView();
	            
	            optionsView.setLocationRelativeTo(renderView);
	            optionsView.setLocation(renderView.getX(), renderView.getY() + renderView.getHeight());
	         }
	      });
	   }

}
