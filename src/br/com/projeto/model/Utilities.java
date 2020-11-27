package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Marcelo Saorim
 */
public class Utilities {
    
    /**
     *
     * @param container
     */
    public void limpaTela(JPanel container){
        Component components[] = container.getComponents();
        for(Component component: components){
            if(component instanceof JTextField){
                ((JTextComponent) component).setText(null);
            }
            if(component instanceof JComboBox){
                ((JComboBox) component).setSelectedItem("SP");
            }
        }
    }
}
