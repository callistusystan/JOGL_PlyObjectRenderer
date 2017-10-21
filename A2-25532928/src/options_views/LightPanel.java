package options_views;

import javax.swing.*;

import entities.OptionsManager;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/***
 * A panel that allows users to toggle lights
 * 
 * @author Callistus
 */
@SuppressWarnings("serial")
public class LightPanel extends JPanel {

    public LightPanel() {
        super(new GridLayout(1, 2));

        JLabel label = new JLabel("Lights on?", SwingConstants.CENTER);
        JCheckBox checkbox = new JCheckBox();
        checkbox.setSelected(true);
        
        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    OptionsManager.getInstance().setIsLightOn(true);
                } else {
                    OptionsManager.getInstance().setIsLightOn(false);
                }
            }
        });
        add(label);
        add(checkbox);
    }
}
