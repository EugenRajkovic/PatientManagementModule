package ViewModel;

import Model.*;

/**
 *
 * @author Eugen
 */
public class Doctor {

    /**
     * @return the IDDoctor
     */
    
    public Doctor(int doctorID){
        this.IDDoctor = doctorID;
    }
    
    public int getIDDoctor() {
        return IDDoctor;
    }

    public void setIDDoctor(int IDDoctor) {
        this.IDDoctor = IDDoctor;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Surname
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * @param Surname the Surname to set
     */
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    /**
     * @return the Telephone
     */
    public String getTelephone() {
        return Telephone;
    }

    /**
     * @param Telephone the Telephone to set
     */
    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Specialist
     */
    public boolean isSpecialist() {
        return Specialist;
    }

    /**
     * @param Specialist the Specialist to set
     */
    public void setSpecialist(boolean Specialist) {
        this.Specialist = Specialist;
    }

    public String getCityName() {
        return City.getName();
    }

    public void setCityName(String cityName) {
        this.City.setName(cityName);
    }

    /**
     * @return the CityID
     */
    public int getCityID() {
        return City.getIDCity();
    }

    public void setCityID(int cityID) {
        this.City.setIDCity(cityID);
    }

    private int IDDoctor;
    private String Name;
    private String Surname;
    private String Telephone;
    private String Email;
    private boolean Specialist;
    private City City;

    public Doctor() {
        this.City = new City();
    }

    @Override
    public String toString() {
        return String.format(formatIspisa.toString(), IDDoctor, Name, Surname, Telephone, Email, Boolean.toString(Specialist).equals("true") ? "Specialist" : "General physician", City.getName());
    }
    
    
    public String SimpleDoctorFormat(){
        return String.format("[%s] - %s %s", IDDoctor, Name, Surname);
    }
    
    public String DoctorNameSurname(){
        return String.format("%s %s", Name, Surname);
    }

    StringBuilder formatIspisa = new StringBuilder("ID: %s\n"
            + "Name: %s\n" + "Surname: %s\n" + "Telephone: %s\n" + "Email: %s\n" + "Type of doctor: %s\n" + "City: %s\n");
}
