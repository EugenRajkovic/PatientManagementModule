/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Eugen
 */
public class Validators {

    public static boolean validateField(JTextField f, String errormsg) {
        if (f.getText().trim().length() == 0) {
            return failedMessage(f, errormsg);
        } else {
            return true; 
        }
    }
    
    public static boolean validateField(JTextField textField) {
       return textField.getText().trim().length() != 0;
    }
    
    
    public static boolean validateTextArea(JTextArea textArea)
    {
        return textArea.getText().length() != 0;
    }
    
    public static boolean validateTextArea(JTextArea f, String errormsg) {
        if (f.getText().length() == 0) {
            return failedMessage(f, errormsg);
        } else {
            return true; 
        }
    }
    
    public static boolean validateComboBox(JComboBox f, String errormsg) {
        if (f.getSelectedItem() == null) {
            return failedMessage(f, errormsg);
        } else {
            return true; 
        }
    }
    
    public static boolean validateComboBox(JComboBox comboBox) {
        return comboBox.getSelectedItem() != null;
    }
    
    public static boolean validateJXDate(JXDatePicker f, String errormsg) {
        if (f.getDate() == null) {
            return failedMessage(f, errormsg);
        } else {
            return true; 
        }
    }
    
    public static boolean validateJXDate(JXDatePicker datePicker) {
        return datePicker.getDate() != null;
    }

    public static boolean validateInteger(JTextField f, String errormsg) {
        try {  
            int i = Integer.parseInt(f.getText());
            
            if (i > 0) {
                return true; 
            }
        } catch (NumberFormatException e) {
            
        }
        return failedMessage(f, errormsg);
    }

    public static boolean failedMessage(JComponent f, String errormsg) {
        JOptionPane.showMessageDialog(null, errormsg); 
        f.requestFocus(); 
        return false; 
    }
}
