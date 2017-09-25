
package Model;

/**
 *
 * @author Eugen
 */
public class City {

    /**
     * @return the IDCity
     */
    public int getIDCity() {
        return IDCity;
    }

    /**
     * @param IDCity the IDCity to set
     */
    public void setIDCity(int IDCity) {
        this.IDCity = IDCity;
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
    
    private int IDCity;
    private String Name;
    private final int CountryID;

    public City() {
        this.CountryID = 1;
    }
    
    
}
