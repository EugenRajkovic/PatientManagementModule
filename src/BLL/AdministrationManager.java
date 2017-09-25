
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
public class AdministrationManager {
    IRepo repo = RepoFactory.GetRepo(1);
    
    public List<Doctor> GetAllDoctors() throws Exception{
        return repo.GetAllDoctors();
    }
    
    public int InsertDoctor(Doctor d) throws Exception{
        return repo.InsertDoctor(d);
    }
    
    public List<City> GetCities() throws Exception{
        return repo.GetCities();
    }
    
    public int DeleteDoctor(int doctor) throws Exception{
        return repo.DeleteDoctor(doctor);
    }
    
    public int UpdateDoctor(Doctor d) throws Exception{
        return repo.UpdateDoctor(d);
    }
}
