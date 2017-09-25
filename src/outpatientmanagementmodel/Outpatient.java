package outpatientmanagementmodel;

import BLL.NursesManager;
import DAL.*;
import UI.*;
import ViewModel.Doctor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Eugen
 */
public class Outpatient {

    public enum FormatEnum {
        DEFAULT,
        APPOINTMENT_FORMAT,
        SIMPLE_PATIENT_FORMAT
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
           
        }

        IView view = ViewFactory.CurrentView(2);

        view.Start();

    }

}
