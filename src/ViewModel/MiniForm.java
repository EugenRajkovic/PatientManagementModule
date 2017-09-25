
package ViewModel;

import Model.ContactInfo;
import java.util.Date;

/**
 *
 * @author Eugen
 */
public class MiniForm {

    /**
     * @return the IDPatient
     */
    
    
//    public MiniForm(int IDPatient) {
//        this.IDPatient = IDPatient;
//    }

    public int getIDPatient() {
        return IDPatient;
    }

    /**
     * @param IDPatient the IDPatient to set
     */
    public void setIDPatient(int IDPatient) {
        this.IDPatient = IDPatient;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return the MiddleName
     */
    public String getMiddleName() {
        return MiddleName;
    }

    /**
     * @param MiddleName the MiddleName to set
     */
    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the Sex
     */
    public char getSex() {
        return Sex;
    }

    /**
     * @param Sex the Sex to set
     */
    public void setSex(char Sex) {
        this.Sex = Sex;
    }

    /**
     * @return the DateOfBirth
     */
    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    /**
     * @param DateOfBirth the DateOfBirth to set
     */
    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    /**
     * @return the Complaint
     */
    public String getComplaint() {
        return Complaint;
    }

    /**
     * @param Complaint the Complaint to set
     */
    public void setComplaint(String Complaint) {
        this.Complaint = Complaint;
    }

    /**
     * @return the KinFirstName
     */
    public String getKinFirstName() {
        return KinFirstName;
    }

    /**
     * @param KinFirstName the KinFirstName to set
     */
    public void setKinFirstName(String KinFirstName) {
        this.KinFirstName = KinFirstName;
    }

    /**
     * @return the KinMiddleName
     */
    public String getKinMiddleName() {
        return KinMiddleName;
    }

    /**
     * @param KinMiddleName the KinMiddleName to set
     */
    public void setKinMiddleName(String KinMiddleName) {
        this.KinMiddleName = KinMiddleName;
    }

    /**
     * @return the KinLastName
     */
    public String getKinLastName() {
        return KinLastName;
    }

    /**
     * @param KinLastName the KinLastName to set
     */
    public void setKinLastName(String KinLastName) {
        this.KinLastName = KinLastName;
    }

    /**
     * @return the RelationshipToPatient
     */
    public String getRelationshipToPatient() {
        return RelationshipToPatient;
    }

    /**
     * @param RelationshipToPatient the RelationshipToPatient to set
     */
    public void setRelationshipToPatient(String RelationshipToPatient) {
        this.RelationshipToPatient = RelationshipToPatient;
    }

    /**
     * @return the ContactInfo
     */
    public ContactInfo getContactInfo() {
        return ContactInfo;
    }

    /**
     * @param ContactInfo the ContactInfo to set
     */
    public void setContactInfo(ContactInfo ContactInfo) {
        this.ContactInfo = ContactInfo;
    }
    
    
    private int IDPatient;
    private String FirstName;    
    private String MiddleName;
    private String LastName;
    private char Sex;
    private Date DateOfBirth;
    private String Complaint;
    private String KinFirstName;    
    private String KinMiddleName;
    private String KinLastName;
    private String RelationshipToPatient;
    private ContactInfo ContactInfo;

    @Override
    public String toString() {
        return String.format("%d - %s %s %s", IDPatient, FirstName, MiddleName, LastName);
    }  
}
