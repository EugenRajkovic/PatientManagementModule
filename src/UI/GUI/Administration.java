/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import BLL.AdministrationManager;
import Helper.Utils;
import Helper.Validators;
import Model.City;
import ViewModel.Doctor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eugen
 */
public class Administration extends javax.swing.JFrame {

    private final AdministrationManager manager;

    private final DefaultTableModel model;

    private DefaultComboBoxModel comboBoxModel;

    /**
     * Creates new form Administration
     */
    public Administration() {
        super("Administration");
        initComponents();

        Toolkit tk = this.getToolkit();
        Dimension dim = tk.getScreenSize();
        setLocation((int) (dim.getWidth() - getWidth()) / 2, (int) (dim.getHeight() - getHeight()) / 2);

        manager = new AdministrationManager();
        model = (DefaultTableModel) jtblDoctors.getModel();

        ShowDoctors();
        LoadCitiesComboBox();
    }

    private void ShowDoctors() {
        try {
            List<Doctor> doctors = manager.GetAllDoctors();
            for (Doctor doctor : doctors) {
                model.addRow(new Object[]{doctor.getIDDoctor(), doctor.getName(), doctor.getSurname(), doctor.getTelephone(), doctor.getEmail(), doctor.isSpecialist(), doctor.getCityName()});
                jtblDoctors.setModel(model);
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No doctors to show.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlHeader = new javax.swing.JPanel();
        jlblIcon = new javax.swing.JLabel();
        jlblTitle = new javax.swing.JLabel();
        jpnlContainer = new javax.swing.JPanel();
        jscrpnlTable = new javax.swing.JScrollPane();
        jtblDoctors = new javax.swing.JTable();
        jlblName = new javax.swing.JLabel();
        jlblSurname = new javax.swing.JLabel();
        jlblTelephone = new javax.swing.JLabel();
        jlblEmail = new javax.swing.JLabel();
        jtxtName = new javax.swing.JTextField();
        jtxtSurname = new javax.swing.JTextField();
        jtxtTelephone = new javax.swing.JTextField();
        jtxtEmail = new javax.swing.JTextField();
        jchboxSpecialist = new javax.swing.JCheckBox();
        jlblSpecialist = new javax.swing.JLabel();
        jlblCity = new javax.swing.JLabel();
        jcmbBoxCity = new javax.swing.JComboBox<>();
        jbtnAdd = new javax.swing.JButton();
        jbtnDelete = new javax.swing.JButton();
        jbtnUpdate = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnlHeader.setBackground(new java.awt.Color(51, 153, 255));

        jlblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/admin.png"))); // NOI18N

        jlblTitle.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 48)); // NOI18N
        jlblTitle.setText("VIRGO HOSPITAL");

        javax.swing.GroupLayout jpnlHeaderLayout = new javax.swing.GroupLayout(jpnlHeader);
        jpnlHeader.setLayout(jpnlHeaderLayout);
        jpnlHeaderLayout.setHorizontalGroup(
            jpnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlHeaderLayout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(jlblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jpnlHeaderLayout.setVerticalGroup(
            jpnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpnlHeaderLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jlblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlContainer.setBackground(new java.awt.Color(255, 255, 255));

        jtblDoctors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDDoctor", "Name", "Surname", "Telephone", "Email", "Specialist", "CityName"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtblDoctors.setColumnSelectionAllowed(true);
        jtblDoctors.getTableHeader().setReorderingAllowed(false);
        jtblDoctors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblDoctorsMouseClicked(evt);
            }
        });
        jscrpnlTable.setViewportView(jtblDoctors);
        jtblDoctors.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jlblName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblName.setText("Enter name:");

        jlblSurname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSurname.setText("Enter surname:");

        jlblTelephone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblTelephone.setText("Enter telephone:");

        jlblEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblEmail.setText("Enter email:");

        jchboxSpecialist.setBackground(new java.awt.Color(255, 255, 255));

        jlblSpecialist.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSpecialist.setText("Specialist:");

        jlblCity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblCity.setText("City:");

        jcmbBoxCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zagreb", "Split", "Rijeka", "Osijek" }));

        jbtnAdd.setBackground(new java.awt.Color(102, 153, 255));
        jbtnAdd.setText("ADD DOCTOR");
        jbtnAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jbtnDelete.setBackground(new java.awt.Color(102, 153, 255));
        jbtnDelete.setText("DELETE DOCTOR");
        jbtnDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        jbtnUpdate.setBackground(new java.awt.Color(102, 153, 255));
        jbtnUpdate.setText("UPDATE DOCTOR");
        jbtnUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jbtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateActionPerformed(evt);
            }
        });

        jbtnClear.setBackground(new java.awt.Color(102, 153, 255));
        jbtnClear.setText("CLEAR");
        jbtnClear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlContainerLayout = new javax.swing.GroupLayout(jpnlContainer);
        jpnlContainer.setLayout(jpnlContainerLayout);
        jpnlContainerLayout.setHorizontalGroup(
            jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlContainerLayout.createSequentialGroup()
                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlContainerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jscrpnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlContainerLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblSurname)
                            .addComponent(jlblName)
                            .addComponent(jlblTelephone)
                            .addComponent(jlblEmail)
                            .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnlContainerLayout.createSequentialGroup()
                                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnlContainerLayout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(jlblCity)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcmbBoxCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpnlContainerLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jlblSpecialist)
                                        .addGap(18, 18, 18)
                                        .addComponent(jchboxSpecialist))))
                            .addGroup(jpnlContainerLayout.createSequentialGroup()
                                .addComponent(jbtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jbtnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnlContainerLayout.setVerticalGroup(
            jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlContainerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblName)
                    .addComponent(jtxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblCity)
                    .addComponent(jcmbBoxCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtxtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblSpecialist))
                    .addComponent(jchboxSpecialist))
                .addGap(27, 27, 27)
                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblTelephone))
                .addGap(30, 30, 30)
                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblEmail))
                .addGap(45, 45, 45)
                .addGroup(jpnlContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jbtnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jbtnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 20, Short.MAX_VALUE)
                .addComponent(jscrpnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    Dodavanje doktora - u setCityID index je +1 jer su u tablici gradova indexi od 0 a u bazi od 1
    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        try {
            if (validateFields()) {
                Doctor d = new Doctor();
                d.setName(jtxtName.getText());
                d.setSurname(jtxtSurname.getText());
                d.setTelephone(jtxtTelephone.getText());
                d.setEmail(jtxtEmail.getText());
                d.setSpecialist(jchboxSpecialist.isSelected());
                d.setCityID(jcmbBoxCity.getSelectedIndex() + 1);

                if (manager.InsertDoctor(d) != 0) {

                    model.addRow(new Object[]{d.getIDDoctor(), jtxtName.getText(), jtxtSurname.getText(), jtxtTelephone.getText(), jtxtEmail.getText(),
                        jchboxSpecialist.isSelected(), jcmbBoxCity.getSelectedItem()});
                    Utils.ClearForm(jpnlContainer);
                    JOptionPane.showMessageDialog(this, "Doctor successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Doctor already exists. Try again.", "!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed

        int selectedIndex = jtblDoctors.getSelectedRow();
        try {
            if (selectedIndex != -1) {
                int delete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete doctor?", "DELETE", JOptionPane.YES_NO_OPTION);
                if (delete == 0) {
                    int doctorID = (int) model.getValueAt(selectedIndex, 0);
                    if (manager.DeleteDoctor(doctorID) != 0) {
                        JOptionPane.showMessageDialog(this, "Doctor successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        model.removeRow(selectedIndex);
                        ResetModelTable();
                        Utils.ClearForm(jpnlContainer);
                    } else {
                        JOptionPane.showMessageDialog(this, "Doctor already exists. Try again.", "!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Error deleting doctor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnDeleteActionPerformed

    private void ResetModelTable() {
        model.setRowCount(0);
        ShowDoctors();
    }

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed
        try {
            if (validateFields()) {
                Doctor d = new Doctor();
                d.setIDDoctor((int) model.getValueAt(jtblDoctors.getSelectedRow(), 0));
                d.setName(jtxtName.getText());
                d.setSurname(jtxtSurname.getText());
                d.setTelephone(jtxtTelephone.getText());
                d.setEmail(jtxtEmail.getText());
                d.setSpecialist(jchboxSpecialist.isSelected());
                d.setCityID(jcmbBoxCity.getSelectedIndex() + 1);

                if (manager.UpdateDoctor(d) != 0) {

                    ResetModelTable();
                    Utils.ClearForm(jpnlContainer);
                    JOptionPane.showMessageDialog(this, "Doctor successfully edited.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Doctor already exists. Try again.", "!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnUpdateActionPerformed

    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearActionPerformed
        Utils.ClearForm(jpnlContainer);
    }//GEN-LAST:event_jbtnClearActionPerformed

    private void jtblDoctorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblDoctorsMouseClicked
        int i = jtblDoctors.getSelectedRow();
        jtxtName.setText(model.getValueAt(i, 1).toString());
        jtxtSurname.setText(model.getValueAt(i, 2).toString());
        jtxtTelephone.setText(model.getValueAt(i, 3).toString());
        jtxtEmail.setText(model.getValueAt(i, 4).toString());
        jchboxSpecialist.setSelected((boolean) model.getValueAt(i, 5));
        jcmbBoxCity.setSelectedItem(model.getValueAt(i, 6).toString());
    }//GEN-LAST:event_jtblDoctorsMouseClicked

    public boolean validateFields() {
        if (!Validators.validateField(jtxtName, "Please enter first name.")) {
            return false;
        } else if (!Validators.validateField(jtxtSurname, "Please enter last name.")) {
            return false;
        } else if (!Validators.validateField(jtxtTelephone, "Please enter phone number.")) {
            return false;
        } else if (!Validators.validateField(jtxtEmail, "Please enter email.")) {
            return false;
        } else {
            return true;
        }
    }

    private void LoadCitiesComboBox() {
        try {
            jcmbBoxCity.removeAllItems();
            List<City> cities = manager.GetCities();
            Vector comboItems = new Vector();

            for (City city : cities) {
                comboItems.add(city.getName());
            }

            comboBoxModel = new DefaultComboBoxModel(comboItems);
            jcmbBoxCity.setModel(comboBoxModel);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No cities to show.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JCheckBox jchboxSpecialist;
    private javax.swing.JComboBox<String> jcmbBoxCity;
    private javax.swing.JLabel jlblCity;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblIcon;
    private javax.swing.JLabel jlblName;
    private javax.swing.JLabel jlblSpecialist;
    private javax.swing.JLabel jlblSurname;
    private javax.swing.JLabel jlblTelephone;
    private javax.swing.JLabel jlblTitle;
    private javax.swing.JPanel jpnlContainer;
    private javax.swing.JPanel jpnlHeader;
    private javax.swing.JScrollPane jscrpnlTable;
    private javax.swing.JTable jtblDoctors;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtName;
    private javax.swing.JTextField jtxtSurname;
    private javax.swing.JTextField jtxtTelephone;
    // End of variables declaration//GEN-END:variables

}