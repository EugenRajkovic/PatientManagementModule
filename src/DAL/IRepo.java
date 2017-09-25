
package DAL;

import Model.*;
import ViewModel.*;
import java.util.List;

/**
 *
 * @author Eugen
 */
public interface IRepo {
    
    public List<Doctor> GetAllDoctors() throws Exception;
    public int InsertDoctor(Doctor doktor)  throws Exception;
    public List<City> GetCities() throws Exception;
    public int DeleteDoctor(int doctorID) throws Exception;
    public int UpdateDoctor(Doctor doktor) throws Exception;
    public int MiniForm(MiniForm form) throws Exception;
    public int ComprehensiveForm(ComprehensiveForm form) throws Exception;
    public List<MiniForm> GetPatientInfo() throws Exception;
    public int CompletePatientInfo(MiniForm mini, ComprehensiveForm comprehensive) throws Exception;
    public int CreateNewPatientRecord(PatientRecord record) throws Exception;
    public List<Item> GetItems() throws Exception;
    public int PrescribeItem(Item item) throws Exception;
    public List<PatientRecord>GetPatientRecords(int doctorID) throws Exception;
    public int UpdatePatient(MiniForm mini, ComprehensiveForm comprehensive) throws Exception;
    public List<PatientRecord>GetPatientAppointments(int patientID, int doctorID) throws Exception;
    public List<PatientRecord>GetPatientsByDoctorID(int doctorID) throws Exception;
    public List<Item>GetPrescribedItemsForPatient(int patientID) throws Exception;
}
