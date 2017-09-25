
package BLL;

import DAL.IRepo;
import DAL.RepoFactory;
import Model.*;
import ViewModel.*;
import java.util.List;
/**
 *
 * @author Eugen
 */
public class DoctorManager {
    IRepo repo = RepoFactory.GetRepo(1);
    
    public List<MiniForm> GetPatientInfo() throws Exception {
        return repo.GetPatientInfo();
    }

    public int CreateNewPatientRecord(PatientRecord record) throws Exception {
        return repo.CreateNewPatientRecord(record);
    }

    public List<Item> GetItems() throws Exception {
        return repo.GetItems();
    }

    public int PrescribeItem(Item item) throws Exception {
        return repo.PrescribeItem(item);
    }

    public List<PatientRecord> GetPatientRecords(int doctorID) throws Exception {
        return repo.GetPatientRecords(doctorID);
    }

    public List<PatientRecord> GetPatientAppointments(int patientID, int doctorID) throws Exception {
        return repo.GetPatientAppointments(patientID, doctorID);
    }

    public List<PatientRecord> GetPatientsByDoctorID(int doctorID) throws Exception {
        return repo.GetPatientsByDoctorID(doctorID);
    }

    public List<Item> GetPrescribedItemsForPatient(int patientID) throws Exception {
        return repo.GetPrescribedItemsForPatient(patientID);
    }
}
