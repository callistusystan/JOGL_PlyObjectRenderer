package options_views;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entities.OptionsManager;

/***
 * A panel that allows users to specify translation in all axes
 * 
 * @author Callistus
 */
@SuppressWarnings("serial")
public class TranslationPanel extends JPanel {

    public TranslationPanel() {
        super(new GridLayout(3, 1));

        add(createTranslatePanel("X"));
        add(createTranslatePanel("Y"));
        add(createTranslatePanel("Z"));
    }
    
    /***
     * A helper method that creates a translation panel based on the specified axis
     * 
     * @param axis
     * @return a JPanel for the corresponding axis
     */
    public JPanel createTranslatePanel(String axis) {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JLabel panelLabel = new JLabel("Translation on " + axis + " axis", SwingConstants.CENTER);
        
        JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	float value = slider.getValue() - 50f;
            	if (axis.equals("X")) {
                    OptionsManager.getInstance().setTranslateX(value);
            	} else if (axis.equals("Y")) {
                    OptionsManager.getInstance().setTranslateY(value);
            	} else if (axis.equals("Z")) {
                    OptionsManager.getInstance().setTranslateZ(value);
            	}
            }
        });
        
        panel.add(panelLabel);
        panel.add(slider);
    	
        return panel;
    }
	
}
