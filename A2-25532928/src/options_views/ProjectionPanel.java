package options_views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.TypeStore;
import entities.OptionsManager;

/***
 * A panel that allows users to specify projection type
 * 
 * @author Callistus
 */
@SuppressWarnings("serial")
public class ProjectionPanel extends JPanel {
	
    public ProjectionPanel() {
        super(new GridLayout(1, 2));

        JLabel label = new JLabel("Projection", SwingConstants.CENTER);
        
        String[] choices = { TypeStore.PERSPECTIVE, TypeStore.ORTHOGRAPHIC };
        JComboBox<String> list = new JComboBox<>(choices);
        list.setSelectedIndex(0);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	OptionsManager.getInstance().setProjection((String) list.getSelectedItem());
            }
        });
        add(label);
        add(list);
    }
    
}
