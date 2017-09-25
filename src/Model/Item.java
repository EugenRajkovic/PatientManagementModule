
package Model;

import ViewModel.PatientRecord;

/**
 *
 * @author Eugen
 */
public class Item {

    public Item() {
        this.record = new PatientRecord();
    }  

    /**
     * @return the record
     */
    public PatientRecord getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(PatientRecord record) {
        this.record = record;
    }

    /**
     * @return the IDItem
     */
    public int getIDItem() {
        return IDItem;
    }

    /**
     * @param IDItem the IDItem to set
     */
    public void setIDItem(int IDItem) {
        this.IDItem = IDItem;
    }

    /**
     * @return the ItemDescription
     */
    public String getItemDescription() {
        return ItemDescription;
    }

    /**
     * @param ItemDescription the ItemDescription to set
     */
    public void setItemDescription(String ItemDescription) {
        this.ItemDescription = ItemDescription;
    }

    /**
     * @return the Price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    private int IDItem;
    private String ItemDescription;
    private double Price;
    private PatientRecord record;

    @Override
    public String toString() {
        return String.format("[%s] - %s | %,.2f", IDItem, ItemDescription, Price);
    }   
    
    public String PrescribedItemInfo(){
        return String.format("[%s] - %s | %,.2f | %tF | %s", IDItem, ItemDescription, Price, record.getAppointmentDate(), record.getDiagnosis());
    }
}
