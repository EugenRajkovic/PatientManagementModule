/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.awt.Component;
import java.awt.Point;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Eugen
 */
public class Utils {

    //Clears panel components of all content. TextArea had to be multiply wraped because it is inside JScrollPane.
    //Radio buttons should be grouped together and clearSelection() should be called upon that group.
    public static void ClearForm(JPanel panel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(Boolean.FALSE);
            } else if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedItem(null);
            } else if (c instanceof JXDatePicker) {
                ((JXDatePicker) c).setDate(null);
            } else if (c instanceof JScrollPane) {
                ((JTextArea) ((JViewport) ((JScrollPane) c).getViewport()).getView()).setText("");
            } else if (c instanceof JRadioButton) {
                ((DefaultButtonModel) ((JRadioButton) c).getModel()).getGroup().clearSelection();
            } else if (c instanceof JPanel) {
                ClearForm((JPanel)c);
            }
        }
    }

    //Validates all the components in the passed panel and returns string that will be used for errorMessage. 
    public static String ValidateComponents(JPanel panel) {
        
        Vector<Component> components = new Vector<>();
        components.addAll(Arrays.asList(panel.getComponents()));

        Utils.SortComponents(components);
        
        for (Component c : components) {
            if (c instanceof JTextField) {
                if (((JTextField) c).getText().trim().length() == 0) {
                    return c.getAccessibleContext().getAccessibleName();
                }
            }      
            if (c instanceof JComboBox) {
                if (((JComboBox) c).getSelectedItem() == null) {
                    return c.getAccessibleContext().getAccessibleName();
                }
            }
            if (c instanceof JXDatePicker) {
                if (((JXDatePicker) c).getDate() == null) {
                   return "date";
                }
            }
            if (c instanceof JRadioButton) {
                if (((DefaultButtonModel) ((JRadioButton) c).getModel()).getGroup().getSelection() == null) {
                    return c.getAccessibleContext().getAccessibleName();
                }
            }
            if (c instanceof JScrollPane) {
                if (((JTextArea) ((JViewport) ((JScrollPane) c).getViewport()).getView()).getText().trim().length() == 0) {
                   return c.getAccessibleContext().getAccessibleName();
                }
            }
            if (c instanceof JPanel) {
                String validation = ValidateComponents((JPanel) c);
                if(validation != null)
                    return validation;
            }
        }
        return null;
    }
    

    public static void SortComponents(Vector<Component> components) {
        components.sort((o1, o2)
                -> {
            Point position1 = o1.getLocation();
            Point position2 = o2.getLocation();

            //Jel position1 ljeviji od position2
            if (position1.x < position2.x) {
                return -1;//position1 ima prednost
            }
            //Jel position2 ljeviji od position1
            if (position2.x < position1.x) {
                return 1;//position2 ima prednost
            }
            //Jel position1 iznad position2
            if (position1.y < position2.y) {
                return -1;
            }

            //Jel position2 iznad position1
            if (position2.y < position1.y) {
                return 1;
            }

            return 0;//Imaju jednaku vrijednost
        });
    }
}
