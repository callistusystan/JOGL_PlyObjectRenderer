package options_views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.MaterialStore;
import entities.OptionsManager;

/***
 * A panel that allows users to specify rendering type
 * 
 * @author Callistus
 */
@SuppressWarnings("serial")
public class RenderingPanel extends JPanel {
	
    public RenderingPanel() {
        super(new GridLayout(1, 2));

        JLabel label = new JLabel("Rendering", SwingConstants.CENTER);
        
        String[] choices = { MaterialStore.WIREFRAME, MaterialStore.GOLD, MaterialStore.COPPER, MaterialStore.WHITE_PLASTIC };
        JComboBox<String> list = new JComboBox<>(choices);
        list.setSelectedIndex(0);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	OptionsManager.getInstance().setRendering((String) list.getSelectedItem());
            }
        });
        add(label);
        add(list);
    }
    
}
