
package ViewModel;

import Model.Item;
import java.util.Date;

/**
 *
 * @author Eugen
 */
public class PatientRecord {

    /**
     * @return the Item
     */
    public Item getItem() {
        return Item;
    }

    /**
     * @param Item the Item to set
     */
    public void setItem(Item Item) {
        this.Item = Item;
    }

    /**
     * @return the IDRecord
     */
    
    
    public PatientRecord() {
        this.Patient = new MiniForm();
    }

    public int getIDRecord() {
        return IDRecord;
    }

    /**
     * @param IDRecord the IDRecord to set
     */
    public void setIDRecord(int IDRecord) {
        this.IDRecord = IDRecord;
    }

    /**
     * @return the Patient
     */
    public MiniForm getPatient() {
        return Patient;
    }

    /**
     * @param Patient the Patient to set
     */
    public void setPatient(MiniForm Patient) {
        this.Patient = Patient;
    }

    /**
     * @return the Doctor
     */
    public Doctor getDoctor() {
        return Doctor;
    }

    /**
     * @param Doctor the Doctor to set
     */
    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    /**
     * @return the Diagnosis
     */
    public String getDiagnosis() {
        return Diagnosis;
    }

    /**
     * @param Diagnosis the Diagnosis to set
     */
    public void setDiagnosis(String Diagnosis) {
        this.Diagnosis = Diagnosis;
    }

    /**
     * @return the AppointmentDate
     */
    public Date getAppointmentDate() {
        return AppointmentDate;
    }

    /**
     * @param AppointmentDate the AppointmentDate to set
     */
    public void setAppointmentDate(Date AppointmentDate) {
        this.AppointmentDate = AppointmentDate;
    }

    /**
     * @return the FollowUpAppointment
     */
    public Date getFollowUpAppointment() {
        return FollowUpAppointment;
    }

    /**
     * @param FollowUpAppointment the FollowUpAppointment to set
     */
    public void setFollowUpAppointment(Date FollowUpAppointment) {
        this.FollowUpAppointment = FollowUpAppointment;
    }
    
    private int IDRecord;
    private MiniForm Patient;
    private Doctor Doctor;
    private String Diagnosis;
    private Date AppointmentDate;
    private Date FollowUpAppointment;
    private Item Item;

    @Override
    public String toString() {
        return String.format("[%d] - %s %s | %tF", IDRecord, Patient.getFirstName(), Patient.getLastName(), AppointmentDate);
    }   
    
    public String AppointmentsFormat(){
        return String.format("[%d] - %tF | %tF | %s", IDRecord, AppointmentDate, FollowUpAppointment, Diagnosis);
    }

    public String SimplePatientFormat() {
        return String.format("[%d] - %s %s %s", Patient.getIDPatient(), Patient.getFirstName(), Patient.getMiddleName(), Patient.getLastName());
    }   
    
}
