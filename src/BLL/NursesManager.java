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
public class NursesManager {

    IRepo repo = RepoFactory.GetRepo(1);

    public int MiniForm(MiniForm form) throws Exception {
        return repo.MiniForm(form);
    }

    public int ComprehensiveForm(ComprehensiveForm form) throws Exception {
        return repo.ComprehensiveForm(form);
    }

    public List<MiniForm> GetPatientInfo() throws Exception {
        return repo.GetPatientInfo();
    }

    public int CompletePatientInfo(MiniForm mini, ComprehensiveForm comprehensive) throws Exception {
        return repo.CompletePatientInfo(mini, comprehensive);
    }

    public int UpdatePatient(MiniForm mini, ComprehensiveForm comprehensive) throws Exception {
        return repo.UpdatePatient(mini, comprehensive);
    }
}
