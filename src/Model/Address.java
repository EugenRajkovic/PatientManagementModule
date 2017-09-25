/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ViewModel.ComprehensiveForm;

/**
 *
 * @author Eugen
 */
public class Address {

    /**
     * @return the IDPatientAddress
     */
    public int getIDPatientAddress() {
        return IDPatientAddress;
    }

    /**
     * @param IDPatientAddress the IDPatientAddress to set
     */
    public void setIDPatientAddress(int IDPatientAddress) {
        this.IDPatientAddress = IDPatientAddress;
    }

    /**
     * @return the PresentAddress
     */
    public boolean isPresentAddress() {
        return PresentAddress;
    }

    /**
     * @param PresentAddress the PresentAddress to set
     */
    public void setPresentAddress(boolean PresentAddress) {
        this.PresentAddress = PresentAddress;
    }

    /**
     * @return the KinAddress
     */
    public boolean isKinAddress() {
        return KinAddress;
    }

    /**
     * @param KinAddress the KinAddress to set
     */
    public void setKinAddress(boolean KinAddress) {
        this.KinAddress = KinAddress;
    }

    /**
     * @return the DoorNo
     */
    public int getDoorNo() {
        return DoorNo;
    }

    /**
     * @param DoorNo the DoorNo to set
     */
    public void setDoorNo(int DoorNo) {
        this.DoorNo = DoorNo;
    }

    /**
     * @return the Street
     */
    public String getStreet() {
        return Street;
    }

    /**
     * @param Street the Street to set
     */
    public void setStreet(String Street) {
        this.Street = Street;
    }

    /**
     * @return the Area
     */
    public String getArea() {
        return Area;
    }

    /**
     * @param Area the Area to set
     */
    public void setArea(String Area) {
        this.Area = Area;
    }

    /**
     * @return the City
     */
    public City getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(City City) {
        this.City = City;
    }

    /**
     * @return the PatientDetailsID
     */
    public ComprehensiveForm getPatientDetailsID() {
        return PatientDetailsID;
    }

    /**
     * @param PatientDetailsID the PatientDetailsID to set
     */
    public void setPatientDetailsID(ComprehensiveForm PatientDetailsID) {
        this.PatientDetailsID = PatientDetailsID;
    }
    
    private int IDPatientAddress;
    private boolean PresentAddress;
    private boolean KinAddress;
    private int DoorNo;
    private String Street;
    private String Area;
    private City City;
    private ComprehensiveForm PatientDetailsID;
}
