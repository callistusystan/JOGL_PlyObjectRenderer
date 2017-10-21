package options_views;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/***
 * A JFrame that wraps each of the options panel
 * 
 * @author Callistus
 */
@SuppressWarnings("serial")
public class OptionsView extends JFrame {

    public OptionsView() {
    	this.setTitle("Options");
        
    	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    	this.getContentPane().add(new RenderingPanel());
    	this.getContentPane().add(new ProjectionPanel());
    	this.getContentPane().add(new TranslationPanel());
    	this.getContentPane().add(new LightPanel());

    	this.pack();
    	this.setVisible(true);
    }

}
