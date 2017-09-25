/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.GUI;

import BLL.*;
import Helper.*;
import Model.*;
import ViewModel.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Eugen
 */
public class Doctors extends javax.swing.JFrame {

    private final AdministrationManager adminManager;
    private final DoctorManager doctorManager;
    private final NursesManager nursesManager;
    private int doctorID;
    private int patientID;
    
    private DefaultComboBoxModel comboBoxModel;

    private final DefaultTableModel tablePatientRecordsModel;
    private final DefaultTableModel tablePatientAppointmentsModel;
    private final DefaultTableModel tablePrescribedItemsModel;
    /**
     * Creates new form Doctors
     */
    public Doctors() {
        super("Doctors");
        initComponents();

        Toolkit tk = this.getToolkit();
        Dimension dim = tk.getScreenSize();
        setLocation((int) (dim.getWidth() - getWidth()) / 2, (int) (dim.getHeight() - getHeight()) / 2);

        doctorManager = new DoctorManager();
        adminManager = new AdministrationManager();
        nursesManager = new NursesManager();
        tablePatientRecordsModel = (DefaultTableModel)jtblPatientRecords.getModel();
        tablePatientAppointmentsModel = (DefaultTableModel)jtblPatientAppointments.getModel();
        tablePrescribedItemsModel = (DefaultTableModel)jtblPrescribedItems.getModel();
        
        jpnlPatientRecords.setVisible(false);

        LoadDoctorsComboBox();      
        
        AutoCompletion.enable(jcmbBoxDoctors);        
    }

    
    private void LoadDoctorsComboBox(){
        
        try {
            jcmbBoxDoctors.removeAllItems();
            List<Doctor> doctors = adminManager.GetAllDoctors();
            Vector comboItems = new Vector();
            
            for (Doctor doctor : doctors) {
                comboItems.add(doctor.DoctorNameSurname());
            }
            
            comboBoxModel = new DefaultComboBoxModel(comboItems);
            jcmbBoxDoctors.setModel(comboBoxModel);
        } 
        catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No doctors to show.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    private void LoadAllPatientsComboBox(){
        try {
            jcmbBoxSelectPatient.removeAllItems();
            List<MiniForm> patients = nursesManager.GetPatientInfo();
            Vector comboItems = new Vector();
            
            for (MiniForm patient : patients) {
                comboItems.add(patient.getFirstName() + " " + patient.getLastName());
            }
            
            comboBoxModel = new DefaultComboBoxModel(comboItems);
            jcmbBoxSelectPatient.setModel(comboBoxModel);
            jcmbBoxSelectPatient.setSelectedItem(null);
        } 
        catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No patients to show.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    
    private void LoadDoctorPatientsComboBox(JComboBox comboBox){
        try {
            comboBox.removeAllItems();
            List<PatientRecord> patients = doctorManager.GetPatientsByDoctorID(doctorID);
            Vector comboItems = new Vector();
            
            for (PatientRecord patient : patients) {
                comboItems.add(patient);
            }           
                
            KeySelectionRenderer renderer = new KeySelectionRenderer(comboBox) {
                @Override
                public String getDisplayValue(Object item) {
                    PatientRecord patient = (PatientRecord) item;
                    return patient.getPatient().getFirstName() + " " + patient.getPatient().getLastName();
                }
            };
            
            comboBoxModel = new DefaultComboBoxModel(comboItems);
            comboBox.setModel(comboBoxModel); 
            comboBox.setSelectedItem(null);
        } 
        catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No patients to show.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }    
    
    
    private void LoadTreatmentComboBox(){
        
        try {
            jcmbBoxTreatment.removeAllItems();
            List<Item> items = doctorManager.GetItems();
            Vector comboItems = new Vector();
            
            for (Item item : items) {
                comboItems.add(item.getItemDescription());
            }
            
            comboBoxModel = new DefaultComboBoxModel(comboItems);
            jcmbBoxTreatment.setModel(comboBoxModel);
            jcmbBoxTreatment.setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No treatments to show.", "Error", JOptionPane.ERROR_MESSAGE);
        }     
    }    
    
    
    private void ShowPatientRecords(int doctorID){
           
        try {
            tablePatientRecordsModel.setRowCount(0);
            List<PatientRecord> PatientRecords = doctorManager.GetPatientRecords(doctorID);
            
            for (PatientRecord record : PatientRecords) {
                tablePatientRecordsModel.addRow(new Object[]{record.getIDRecord(), record.getPatient().getFirstName(),record.getPatient().getLastName(),record.getAppointmentDate()});
                jtblPatientRecords.setModel(tablePatientRecordsModel);
            }           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No patient records to show.", "Error", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    
    private void ShowPatientAppointments(int patientID, int doctorID){
           
        try {
            tablePatientAppointmentsModel.setRowCount(0);
            List<PatientRecord> PatientRecords = doctorManager.GetPatientAppointments(patientID, doctorID);
            
            for (PatientRecord record : PatientRecords) {
                tablePatientAppointmentsModel.addRow(new Object[]{record.getIDRecord(), record.getAppointmentDate(), record.getFollowUpAppointment(), record.getDiagnosis()});
                jtblPatientRecords.setModel(tablePatientAppointmentsModel);
            }           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No patient appointments to show.", "Error", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    private void ShowPrescribedItems(int patientID){
           
        try {
            tablePrescribedItemsModel.setRowCount(0);
            List<Item> items = doctorManager.GetPrescribedItemsForPatient(patientID);
            
            for (Item item : items) {
                tablePrescribedItemsModel.addRow(new Object[]{item.getIDItem(), item.getItemDescription(), item.getPrice(), item.getRecord().getAppointmentDate(), 
                    item.getRecord().getDiagnosis()});
                jtblPrescribedItems.setModel(tablePrescribedItemsModel);
            }           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No prescribed items to show.", "Error", JOptionPane.ERROR_MESSAGE);
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
        jpnlSignIn = new javax.swing.JPanel();
        jlblInfo = new javax.swing.JLabel();
        jcmbBoxDoctors = new javax.swing.JComboBox<>();
        jbtnSignIn = new javax.swing.JButton();
        jlblWelcomeMsg = new javax.swing.JLabel();
        jpnlPatientRecords = new javax.swing.JTabbedPane();
        jpnlGetPatientRecords = new javax.swing.JPanel();
        jscrPnlRecords = new javax.swing.JScrollPane();
        jtblPatientRecords = new javax.swing.JTable();
        jpnlCreatePatientRecord = new javax.swing.JPanel();
        jlblSelectPatient = new javax.swing.JLabel();
        jcmbBoxSelectPatient = new javax.swing.JComboBox<>();
        jlblDiagnosis = new javax.swing.JLabel();
        jlblFollowUpAppointment = new javax.swing.JLabel();
        jXDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jlblTreatment = new javax.swing.JLabel();
        jcmbBoxTreatment = new javax.swing.JComboBox<>();
        jbtnSavePatientRecord = new javax.swing.JButton();
        jbtnClearForm = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxtAreaDiagnosis = new javax.swing.JTextArea();
        jpnlPatientAppointments = new javax.swing.JPanel();
        jlblSelectDoctorPatient = new javax.swing.JLabel();
        jcmbBoxDoctorPatients = new javax.swing.JComboBox<>();
        jscrPnlAppointments = new javax.swing.JScrollPane();
        jtblPatientAppointments = new javax.swing.JTable();
        jpnlPrescribedItems = new javax.swing.JPanel();
        jlblPrescribedItems = new javax.swing.JLabel();
        jcmbBoxPrescribedItems = new javax.swing.JComboBox<>();
        jscrPnlPrescribedItems = new javax.swing.JScrollPane();
        jtblPrescribedItems = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 500));

        jpnlHeader.setBackground(new java.awt.Color(51, 153, 255));

        jlblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/doctor.png"))); // NOI18N
        jlblIcon.setMaximumSize(new java.awt.Dimension(200, 200));
        jlblIcon.setMinimumSize(new java.awt.Dimension(200, 200));
        jlblIcon.setPreferredSize(new java.awt.Dimension(200, 200));

        jlblTitle.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 48)); // NOI18N
        jlblTitle.setText("VIRGO HOSPITAL");

        javax.swing.GroupLayout jpnlHeaderLayout = new javax.swing.GroupLayout(jpnlHeader);
        jpnlHeader.setLayout(jpnlHeaderLayout);
        jpnlHeaderLayout.setHorizontalGroup(
            jpnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        jpnlHeaderLayout.setVerticalGroup(
            jpnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlHeaderLayout.createSequentialGroup()
                        .addComponent(jlblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlHeaderLayout.createSequentialGroup()
                        .addComponent(jlblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );

        jlblInfo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlblInfo.setText("Enter doctor:");

        jcmbBoxDoctors.setEditable(true);
        jcmbBoxDoctors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnSignIn.setBackground(new java.awt.Color(102, 153, 255));
        jbtnSignIn.setText("SIGN IN");
        jbtnSignIn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jbtnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSignInActionPerformed(evt);
            }
        });

        jlblWelcomeMsg.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlblWelcomeMsg.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jpnlSignInLayout = new javax.swing.GroupLayout(jpnlSignIn);
        jpnlSignIn.setLayout(jpnlSignInLayout);
        jpnlSignInLayout.setHorizontalGroup(
            jpnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlSignInLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jlblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jcmbBoxDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblWelcomeMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnlSignInLayout.setVerticalGroup(
            jpnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlSignInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlSignInLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jcmbBoxDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlSignInLayout.createSequentialGroup()
                        .addGroup(jpnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblWelcomeMsg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnSignIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnlSignInLayout.createSequentialGroup()
                                .addComponent(jlblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        jpnlPatientRecords.setBackground(new java.awt.Color(255, 255, 255));
        jpnlPatientRecords.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jpnlPatientRecords.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jpnlPatientRecordsStateChanged(evt);
            }
        });

        jtblPatientRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PatientID", "First name", "Last name", "Appointment date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtblPatientRecords.getTableHeader().setReorderingAllowed(false);
        jscrPnlRecords.setViewportView(jtblPatientRecords);

        javax.swing.GroupLayout jpnlGetPatientRecordsLayout = new javax.swing.GroupLayout(jpnlGetPatientRecords);
        jpnlGetPatientRecords.setLayout(jpnlGetPatientRecordsLayout);
        jpnlGetPatientRecordsLayout.setHorizontalGroup(
            jpnlGetPatientRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jscrPnlRecords, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
        );
        jpnlGetPatientRecordsLayout.setVerticalGroup(
            jpnlGetPatientRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jscrPnlRecords, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
        );

        jpnlPatientRecords.addTab("Get patient records", jpnlGetPatientRecords);

        jpnlCreatePatientRecord.setBackground(new java.awt.Color(255, 255, 255));

        jlblSelectPatient.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblSelectPatient.setText("Select patient:");

        jcmbBoxSelectPatient.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcmbBoxSelectPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlblDiagnosis.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblDiagnosis.setText("Enter diagnosis:");

        jlblFollowUpAppointment.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblFollowUpAppointment.setText("Follow up appointment");

        jlblTreatment.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblTreatment.setText("Treatment/Lab tests/Medication");

        jcmbBoxTreatment.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcmbBoxTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnSavePatientRecord.setBackground(new java.awt.Color(102, 153, 255));
        jbtnSavePatientRecord.setText("SAVE");
        jbtnSavePatientRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSavePatientRecordActionPerformed(evt);
            }
        });

        jbtnClearForm.setBackground(new java.awt.Color(102, 153, 255));
        jbtnClearForm.setText("CLEAR");
        jbtnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearFormActionPerformed(evt);
            }
        });

        jtxtAreaDiagnosis.setColumns(20);
        jtxtAreaDiagnosis.setRows(5);
        jScrollPane2.setViewportView(jtxtAreaDiagnosis);

        javax.swing.GroupLayout jpnlCreatePatientRecordLayout = new javax.swing.GroupLayout(jpnlCreatePatientRecord);
        jpnlCreatePatientRecord.setLayout(jpnlCreatePatientRecordLayout);
        jpnlCreatePatientRecordLayout.setHorizontalGroup(
            jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlCreatePatientRecordLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlCreatePatientRecordLayout.createSequentialGroup()
                        .addGroup(jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblDiagnosis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblSelectPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcmbBoxSelectPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlCreatePatientRecordLayout.createSequentialGroup()
                        .addGroup(jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblFollowUpAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addComponent(jcmbBoxTreatment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnlCreatePatientRecordLayout.createSequentialGroup()
                        .addComponent(jbtnClearForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jbtnSavePatientRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jpnlCreatePatientRecordLayout.setVerticalGroup(
            jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlCreatePatientRecordLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblSelectPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbBoxSelectPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblFollowUpAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jlblDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnlCreatePatientRecordLayout.createSequentialGroup()
                        .addComponent(jlblTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcmbBoxTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257)
                        .addGroup(jpnlCreatePatientRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnClearForm, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnSavePatientRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jpnlPatientRecords.addTab("Create patient record", jpnlCreatePatientRecord);

        jpnlPatientAppointments.setBackground(new java.awt.Color(255, 255, 255));

        jlblSelectDoctorPatient.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblSelectDoctorPatient.setText("Select patient:");

        jcmbBoxDoctorPatients.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcmbBoxDoctorPatients.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbBoxDoctorPatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbBoxDoctorPatientsActionPerformed(evt);
            }
        });

        jtblPatientAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Record ID", "Appointment date", "Follow-up appointment", "Diagnosis"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPatientAppointments.getTableHeader().setReorderingAllowed(false);
        jscrPnlAppointments.setViewportView(jtblPatientAppointments);

        javax.swing.GroupLayout jpnlPatientAppointmentsLayout = new javax.swing.GroupLayout(jpnlPatientAppointments);
        jpnlPatientAppointments.setLayout(jpnlPatientAppointmentsLayout);
        jpnlPatientAppointmentsLayout.setHorizontalGroup(
            jpnlPatientAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlPatientAppointmentsLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jlblSelectDoctorPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcmbBoxDoctorPatients, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(584, Short.MAX_VALUE))
            .addComponent(jscrPnlAppointments)
        );
        jpnlPatientAppointmentsLayout.setVerticalGroup(
            jpnlPatientAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlPatientAppointmentsLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jpnlPatientAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblSelectDoctorPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbBoxDoctorPatients, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(jscrPnlAppointments, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        jpnlPatientRecords.addTab("Patient appointments", jpnlPatientAppointments);

        jpnlPrescribedItems.setBackground(new java.awt.Color(255, 255, 255));

        jlblPrescribedItems.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlblPrescribedItems.setText("Select patient:");

        jcmbBoxPrescribedItems.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcmbBoxPrescribedItems.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbBoxPrescribedItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbBoxPrescribedItemsActionPerformed(evt);
            }
        });

        jtblPrescribedItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ItemID", "Description", "Price", "Appointment date", "Diagnosis"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPrescribedItems.getTableHeader().setReorderingAllowed(false);
        jscrPnlPrescribedItems.setViewportView(jtblPrescribedItems);

        javax.swing.GroupLayout jpnlPrescribedItemsLayout = new javax.swing.GroupLayout(jpnlPrescribedItems);
        jpnlPrescribedItems.setLayout(jpnlPrescribedItemsLayout);
        jpnlPrescribedItemsLayout.setHorizontalGroup(
            jpnlPrescribedItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlPrescribedItemsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlblPrescribedItems, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcmbBoxPrescribedItems, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(588, Short.MAX_VALUE))
            .addComponent(jscrPnlPrescribedItems)
        );
        jpnlPrescribedItemsLayout.setVerticalGroup(
            jpnlPrescribedItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlPrescribedItemsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jpnlPrescribedItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblPrescribedItems, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcmbBoxPrescribedItems, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jscrPnlPrescribedItems, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
        );

        jpnlPatientRecords.addTab("Prescribed items", jpnlPrescribedItems);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlSignIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnlPatientRecords)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlPatientRecords))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //This method is called when button "Sign in" is pressed. It activates the panel Patient records and shows the records of patients
    //of selected doctor. Indexes are +1 because they start from 0 and in database they start from 1.
    private void jbtnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSignInActionPerformed
        try {
            jlblInfo.setVisible(false);
            jcmbBoxDoctors.setVisible(false);
            jbtnSignIn.setVisible(false);
            jpnlPatientRecords.setVisible(true);
            doctorID = jcmbBoxDoctors.getSelectedIndex() + 1;
            Thread.sleep(1000);
            jlblWelcomeMsg.setText("Welcome doctor " + jcmbBoxDoctors.getSelectedItem().toString() + "!");
            ShowPatientRecords(doctorID);
        } catch (InterruptedException ex) {
            Logger.getLogger(Doctors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSignInActionPerformed

    private void jpnlPatientRecordsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jpnlPatientRecordsStateChanged
        JTabbedPane pane = (JTabbedPane)evt.getSource();
        switch (pane.getSelectedIndex()) {
            case 0:
                if (doctorID == 0) {
                    return;
                }
                ShowPatientRecords(doctorID);
                break;
            case 1:
                LoadAllPatientsComboBox();
                LoadTreatmentComboBox();
                break;
            case 2:
                LoadDoctorPatientsComboBox(jcmbBoxDoctorPatients);
                break;
            case 3:
                LoadDoctorPatientsComboBox(jcmbBoxPrescribedItems);
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_jpnlPatientRecordsStateChanged

    private void jbtnSavePatientRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSavePatientRecordActionPerformed
        patientID = jcmbBoxSelectPatient.getSelectedIndex() + 1;
        
        boolean diagnosis = Validators.validateTextArea(jtxtAreaDiagnosis);        
        boolean comboboxPatient = Validators.validateComboBox(jcmbBoxSelectPatient);
        boolean comboboxTreatment = Validators.validateComboBox(jcmbBoxTreatment);
        boolean date = Validators.validateJXDate(jXDatePicker);
               
        try {
            if (diagnosis && comboboxPatient && comboboxTreatment && date) {
                int addRecord = JOptionPane.showConfirmDialog(this, "Are you sure you want to add new patient record?", "Patient record",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (addRecord == 0) {
                    PatientRecord record = new PatientRecord();
                    MiniForm patient = new MiniForm();                    
                    Item treatment = new Item();
                    
                    patient.setIDPatient(patientID);
                    record.setPatient(patient);
                    record.setDoctor(new Doctor(doctorID));
                    record.setDiagnosis(jtxtAreaDiagnosis.getText());
                    record.setFollowUpAppointment(jXDatePicker.getDate());
                    treatment.setIDItem(jcmbBoxTreatment.getSelectedIndex()+1);
                    if (doctorManager.CreateNewPatientRecord(record) != 0 && doctorManager.PrescribeItem(treatment) != 0) {
                        JOptionPane.showMessageDialog(this, "Patient record successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        Utils.ClearForm(jpnlCreatePatientRecord);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Error adding patient record. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            else if (diagnosis && comboboxPatient) {
                int addRecord = JOptionPane.showConfirmDialog(this, "Are you sure you want to add patient record without follow-up appointment date and treatment?", "Patient record",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (addRecord == 0) {
                    PatientRecord record = new PatientRecord();
                    MiniForm patient = new MiniForm();                    
                    
                    patient.setIDPatient(patientID);
                    record.setPatient(patient);
                    record.setDoctor(new Doctor(doctorID));
                    record.setDiagnosis(jtxtAreaDiagnosis.getText());
                    if (doctorManager.CreateNewPatientRecord(record) != 0) {
                        JOptionPane.showMessageDialog(this, "Patient record successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        Utils.ClearForm(jpnlCreatePatientRecord);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Error adding patient record. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }            
            else{
                JOptionPane.showMessageDialog(this, "Please select at least a patient and enter diagnosis.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } 
        catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jbtnSavePatientRecordActionPerformed

    private void jbtnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearFormActionPerformed
        Utils.ClearForm(jpnlCreatePatientRecord);
    }//GEN-LAST:event_jbtnClearFormActionPerformed

    private void jcmbBoxDoctorPatientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbBoxDoctorPatientsActionPerformed
        try {
            if (jcmbBoxDoctorPatients.getSelectedItem() != null) {
                tablePatientAppointmentsModel.setRowCount(0);
                PatientRecord patient = (PatientRecord) jcmbBoxDoctorPatients.getSelectedItem();            
                ShowPatientAppointments(patient.getPatient().getIDPatient(), doctorID);
            }
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jcmbBoxDoctorPatientsActionPerformed

    private void jcmbBoxPrescribedItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbBoxPrescribedItemsActionPerformed
        try {
            if (jcmbBoxPrescribedItems.getSelectedItem() != null) {
                tablePrescribedItemsModel.setRowCount(0);
                PatientRecord patient = (PatientRecord) jcmbBoxPrescribedItems.getSelectedItem();            
                ShowPrescribedItems(patient.getPatient().getIDPatient());
            }
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jcmbBoxPrescribedItemsActionPerformed

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
            java.util.logging.Logger.getLogger(Doctors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Doctors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Doctors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Doctors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Doctors().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker;
    private javax.swing.JButton jbtnClearForm;
    private javax.swing.JButton jbtnSavePatientRecord;
    private javax.swing.JButton jbtnSignIn;
    private javax.swing.JComboBox<String> jcmbBoxDoctorPatients;
    private javax.swing.JComboBox<String> jcmbBoxDoctors;
    private javax.swing.JComboBox<String> jcmbBoxPrescribedItems;
    private javax.swing.JComboBox<String> jcmbBoxSelectPatient;
    private javax.swing.JComboBox<String> jcmbBoxTreatment;
    private javax.swing.JLabel jlblDiagnosis;
    private javax.swing.JLabel jlblFollowUpAppointment;
    private javax.swing.JLabel jlblIcon;
    private javax.swing.JLabel jlblInfo;
    private javax.swing.JLabel jlblPrescribedItems;
    private javax.swing.JLabel jlblSelectDoctorPatient;
    private javax.swing.JLabel jlblSelectPatient;
    private javax.swing.JLabel jlblTitle;
    private javax.swing.JLabel jlblTreatment;
    private javax.swing.JLabel jlblWelcomeMsg;
    private javax.swing.JPanel jpnlCreatePatientRecord;
    private javax.swing.JPanel jpnlGetPatientRecords;
    private javax.swing.JPanel jpnlHeader;
    private javax.swing.JPanel jpnlPatientAppointments;
    private javax.swing.JTabbedPane jpnlPatientRecords;
    private javax.swing.JPanel jpnlPrescribedItems;
    private javax.swing.JPanel jpnlSignIn;
    private javax.swing.JScrollPane jscrPnlAppointments;
    private javax.swing.JScrollPane jscrPnlPrescribedItems;
    private javax.swing.JScrollPane jscrPnlRecords;
    private javax.swing.JTable jtblPatientAppointments;
    private javax.swing.JTable jtblPatientRecords;
    private javax.swing.JTable jtblPrescribedItems;
    private javax.swing.JTextArea jtxtAreaDiagnosis;
    // End of variables declaration//GEN-END:variables
}
