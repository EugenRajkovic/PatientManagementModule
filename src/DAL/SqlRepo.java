package DAL;

import Helper.*;
import Model.City;
import Model.Item;
import ViewModel.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eugen
 */
public class SqlRepo implements IRepo {

    SQLServerDataSource ds = SQLHelper.createDataSource();

    @Override
    public List<Doctor> GetAllDoctors() throws Exception {

        List<Doctor> doktori = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_GetAllDoctors()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setIDDoctor(rs.getInt("IDDoctor"));
                d.setName(rs.getString("Name"));
                d.setSurname(rs.getString("Surname"));
                d.setTelephone(rs.getString("Telephone"));
                d.setEmail(rs.getString("Email"));
                d.setSpecialist(rs.getBoolean("Specialist"));
                d.setCityName(rs.getString("CityName"));
                doktori.add(d);
            }

        }
        return doktori;
    }

    @Override
    public int InsertDoctor(Doctor d) throws Exception {

        int insert = 0;
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_InsertDoctor(?,?,?,?,?,?)}");
            cs.setString(1, d.getName());
            cs.setString(2, d.getSurname());
            cs.setString(3, d.getTelephone());
            cs.setString(4, d.getEmail());
            cs.setBoolean(5, d.isSpecialist());
            cs.setInt(6, d.getCityID());

            insert = cs.executeUpdate();
            return insert;
        }
    }

    @Override
    public List<City> GetCities() throws Exception {
        List<City> cities = new ArrayList<>();

        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_GetCities()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                City c = new City();
                c.setIDCity(rs.getInt("IDCity"));
                c.setName(rs.getString("Name"));
                cities.add(c);
            }
        }
        return cities;
    }

    @Override
    public int DeleteDoctor(int doctor) throws Exception {
        int deleted = 0;
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_DeleteDoctor(?)}");
            cs.setInt(1, doctor);

            deleted = cs.executeUpdate();
        }
        return deleted;
    }

    @Override
    public int UpdateDoctor(Doctor d) throws Exception {
        int unos = 0;
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_UpdateDoctor(?,?,?,?,?,?,?)}");
            cs.setInt(1, d.getIDDoctor());
            cs.setString(2, d.getName());
            cs.setString(3, d.getSurname());
            cs.setString(4, d.getTelephone());
            cs.setString(5, d.getEmail());
            cs.setBoolean(6, d.isSpecialist());
            cs.setInt(7, d.getCityID());

            unos = cs.executeUpdate();
        }
        return unos;
    }

    @Override
    public int MiniForm(MiniForm f) throws Exception {

        int insert = 0;

        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_MiniForm(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, f.getFirstName());
            cs.setString(2, f.getMiddleName());
            cs.setString(3, f.getLastName());
            cs.setString(4, String.valueOf(f.getSex()));
            cs.setDate(5, Parser.ParseUtilDateToSqlDate(f.getDateOfBirth()));
            cs.setString(6, f.getComplaint());
            cs.setString(7, f.getKinFirstName());
            cs.setString(8, f.getKinMiddleName());
            cs.setString(9, f.getKinLastName());
            cs.setString(10, f.getRelationshipToPatient());
            cs.setString(11, f.getContactInfo().getTelephone1());
            cs.setString(12, f.getContactInfo().getTelephone2());

            insert = cs.executeUpdate();
        }
        return insert;
    }

    @Override
    public int ComprehensiveForm(ComprehensiveForm f) throws Exception {
        int insert = 0;

        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_ComprehensiveForm(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, f.getMiniFormID().getIDPatient());
            cs.setBoolean(2, f.getPatientAddress().isPresentAddress());
            cs.setBoolean(3, f.getPatientAddress().isKinAddress());
            cs.setString(4, f.getPatientAddress().getStreet());
            cs.setInt(5, f.getPatientAddress().getDoorNo());
            cs.setString(6, f.getPatientAddress().getArea());
            cs.setInt(7, f.getPatientAddress().getCity().getIDCity());

            cs.setBoolean(8, f.getKinAddress().isPresentAddress());
            cs.setBoolean(9, f.getKinAddress().isKinAddress());
            cs.setString(10, f.getKinAddress().getStreet());
            cs.setInt(11, f.getKinAddress().getDoorNo());
            cs.setString(12, f.getKinAddress().getArea());
            cs.setInt(13, f.getKinAddress().getCity().getIDCity());

            cs.setString(14, f.getContactInfo().getTelephoneWork());
            cs.setString(15, f.getContactInfo().getTelephoneHome());
            cs.setString(16, f.getContactInfo().getMobile());
            cs.setString(17, f.getContactInfo().getPager());
            cs.setString(18, f.getContactInfo().getFax());
            cs.setString(19, f.getContactInfo().getEmail());

            cs.setString(20, f.getMaritalStatus());
            cs.setInt(21, f.getNoOfDependants());
            cs.setInt(22, f.getHeight());
            cs.setInt(23, f.getWeight());
            cs.setString(24, f.getBloodType());
            cs.setString(25, f.getOccupation());
            cs.setDouble(26, f.getGrossAnnualIncome());
            cs.setBoolean(27, f.isVegetarian());
            cs.setBoolean(28, f.isSmoker());
            cs.setInt(29, f.getAverageNrCigarettes());
            cs.setBoolean(30, f.isConsumingAlcohol());
            cs.setInt(31, f.getAverageNrDrinks());
            cs.setString(32, f.getUsingStimulants());
            cs.setInt(33, f.getCoffeeOrTeaPerDay());
            cs.setInt(34, f.getSoftDrinksPerDay());
            cs.setBoolean(35, f.isHaveRegularMeals());
            cs.setString(36, f.getHomeFoodOrOutside());
            cs.setString(37, f.getComplaint());
            cs.setString(38, f.getDiabetic());
            cs.setString(39, f.getHypertensive());
            cs.setString(40, f.getCardiacCondition());
            cs.setString(41, f.getRespiratoryCondition());
            cs.setString(42, f.getDigestiveCondition());
            cs.setString(43, f.getOrthopedicCondition());
            cs.setString(44, f.getMuscularCondition());
            cs.setString(45, f.getNeurologicalCondition());
            cs.setString(46, f.getKnownAllergies());
            cs.setString(47, f.getReactionToDrugs());
            cs.setString(48, f.getMajorSurgeries());

            insert = cs.executeUpdate();
        }
        return insert;
    }

    @Override
    public List<MiniForm> GetPatientInfo() throws Exception {

        List<MiniForm> pacijenti = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_PatientInfo()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MiniForm mf = new MiniForm();
                mf.setIDPatient(rs.getInt("IDPatient"));
                mf.setFirstName(rs.getString("FirstName"));
                mf.setMiddleName(rs.getString("MiddleName"));
                mf.setLastName(rs.getString("LastName"));
                pacijenti.add(mf);
            }
        }
        return pacijenti;
    }

    @Override
    public int CompletePatientInfo(MiniForm mf, ComprehensiveForm cf) throws Exception {
        int insert = 0;

        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_CompletePatientInfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, mf.getFirstName());
            cs.setString(2, mf.getMiddleName());
            cs.setString(3, mf.getLastName());
            cs.setString(4, String.valueOf(mf.getSex()));
            cs.setDate(5, Parser.ParseUtilDateToSqlDate(mf.getDateOfBirth()));
            cs.setString(6, mf.getComplaint());
            cs.setString(7, mf.getKinFirstName());
            cs.setString(8, mf.getKinMiddleName());
            cs.setString(9, mf.getKinLastName());
            cs.setString(10, mf.getRelationshipToPatient());

            cs.setInt(11, cf.getMiniFormID().getIDPatient());

            cs.setBoolean(12, cf.getPatientAddress().isPresentAddress());
            cs.setBoolean(13, cf.getPatientAddress().isKinAddress());
            cs.setString(14, cf.getPatientAddress().getStreet());
            cs.setInt(15, cf.getPatientAddress().getDoorNo());
            cs.setString(16, cf.getPatientAddress().getArea());
            cs.setInt(17, cf.getPatientAddress().getCity().getIDCity());

            cs.setBoolean(18, cf.getKinAddress().isPresentAddress());
            cs.setBoolean(19, cf.getKinAddress().isKinAddress());
            cs.setString(20, cf.getKinAddress().getStreet());
            cs.setInt(21, cf.getKinAddress().getDoorNo());
            cs.setString(22, cf.getKinAddress().getArea());
            cs.setInt(23, cf.getKinAddress().getCity().getIDCity());

            cs.setString(24, mf.getContactInfo().getTelephone1());
            cs.setString(25, mf.getContactInfo().getTelephone2());
            cs.setString(26, mf.getContactInfo().getTelephoneWork());
            cs.setString(27, mf.getContactInfo().getTelephoneHome());
            cs.setString(28, mf.getContactInfo().getMobile());
            cs.setString(29, mf.getContactInfo().getPager());
            cs.setString(30, mf.getContactInfo().getFax());
            cs.setString(31, mf.getContactInfo().getEmail());

            cs.setString(32, cf.getMaritalStatus());
            cs.setInt(33, cf.getNoOfDependants());
            cs.setInt(34, cf.getHeight());
            cs.setInt(35, cf.getWeight());
            cs.setString(36, cf.getBloodType());
            cs.setString(37, cf.getOccupation());
            cs.setDouble(38, cf.getGrossAnnualIncome());
            cs.setBoolean(39, cf.isVegetarian());
            cs.setBoolean(40, cf.isSmoker());
            cs.setInt(41, cf.getAverageNrCigarettes());
            cs.setBoolean(42, cf.isConsumingAlcohol());
            cs.setInt(43, cf.getAverageNrDrinks());
            cs.setString(44, cf.getUsingStimulants());
            cs.setInt(45, cf.getCoffeeOrTeaPerDay());
            cs.setInt(46, cf.getSoftDrinksPerDay());
            cs.setBoolean(47, cf.isHaveRegularMeals());
            cs.setString(48, cf.getHomeFoodOrOutside());
            cs.setString(49, cf.getComplaint());
            cs.setString(50, cf.getDiabetic());
            cs.setString(51, cf.getHypertensive());
            cs.setString(52, cf.getCardiacCondition());
            cs.setString(53, cf.getRespiratoryCondition());
            cs.setString(54, cf.getDigestiveCondition());
            cs.setString(55, cf.getOrthopedicCondition());
            cs.setString(56, cf.getMuscularCondition());
            cs.setString(57, cf.getNeurologicalCondition());
            cs.setString(58, cf.getKnownAllergies());
            cs.setString(59, cf.getReactionToDrugs());
            cs.setString(60, cf.getMajorSurgeries());

            insert = cs.executeUpdate();
        }
        return insert;
    }

    @Override
    public int CreateNewPatientRecord(PatientRecord r) throws Exception {

        int insert = 0;
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_CreateNewRecord(?,?,?,?)}");
            cs.setInt(1, r.getPatient().getIDPatient());
            cs.setInt(2, r.getDoctor().getIDDoctor());
            cs.setString(3, r.getDiagnosis());
            if (Parser.ParseUtilDateToSqlDate(r.getFollowUpAppointment()) == null) {
                cs.setNull(4, java.sql.Types.DATE);
            } else {
                cs.setDate(4, Parser.ParseUtilDateToSqlDate(r.getFollowUpAppointment()));
            }
            insert = cs.executeUpdate();
        }
        return insert;
    }

    @Override
    public List<Item> GetItems() throws Exception {

        List<Item> items = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_GetItems()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Item i = new Item();
                i.setIDItem(rs.getInt("IDItem"));
                i.setItemDescription(rs.getString("ItemDescription"));
                i.setPrice(rs.getDouble("Price"));
                items.add(i);
            }

        }
        return items;
    }

    @Override
    public int PrescribeItem(Item item) throws Exception {

        int insert = 0;
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_PrescribeItem(?)}");
            cs.setInt(1, item.getIDItem());

            insert = cs.executeUpdate();
        }
        return insert;
    }

    @Override
    public List<PatientRecord> GetPatientRecords(int doctorID) throws Exception {
        List<PatientRecord> records = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_GetPatientRecords(?)}");
            cs.setInt(1, doctorID);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PatientRecord r = new PatientRecord();
                r.setIDRecord(rs.getInt("IDRecord"));
                r.getPatient().setFirstName(rs.getString("FirstName"));
                r.getPatient().setLastName(rs.getString("LastName"));
                r.setAppointmentDate(Parser.ParseSQLDateToUtilDate(rs.getDate("AppointmentDate")));
                records.add(r);
            }
        }
        return records;
    }

    @Override
    public int UpdatePatient(MiniForm mf, ComprehensiveForm cf) throws Exception {
        int insert = 0;

        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_UpdatePatientInfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, mf.getIDPatient());
            cs.setString(2, mf.getFirstName());
            cs.setString(3, mf.getMiddleName());
            cs.setString(4, mf.getLastName());
            cs.setString(5, String.valueOf(mf.getSex()));
            cs.setDate(6, Parser.ParseUtilDateToSqlDate(mf.getDateOfBirth()));
            cs.setString(7, mf.getComplaint());
            cs.setString(8, mf.getKinFirstName());
            cs.setString(9, mf.getKinMiddleName());
            cs.setString(10, mf.getKinLastName());
            cs.setString(11, mf.getRelationshipToPatient());

            cs.setInt(12, cf.getMiniFormID().getIDPatient());

            cs.setBoolean(13, cf.getPatientAddress().isPresentAddress());
            cs.setBoolean(14, cf.getPatientAddress().isKinAddress());
            cs.setString(15, cf.getPatientAddress().getStreet());
            cs.setInt(16, cf.getPatientAddress().getDoorNo());
            cs.setString(17, cf.getPatientAddress().getArea());
            cs.setInt(18, cf.getPatientAddress().getCity().getIDCity());

            cs.setBoolean(19, cf.getKinAddress().isPresentAddress());
            cs.setBoolean(20, cf.getKinAddress().isKinAddress());
            cs.setString(21, cf.getKinAddress().getStreet());
            cs.setInt(22, cf.getKinAddress().getDoorNo());
            cs.setString(23, cf.getKinAddress().getArea());
            cs.setInt(24, cf.getKinAddress().getCity().getIDCity());

            cs.setString(25, mf.getContactInfo().getTelephone1());
            cs.setString(26, mf.getContactInfo().getTelephone2());
            cs.setString(27, mf.getContactInfo().getTelephoneWork());
            cs.setString(28, mf.getContactInfo().getTelephoneHome());
            cs.setString(29, mf.getContactInfo().getMobile());
            cs.setString(30, mf.getContactInfo().getPager());
            cs.setString(31, mf.getContactInfo().getFax());
            cs.setString(32, mf.getContactInfo().getEmail());

            cs.setString(33, cf.getMaritalStatus());
            cs.setInt(34, cf.getNoOfDependants());
            cs.setInt(35, cf.getHeight());
            cs.setInt(36, cf.getWeight());
            cs.setString(37, cf.getBloodType());
            cs.setString(38, cf.getOccupation());
            cs.setDouble(39, cf.getGrossAnnualIncome());
            cs.setBoolean(40, cf.isVegetarian());
            cs.setBoolean(41, cf.isSmoker());
            cs.setInt(42, cf.getAverageNrCigarettes());
            cs.setBoolean(43, cf.isConsumingAlcohol());
            cs.setInt(44, cf.getAverageNrDrinks());
            cs.setString(45, cf.getUsingStimulants());
            cs.setInt(46, cf.getCoffeeOrTeaPerDay());
            cs.setInt(47, cf.getSoftDrinksPerDay());
            cs.setBoolean(48, cf.isHaveRegularMeals());
            cs.setString(49, cf.getHomeFoodOrOutside());
            cs.setString(50, cf.getComplaint());
            cs.setString(51, cf.getDiabetic());
            cs.setString(52, cf.getHypertensive());
            cs.setString(53, cf.getCardiacCondition());
            cs.setString(54, cf.getRespiratoryCondition());
            cs.setString(55, cf.getDigestiveCondition());
            cs.setString(56, cf.getOrthopedicCondition());
            cs.setString(57, cf.getMuscularCondition());
            cs.setString(58, cf.getNeurologicalCondition());
            cs.setString(59, cf.getKnownAllergies());
            cs.setString(60, cf.getReactionToDrugs());
            cs.setString(61, cf.getMajorSurgeries());

            insert = cs.executeUpdate();
        }
        return insert;
    }

    @Override
    public List<PatientRecord> GetPatientAppointments(int patientID, int doctorID) throws Exception {
        List<PatientRecord> records = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_GetPatientAppointments(?,?)}");
            cs.setInt(1, patientID);
            cs.setInt(2, doctorID);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PatientRecord r = new PatientRecord();
                r.setIDRecord(rs.getInt("IDRecord"));
                r.setAppointmentDate(Parser.ParseSQLDateToUtilDate(rs.getDate("AppointmentDate")));
                r.setFollowUpAppointment(Parser.ParseSQLDateToUtilDate(rs.getDate("FollowUpAppointment")));
                r.setDiagnosis(rs.getString("Diagnosis"));
                records.add(r);
            }
        }
        return records;
    }

    @Override
    public List<PatientRecord> GetPatientsByDoctorID(int doctorID) throws Exception {
        List<PatientRecord> pacijenti = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_GetPatientsByDoctorID(?)}");
            cs.setInt(1, doctorID);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PatientRecord pr = new PatientRecord();
                pr.getPatient().setIDPatient(rs.getInt("IDPatient"));
                pr.getPatient().setFirstName(rs.getString("FirstName"));
                pr.getPatient().setMiddleName(rs.getString("MiddleName"));
                pr.getPatient().setLastName(rs.getString("LastName"));                
                pacijenti.add(pr);
            }
        }
        return pacijenti;
    }

    @Override
    public List<Item> GetPrescribedItemsForPatient(int patientID) throws Exception {
        List<Item> items = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL usp_GetPrescribedItemsForPatient(?)}");
            cs.setInt(1, patientID);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Item i = new Item();
                i.setIDItem(rs.getInt("IDItem"));
                i.getRecord().setAppointmentDate(Parser.ParseSQLDateToUtilDate(rs.getDate("AppointmentDate")));
                i.getRecord().setDiagnosis(rs.getString("Diagnosis"));
                i.setItemDescription(rs.getString("ItemDescription"));
                i.setPrice(rs.getDouble("Price"));
                items.add(i);
            }
        }
        return items;
    }
    
    
}
