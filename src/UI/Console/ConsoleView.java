/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Console;

import BLL.AdministrationManager;
import BLL.DoctorManager;
import BLL.NursesManager;
import Helper.Parser;
import Model.*;
import ViewModel.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import outpatientmanagementmodel.Outpatient.FormatEnum;
import UI.IView;

/**
 *
 * @author Eugen
 */
public class ConsoleView implements IView {

    NursesManager nursesManager;
    DoctorManager doctorManager;
    AdministrationManager administrationManager;
    Scanner scanner;
    int doctorID;    

    public ConsoleView() {
        nursesManager = new NursesManager();
        doctorManager = new DoctorManager();
        administrationManager = new AdministrationManager();
        scanner = new Scanner(System.in);
        //scanner.useDelimiter("\\n");
    }

    
    private static void GenerateMenu() {
        System.out.println("MAIN MENU");
        System.out.println("------------------------------");
        System.out.println("[1] Administration console");
        System.out.println("[2] Nurses console");
        System.out.println("[3] Doctors console");
        System.out.println("[4] Receipt");
        System.out.println("[5] Exit");
        System.out.println("------------------------------");
    }

    private static void GenerateAdministrationMenu() {
        System.out.println("Select option:");
        System.out.println("------------------------------");
        System.out.println("[1] Show all doctors");
        System.out.println("[2] Add new doctor");
        System.out.println("[3] Edit doctor");
        System.out.println("[4] Delete doctor");
        System.out.println("[5] Return to main menu");
        System.out.println("------------------------------");
    }

    private static void GenerateNursesMenu() {
        System.out.println("Select option:");
        System.out.println("------------------------------");
        System.out.println("[1] Enter emergency patient");
        System.out.println("[2] Comprehensive patient form");
        System.out.println("[3] Edit patient");
        System.out.println("[4] Return to main menu");
        System.out.println("------------------------------");
    }

    private static void GenerateComprehensiveFormMenu() {
        System.out.println("Select option:");
        System.out.println("------------------------------");
        System.out.println("[1] New comprehensive patient form");
        System.out.println("[2] Finish comprehensive form for existing patient");
        System.out.println("------------------------------");
    }

    private static void GenerateDoctorsMenu() {
        System.out.println("Select option:");
        System.out.println("------------------------------");
        System.out.println("[1] Get patient records");
        System.out.println("[2] Create new record");
        System.out.println("[3] Review appointment history");
        System.out.println("[4] Review treatments, lab results and medications");
        System.out.println("[5] Return to main menu");
        System.out.println("------------------------------");
    }

    
    @Override
    public void Start() {

        String input;

        try {
            do {
                GenerateMenu();

                input = scanner.nextLine();

                switch (input) {
                    case "1":
                        AdministrationMenu();
                        break;
                    case "2":
                        NursesMenu();
                        break;
                    case "3":
                        DoctorsMenu();
                    case "4":
                        System.exit(0);
                    case "5":
                        System.exit(0);
                    default:
                        System.out.println("You didn't select viable option. Try again.");
                }
            } while (!"1".equals(input) || !"2".equals(input) || !"3".equals(input) || !"4".equals(input) || !"5".equals(input));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void AdministrationMenu() {

        String input;
        boolean correct = Boolean.TRUE;
        do {
            try {
                do {
                    GenerateAdministrationMenu();

                    input = scanner.nextLine();

                    switch (input) {
                        case "1":
                            GetAllDoctors();
                            System.out.println("\n");
                            System.out.println("\n");
                            AdministrationMenu();
                            break;
                        case "2":
                            InsertDoctor();
                            System.out.println("\n");
                            System.out.println("\n");
                            AdministrationMenu();
                            break;
                        case "3":
                            UpdateDoctor();
                            System.out.println("\n");
                            System.out.println("\n");
                            AdministrationMenu();
                            break;
                        case "4":
                            DeleteDoctor();
                            System.out.println("\n");
                            System.out.println("\n");
                            AdministrationMenu();
                        case "5":
                            Start();
                        default:
                            System.out.println("You didn't select viable option. Try again.");
                    }
                } while (!"1".equals(input) || !"2".equals(input) || !"3".equals(input) || !"4".equals(input) || !"5".equals(input));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    public void NursesMenu() {

        String input;
        boolean correct = Boolean.TRUE;

        do {
            try {
                do {
                    GenerateNursesMenu();

                    input = scanner.nextLine();

                    switch (input) {
                        case "1":
                            MiniForm();
                            System.out.println("\n");
                            System.out.println("\n");
                            NursesMenu();
                            break;
                        case "2":
                            GenerateComprehensiveFormMenu();
                            String answer = scanner.nextLine();
                            switch (answer) {
                                case "1":
                                    CompletePatientInfo();
                                    System.out.println("\n");
                                    System.out.println("\n");
                                    NursesMenu();
                                    break;
                                case "2":
                                    ComprehensiveForm();
                                    System.out.println("\n");
                                    System.out.println("\n");
                                    NursesMenu();
                            }
                            break;
                        case "3":
                            UpdatePatient();
                            System.out.println("\n");
                            System.out.println("\n");
                            NursesMenu();
                            break;
                        case "4":
                            Start();
                        default:
                            System.out.println("You didn't select viable option. Try again.");
                    }
                } while (!"1".equals(input) || !"2".equals(input) || !"3".equals(input) || !"4".equals(input));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    public void DoctorsMenu() {
        String input;
        boolean correct = Boolean.TRUE;

        do {
            if (doctorID == 0) {
                try {

                    System.out.println("Enter doctor ID:");
                    GetAllDoctors();
                    doctorID = Integer.parseInt(scanner.nextLine());
                    System.out.println("\n");
                    System.out.println("Welcome doctor " + GetDoctor(doctorID).DoctorNameSurname() + "!");
                    GenerateDoctorsMenu();

                    input = scanner.nextLine();

                    switch (input) {
                        case "1":
                            GetPatientRecords();
                            System.out.println("\n");
                            System.out.println("\n");
                            DoctorsMenu();
                            break;
                        case "2":
                            CreateNewPatientRecord();
                            System.out.println("\n");
                            System.out.println("\n");
                            DoctorsMenu();

                            break;
                        case "3":
                            GetPatientAppointments();
                            System.out.println("\n");
                            System.out.println("\n");
                            DoctorsMenu();
                            break;
                        case "4":
                            GetPrescribedItemsForPatient();
                            System.out.println("\n");
                            System.out.println("\n");
                            DoctorsMenu();
                        case "5":
                            Start();
                        default:
                            System.out.println("You didn't select viable option. Try again.");
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage() + "\nTry again.");
                    DoctorsMenu();
                }
            } else {
                try {
                    do {

                        System.out.println("Welcome doctor " + GetDoctor(doctorID).DoctorNameSurname() + "!");
                        System.out.println("\n");
                        GenerateDoctorsMenu();

                        input = scanner.nextLine();

                        switch (input) {
                            case "1":
                                GetPatientRecords();
                                System.out.println("\n");
                                System.out.println("\n");
                                DoctorsMenu();
                                break;
                            case "2":
                                CreateNewPatientRecord();
                                System.out.println("\n");
                                System.out.println("\n");
                                DoctorsMenu();

                                break;
                            case "3":
                                GetPatientAppointments();
                                System.out.println("\n");
                                System.out.println("\n");
                                DoctorsMenu();
                                break;
                            case "4":
                                GetPrescribedItemsForPatient();
                                System.out.println("\n");
                                System.out.println("\n");
                                DoctorsMenu();
                            case "5":
                                doctorID = 0;
                                Start();
                            default:
                                System.out.println("You didn't select viable option. Try again.");
                        }
                    } while (!"1".equals(input) || !"2".equals(input) || !"3".equals(input) || !"4".equals(input) || !"5".equals(input));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage() + "\nTry again.");
                    correct = Boolean.FALSE;
                }
            }
        } while (correct == Boolean.FALSE);
    }

    
    public void GetAllDoctors() {
        try {
            List<Doctor> doktori = administrationManager.GetAllDoctors();
            for (Doctor doctor : doktori) {
                System.out.println(doctor.SimpleDoctorFormat());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Doctor GetDoctor(int doctorID) {
        List<Doctor> doktori = new ArrayList<>();
        try {
            doktori = administrationManager.GetAllDoctors();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return doktori.get(doctorID - 1);
    }

    
    public void InsertDoctor() {
        Doctor d = null;
        boolean correct = Boolean.TRUE;

        do {
            try {
                d = new Doctor();
                System.out.println("Enter name:");
                d.setName(scanner.nextLine());
                System.out.println("Enter surname:");
                d.setSurname(scanner.nextLine());
                System.out.println("Enter telephone:");
                d.setTelephone(scanner.nextLine());
                System.out.println("Enter email:");
                d.setEmail(scanner.nextLine());
                System.out.println("Is doctor specialist (1-Yes / 0-No):");
                d.setSpecialist(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter city:");
                GetCities();
                d.setCityID(Integer.parseInt(scanner.nextLine()));

                if (administrationManager.InsertDoctor(d) != 0) {
                    System.out.println("New doctor successfully added.");
                }

            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }

        } while (correct == Boolean.FALSE);
    }

    
    public void GetCities() {
        try {
            int i = 1;
            for (City c : administrationManager.GetCities()) {
                System.out.println("[" + i + "]" + c.getName());
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public void DeleteDoctor() {
        boolean correct = Boolean.TRUE;
        do {
            try {
                System.out.println("Choose ID of doctor you want to delete:");
                GetAllDoctors();
                if (administrationManager.DeleteDoctor(Integer.parseInt(scanner.nextLine())) != 0) {
                    System.out.println("Doctor successfully deleted.");
                }
            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Missing doctor. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    
    public void UpdateDoctor() {
        boolean correct = Boolean.TRUE;
        Doctor d = null;

        do {
            try {
                d = new Doctor();
                System.out.println("Choose ID of doctor you want to update:");
                GetAllDoctors();
                d.setIDDoctor(Integer.parseInt(scanner.nextLine()));
                System.out.println("\n");
                System.out.println("Enter name:");
                d.setName(scanner.nextLine());
                System.out.println("Enter surname:");
                d.setSurname(scanner.nextLine());
                System.out.println("Enter telephone:");
                d.setTelephone(scanner.nextLine());
                System.out.println("Enter email:");
                d.setEmail(scanner.nextLine());
                System.out.println("Is doctor specialist (1-Yes / 0-No):");
                d.setSpecialist(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter city:");
                GetCities();
                d.setCityID(Integer.parseInt(scanner.nextLine()));

                if (administrationManager.UpdateDoctor(d) != 0) {
                    System.out.println("Doctor successfully updated.");
                }
            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Error updating new doctor. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    
    public void MiniForm() {
        boolean correct = Boolean.TRUE;
        MiniForm form;

        do {
            try {
                form = new MiniForm();
                System.out.println("Enter first name:");
                form.setFirstName(scanner.nextLine());
                System.out.println("Enter middle name:");
                form.setMiddleName(scanner.nextLine());
                System.out.println("Enter last name:");
                form.setLastName(scanner.nextLine());
                System.out.println("Enter sex (M-male/F-female):");
                form.setSex((scanner.nextLine()).charAt(0));
                do {
                    try {
                        System.out.println("Date of birth (format: yyyy/MM/dd):");
                        form.setDateOfBirth(Parser.ParseStringToDate(scanner.nextLine()));
                    } catch (ParseException ex) {
                        System.out.println("Date in wrong format. Try again.");
                    }
                } while (form.getDateOfBirth() == null);

                System.out.println("Enter patient complaint:");
                form.setComplaint(scanner.nextLine());
                System.out.println("Enter kin first name:");
                form.setKinFirstName(scanner.nextLine());
                System.out.println("Enter kin middle name:");
                form.setKinMiddleName(scanner.nextLine());
                System.out.println("Enter kin last name:");
                form.setKinLastName(scanner.nextLine());
                System.out.println("Relationship to patient:");
                form.setRelationshipToPatient(scanner.nextLine());

                ContactInfo ci = new ContactInfo();
                System.out.println("Enter first telephone:");
                ci.setTelephone1(scanner.nextLine());
                System.out.println("Enter second telephone:");
                ci.setTelephone2(scanner.nextLine());
                form.setContactInfo(ci);

                if (nursesManager.MiniForm(form) != 0) {
                    System.out.println("Patient added. Ready for doctors exam.");
                }
            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Error adding new patient. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);

    }

    
    public void ComprehensiveForm() {
        boolean correct = Boolean.TRUE;
        ComprehensiveForm form = null;

        do {
            try {
                form = new ComprehensiveForm();
                MiniForm mf = new MiniForm();
                System.out.println("Choose ID of a patient:");
                GetPatientInfo();
                mf.setIDPatient(Integer.parseInt(scanner.nextLine()));
                form.setMiniFormID(mf);
                System.out.println("\n");

                Address pa = new Address();
                City c = new City();
                System.out.println("PATIENT CONTACT DETAILS");
                pa.setPresentAddress(Boolean.TRUE);
                pa.setKinAddress(Boolean.FALSE);
                System.out.println("Enter street:");
                pa.setStreet(scanner.nextLine());
                System.out.println("Enter door number:");
                pa.setDoorNo(Integer.parseInt(scanner.nextLine()));
                System.out.println("Area:");
                pa.setArea(scanner.nextLine());
                System.out.println("City:");
                GetCities();
                c.setIDCity(Integer.parseInt(scanner.nextLine()));
                pa.setCity(c);
                form.setPatientAddress(pa);

                Address kin = new Address();
                System.out.println("\nKIN CONTACT DETAILS");
                kin.setPresentAddress(Boolean.FALSE);
                kin.setKinAddress(Boolean.TRUE);
                System.out.println("Enter street:");
                kin.setStreet(scanner.nextLine());
                System.out.println("Enter door number:");
                kin.setDoorNo(Integer.parseInt(scanner.nextLine()));
                System.out.println("Area:");
                kin.setArea(scanner.nextLine());
                System.out.println("City:");
                GetCities();
                c.setIDCity(Integer.parseInt(scanner.nextLine()));
                pa.setCity(c);
                kin.setCity(c);
                form.setKinAddress(kin);

                System.out.println("\nTELEPHONES");
                ContactInfo ci = new ContactInfo();
                System.out.println("Enter work telephone:");
                ci.setTelephoneWork(scanner.nextLine());
                System.out.println("Enter home telephone:");
                ci.setTelephoneHome(scanner.nextLine());
                System.out.println("Enter mobile phone:");
                ci.setMobile(scanner.nextLine());
                System.out.println("Enter pager:");
                ci.setPager(scanner.nextLine());
                System.out.println("Enter fax:");
                ci.setFax(scanner.nextLine());
                System.out.println("Enter email:");
                ci.setEmail(scanner.nextLine());
                form.setContactInfo(ci);

                System.out.println("\nPERSONAL DETAILS");
                System.out.println("Enter marital status:");
                form.setMaritalStatus(scanner.nextLine());
                System.out.println("Enter number of dependants:");
                form.setNoOfDependants(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter height:");
                form.setHeight(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter weight:");
                form.setWeight(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter blood type:");
                form.setBloodType(scanner.nextLine());

                System.out.println("\nPROFFESION DETAILS");
                System.out.println("Enter occupation:");
                form.setOccupation(scanner.nextLine());
                System.out.println("Enter gross annual income:");
                form.setGrossAnnualIncome(Double.parseDouble(scanner.nextLine()));

                System.out.println("\nLIFESTYLE");
                System.out.println("Is patient vegetarian (1-Yes / 0-No):");
                form.setVegetarian(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Is patient smoker (1-Yes / 0-No):");
                form.setSmoker(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter average number of cigrattes per day:");
                form.setAverageNrCigarettes(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient consuming alcohol (1-Yes / 0-No):");
                form.setConsumingAlcohol(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter average number of alcoholic drinks per day:");
                form.setAverageNrDrinks(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient using stimulants (specify):");
                form.setUsingStimulants(scanner.nextLine());
                System.out.println("Consumption of coffee/tea per day:");
                form.setCoffeeOrTeaPerDay(Integer.parseInt(scanner.nextLine()));
                System.out.println("Consumption of soft drinks per day:");
                form.setSoftDrinksPerDay(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient having regular meals - breakfast,lunch,dinner (1-Yes / 0-No):");
                form.setHaveRegularMeals(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Does patient eat home food or outside predominantly:");
                form.setHomeFoodOrOutside(scanner.nextLine());

                System.out.println("\nBASIC COMPLAINTS");
                System.out.println("Enter patient complaint:");
                form.setComplaint(scanner.nextLine());
                System.out.println("Diabetic:");
                form.setDiabetic(scanner.nextLine());
                System.out.println("Hypertensive:");
                form.setHypertensive(scanner.nextLine());
                System.out.println("Cardiac condition:");
                form.setCardiacCondition(scanner.nextLine());
                System.out.println("Respiratory condition:");
                form.setRespiratoryCondition(scanner.nextLine());
                System.out.println("Digestive condition:");
                form.setDigestiveCondition(scanner.nextLine());
                System.out.println("Orthopedic condition:");
                form.setOrthopedicCondition(scanner.nextLine());
                System.out.println("Muscular condition:");
                form.setMuscularCondition(scanner.nextLine());
                System.out.println("Neurological condition:");
                form.setNeurologicalCondition(scanner.nextLine());
                System.out.println("Known allergies:");
                form.setKnownAllergies(scanner.nextLine());
                System.out.println("Known adverse reaction to specific drugs:");
                form.setReactionToDrugs(scanner.nextLine());
                System.out.println("Major surgeries:");
                form.setMajorSurgeries(scanner.nextLine());

                if (nursesManager.ComprehensiveForm(form) != 0) {
                    System.out.println("Comprehensive form finished. Patient added.");
                }
            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Error filling comprehensive form. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);

    }

    
    public void GetPatientInfo() {
        try {
            List<MiniForm> pacijenti = nursesManager.GetPatientInfo();
            for (MiniForm pacijent : pacijenti) {
                System.out.println(pacijent);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public void CompletePatientInfo() {
        boolean correct = Boolean.TRUE;
        MiniForm mini;
        ComprehensiveForm comp;

        do {
            try {
                mini = new MiniForm();
                comp = new ComprehensiveForm();
                System.out.println("Enter first name:");
                mini.setFirstName(scanner.nextLine());
                System.out.println("Enter middle name:");
                mini.setMiddleName(scanner.nextLine());
                System.out.println("Enter last name:");
                mini.setLastName(scanner.nextLine());
                System.out.println("Enter sex (M-male/F-female):");
                mini.setSex((scanner.nextLine()).charAt(0));
                do {
                    try {
                        System.out.println("Date of birth (format: yyyy/MM/dd):");
                        mini.setDateOfBirth(Parser.ParseStringToDate(scanner.nextLine()));
                    } catch (ParseException ex) {
                        System.out.println("Date in wrong format. Try again.");
                    }
                } while (mini.getDateOfBirth() == null);

                System.out.println("Enter patient complaint:");
                mini.setComplaint(scanner.nextLine());
                System.out.println("Enter kin first name:");
                mini.setKinFirstName(scanner.nextLine());
                System.out.println("Enter kin middle name:");
                mini.setKinMiddleName(scanner.nextLine());
                System.out.println("Enter kin last name:");
                mini.setKinLastName(scanner.nextLine());
                System.out.println("Relationship to patient:");
                mini.setRelationshipToPatient(scanner.nextLine());

                comp.setMiniFormID(mini);

                Address pa = new Address();
                City c = new City();
                System.out.println("PATIENT CONTACT DETAILS");
                pa.setPresentAddress(Boolean.TRUE);
                pa.setKinAddress(Boolean.FALSE);
                System.out.println("Enter street:");
                pa.setStreet(scanner.nextLine());
                System.out.println("Enter door number:");
                pa.setDoorNo(Integer.parseInt(scanner.nextLine()));
                System.out.println("Area:");
                pa.setArea(scanner.nextLine());
                System.out.println("City:");
                GetCities();
                c.setIDCity(Integer.parseInt(scanner.nextLine()));
                pa.setCity(c);
                pa.setCity(c);
                comp.setPatientAddress(pa);

                Address kin = new Address();
                System.out.println("\nKIN CONTACT DETAILS");
                kin.setPresentAddress(Boolean.FALSE);
                kin.setKinAddress(Boolean.TRUE);
                System.out.println("Enter street:");
                kin.setStreet(scanner.nextLine());
                System.out.println("Enter door number:");
                kin.setDoorNo(Integer.parseInt(scanner.nextLine()));
                System.out.println("Area:");
                kin.setArea(scanner.nextLine());
                System.out.println("City:");
                GetCities();
                c.setIDCity(Integer.parseInt(scanner.nextLine()));
                kin.setCity(c);
                kin.setCity(c);
                comp.setKinAddress(kin);

                ContactInfo ci = new ContactInfo();
                System.out.println("\nTELEPHONES");
                System.out.println("Enter first telephone:");
                ci.setTelephone1(scanner.nextLine());
                System.out.println("Enter second telephone:");
                ci.setTelephone2(scanner.nextLine());
                System.out.println("Enter work telephone:");
                ci.setTelephoneWork(scanner.nextLine());
                System.out.println("Enter home telephone:");
                ci.setTelephoneHome(scanner.nextLine());
                System.out.println("Enter mobile phone:");
                ci.setMobile(scanner.nextLine());
                System.out.println("Enter pager:");
                ci.setPager(scanner.nextLine());
                System.out.println("Enter fax:");
                ci.setFax(scanner.nextLine());
                System.out.println("Enter email:");
                ci.setEmail(scanner.nextLine());
                comp.setContactInfo(ci);

                System.out.println("\n");

                System.out.println("PERSONAL DETAILS");
                System.out.println("Enter marital status:");
                comp.setMaritalStatus(scanner.nextLine());
                System.out.println("Enter number of dependants:");
                comp.setNoOfDependants(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter height:");
                comp.setHeight(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter weight:");
                comp.setWeight(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter blood type:");
                comp.setBloodType(scanner.nextLine());

                System.out.println("\nPROFESSION DETAILS");
                System.out.println("Enter occupation:");
                comp.setOccupation(scanner.nextLine());
                System.out.println("Enter gross annual income:");
                comp.setGrossAnnualIncome(Double.parseDouble(scanner.nextLine()));

                System.out.println("\nLIFESTYLE");
                System.out.println("Is patient vegetarian (1-Yes / 0-No):");
                comp.setVegetarian(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Is patient smoker (1-Yes / 0-No):");
                comp.setSmoker(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter average number of cigarattes per day:");
                comp.setAverageNrCigarettes(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient consuming alcohol (1-Yes / 0-No):");
                comp.setConsumingAlcohol(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter average number of alcoholic drinks per day:");
                comp.setAverageNrDrinks(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient using stimulants (specify):");
                comp.setUsingStimulants(scanner.nextLine());
                System.out.println("Consumption of coffee/tea per day:");
                comp.setCoffeeOrTeaPerDay(Integer.parseInt(scanner.nextLine()));
                System.out.println("Consumption of soft drinks per day:");
                comp.setSoftDrinksPerDay(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient having regular meals - breakfast,lunch.dinner (1-Yes / 0-No):");
                comp.setHaveRegularMeals(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Does patient eat home food or outside predominantly:");
                comp.setHomeFoodOrOutside(scanner.nextLine());

                System.out.println("\nBASIC COMPLAINTS");
                System.out.println("Enter patient complaint:");
                comp.setComplaint(scanner.nextLine());
                System.out.println("Diabetic:");
                comp.setDiabetic(scanner.nextLine());
                System.out.println("Hypertensive:");
                comp.setHypertensive(scanner.nextLine());
                System.out.println("Cardiac condition:");
                comp.setCardiacCondition(scanner.nextLine());
                System.out.println("Respiratory condition:");
                comp.setRespiratoryCondition(scanner.nextLine());
                System.out.println("Digestive condition:");
                comp.setDigestiveCondition(scanner.nextLine());
                System.out.println("Orthopedic condition:");
                comp.setOrthopedicCondition(scanner.nextLine());
                System.out.println("Muscular condition:");
                comp.setMuscularCondition(scanner.nextLine());
                System.out.println("Neurological condition:");
                comp.setNeurologicalCondition(scanner.nextLine());
                System.out.println("Known allergies:");
                comp.setKnownAllergies(scanner.nextLine());
                System.out.println("Known adverse reaction to specific drugs:");
                comp.setReactionToDrugs(scanner.nextLine());
                System.out.println("Major surgeries:");
                comp.setMajorSurgeries(scanner.nextLine());

                if (nursesManager.CompletePatientInfo(mini, comp) != 0) {
                    System.out.println("Patient added.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Error adding new patient. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    
    public void CreateNewPatientRecord() {
        boolean correct = Boolean.TRUE;
        PatientRecord r;

        do {
            try {
                r = new PatientRecord();
                MiniForm mf = new MiniForm();

                int answer;
                boolean valid = Boolean.TRUE;
                do {
                    System.out.println("Enter patient ID:");
                    GetPatientInfo();
                    mf.setIDPatient(Integer.parseInt(scanner.nextLine()));
                    r.setPatient(mf);
                    System.out.println("\n");

                    r.setDoctor(new Doctor(doctorID));

                    System.out.println("Enter diagnosis:");
                    r.setDiagnosis(scanner.nextLine());

                    System.out.println("Do you want to enter follow up appointment? (1-Yes/0-No)");
                    answer = Integer.parseInt(scanner.nextLine());
                    if (answer == 1) {
                        do {
                            try {
                                System.out.println("Follow up appointment (format: yyyy/MM/dd):");
                                r.setFollowUpAppointment(Parser.ParseStringToDate(scanner.nextLine()));
                            } catch (ParseException ex) {
                                System.out.println("Date in wrong format. Try again.");
                            }
                        } while (r.getFollowUpAppointment() == null);
                    } else {
                        r.setFollowUpAppointment(null);
                    }

                    if (doctorManager.CreateNewPatientRecord(r) != 0) {
                        System.out.println("New record created.");
                    } else {
                        System.out.println("Error creating new record. Try again.");
                        valid = Boolean.FALSE;
                    }
                } while (valid == Boolean.FALSE);

                System.out.println("\n");
                System.out.println("Do you want to prescribe treatment, lab test or medication? (1-Yes/0-No)");
                answer = Integer.parseInt(scanner.nextLine());

                Item item = new Item();

                if (answer == 1) {
                    boolean valiD = Boolean.TRUE;
                    do {
                        System.out.println("\n");
                        GetItems();
                        System.out.println("\n");
                        System.out.println("Enter ID of item you wish to prescribe:");
                        item.setIDItem(Integer.parseInt(scanner.nextLine()));
                        System.out.println("\n");
                        if (doctorManager.PrescribeItem(item) != 0) {
                            System.out.println("Item successfully prescribed.");
                        } else {
                            System.out.println("Error prescribing item. Try again.");
                            valiD = Boolean.FALSE;
                        }
                    } while (valiD == Boolean.FALSE);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Error precribing item. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    
    public void GetItems() {
        try {
            List<Item> items = doctorManager.GetItems();
            for (Item item : items) {
                System.out.println(item);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public void UpdatePatient() {
        boolean correct = Boolean.TRUE;
        MiniForm mini = null;
        ComprehensiveForm comp = null;

        do {
            try {
                mini = new MiniForm();
                comp = new ComprehensiveForm();
                System.out.println("\n");
                System.out.println("Enter ID of patient you want to edit:");
                GetPatientInfo();
                mini.setIDPatient(Integer.parseInt(scanner.nextLine()));
                System.out.println("-------------------------------------");
                System.out.println("Enter first name:");
                mini.setFirstName(scanner.nextLine());
                System.out.println("Enter middle name:");
                mini.setMiddleName(scanner.nextLine());
                System.out.println("Enter last name:");
                mini.setLastName(scanner.nextLine());
                System.out.println("Enter sex (M-male/F-female):");
                mini.setSex((scanner.nextLine()).charAt(0));
                do {
                    try {
                        System.out.println("Date of birth (format: yyyy/MM/dd):");
                        mini.setDateOfBirth(Parser.ParseStringToDate(scanner.nextLine()));
                    } catch (ParseException ex) {
                        System.out.println("Date in wrong format. Try again.");
                    }
                } while (mini.getDateOfBirth() == null);

                System.out.println("Enter patient complaint:");
                mini.setComplaint(scanner.nextLine());
                System.out.println("Enter kin first name:");
                mini.setKinFirstName(scanner.nextLine());
                System.out.println("Enter kin middle name:");
                mini.setKinMiddleName(scanner.nextLine());
                System.out.println("Enter kin last name:");
                mini.setKinLastName(scanner.nextLine());
                System.out.println("Relationship to patient:");
                mini.setRelationshipToPatient(scanner.nextLine());

                comp.setMiniFormID(mini);

                Address pa = new Address();
                City c = new City();
                System.out.println("PATIENT CONTACT DETAILS");
                pa.setPresentAddress(Boolean.TRUE);
                pa.setKinAddress(Boolean.FALSE);
                System.out.println("Enter street:");
                pa.setStreet(scanner.nextLine());
                System.out.println("Enter door number:");
                pa.setDoorNo(Integer.parseInt(scanner.nextLine()));
                System.out.println("Area:");
                pa.setArea(scanner.nextLine());
                System.out.println("City:");
                GetCities();
                c.setIDCity(Integer.parseInt(scanner.nextLine()));
                pa.setCity(c);
                pa.setCity(c);
                comp.setPatientAddress(pa);

                Address kin = new Address();
                System.out.println("\nKIN CONTACT DETAILS");
                kin.setPresentAddress(Boolean.FALSE);
                kin.setKinAddress(Boolean.TRUE);
                System.out.println("Enter street:");
                kin.setStreet(scanner.nextLine());
                System.out.println("Enter door number:");
                kin.setDoorNo(Integer.parseInt(scanner.nextLine()));
                System.out.println("Area:");
                kin.setArea(scanner.nextLine());
                System.out.println("City:");
                GetCities();
                c.setIDCity(Integer.parseInt(scanner.nextLine()));
                kin.setCity(c);
                kin.setCity(c);
                comp.setKinAddress(kin);

                ContactInfo ci = new ContactInfo();
                System.out.println("\nTELEPHONES");
                System.out.println("Enter first telephone:");
                ci.setTelephone1(scanner.nextLine());
                System.out.println("Enter second telephone:");
                ci.setTelephone2(scanner.nextLine());
                System.out.println("Enter work telephone:");
                ci.setTelephoneWork(scanner.nextLine());
                System.out.println("Enter home telephone:");
                ci.setTelephoneHome(scanner.nextLine());
                System.out.println("Enter mobile phone:");
                ci.setMobile(scanner.nextLine());
                System.out.println("Enter pager:");
                ci.setPager(scanner.nextLine());
                System.out.println("Enter fax:");
                ci.setFax(scanner.nextLine());
                System.out.println("Enter email:");
                ci.setEmail(scanner.nextLine());
                comp.setContactInfo(ci);

                System.out.println("\n");

                System.out.println("PERSONAL DETAILS");
                System.out.println("Enter marital status:");
                comp.setMaritalStatus(scanner.nextLine());
                System.out.println("Enter number of dependants:");
                comp.setNoOfDependants(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter height:");
                comp.setHeight(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter weight:");
                comp.setWeight(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter blood type:");
                comp.setBloodType(scanner.nextLine());

                System.out.println("\nPROFFESION DETAILS");
                System.out.println("Enter occupation:");
                comp.setOccupation(scanner.nextLine());
                System.out.println("Enter gross annual income:");
                comp.setGrossAnnualIncome(Double.parseDouble(scanner.nextLine()));

                System.out.println("\nLIFESTYLE");
                System.out.println("Is patient vegetarian (1-Yes / 0-No):");
                comp.setVegetarian(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Is patient smoker (1-Yes / 0-No):");
                comp.setSmoker(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter average number of cigrattes per day:");
                comp.setAverageNrCigarettes(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient consuming alcohol (1-Yes / 0-No):");
                comp.setConsumingAlcohol(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Enter average number of alcoholic drinks per day:");
                comp.setAverageNrDrinks(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient using stimulants (specify):");
                comp.setUsingStimulants(scanner.nextLine());
                System.out.println("Consumption of coffee/tea per day:");
                comp.setCoffeeOrTeaPerDay(Integer.parseInt(scanner.nextLine()));
                System.out.println("Consumption of soft drinks per day:");
                comp.setSoftDrinksPerDay(Integer.parseInt(scanner.nextLine()));
                System.out.println("Is patient having regular meals - breakfast,lunch.dinner (1-Yes / 0-No):");
                comp.setHaveRegularMeals(Integer.parseInt(scanner.nextLine()) == 1 ? Boolean.TRUE : Boolean.FALSE);
                System.out.println("Does patient eat home food or outside predominantly:");
                comp.setHomeFoodOrOutside(scanner.nextLine());

                System.out.println("\nBASIC COMPLAINTS");
                System.out.println("Enter patient complaint:");
                comp.setComplaint(scanner.nextLine());
                System.out.println("Diabetic:");
                comp.setDiabetic(scanner.nextLine());
                System.out.println("Hypertensive:");
                comp.setHypertensive(scanner.nextLine());
                System.out.println("Cardiac condition:");
                comp.setCardiacCondition(scanner.nextLine());
                System.out.println("Respiratory condition:");
                comp.setRespiratoryCondition(scanner.nextLine());
                System.out.println("Digestive condition:");
                comp.setDigestiveCondition(scanner.nextLine());
                System.out.println("Orthopedic condition:");
                comp.setOrthopedicCondition(scanner.nextLine());
                System.out.println("Muscular condition:");
                comp.setMuscularCondition(scanner.nextLine());
                System.out.println("Neurological condition:");
                comp.setNeurologicalCondition(scanner.nextLine());
                System.out.println("Known allergies:");
                comp.setKnownAllergies(scanner.nextLine());
                System.out.println("Known adverse reaction to specific drugs:");
                comp.setReactionToDrugs(scanner.nextLine());
                System.out.println("Major surgeries:");
                comp.setMajorSurgeries(scanner.nextLine());

                if (nursesManager.CompletePatientInfo(mini, comp) != 0) {
                    System.out.println("Patient added.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (ParseException e) {
                System.out.println("Incorrect format. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            } catch (Exception e) {
                System.out.println("Error adding new patient. Error: " + e.getMessage() + "\nTry again.");
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    
    public void GetPatientRecords() {
        try {
            List<PatientRecord> records = doctorManager.GetPatientRecords(doctorID);
            System.out.println("ID\tFull name\tAppointment date");
            System.out.println("------------------------------------");
            for (PatientRecord record : records) {
                System.out.println(record);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void GetPatientAppointments() {
        boolean correct = Boolean.TRUE;
        int patientID = 0;

        do {
            try {
                System.out.println("\n");
                System.out.println("Enter patient ID:");
                GetPatientsByDoctorID(FormatEnum.SIMPLE_PATIENT_FORMAT);
                patientID = Integer.parseInt(scanner.nextLine());

                System.out.println("\n");
                System.out.println("ID\tAppointment|\tFollow up|\tDiagnosis");
                System.out.println("----------------------------------------------------");
                List<PatientRecord> records = doctorManager.GetPatientAppointments(patientID, doctorID);
                for (PatientRecord record : records) {
                    System.out.println(record.AppointmentsFormat());
                }
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
                correct = Boolean.FALSE;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

    
    public void GetPatientsByDoctorID(FormatEnum format) {
        try {
            List<PatientRecord> patients = doctorManager.GetPatientsByDoctorID(doctorID);
            for (PatientRecord patient : patients) {
                switch (format) {
                    case DEFAULT:
                        System.out.println(patient);
                        break;
                    case APPOINTMENT_FORMAT:
                        System.out.println(patient.AppointmentsFormat());
                        break;
                    case SIMPLE_PATIENT_FORMAT:
                        System.out.println(patient.SimplePatientFormat());
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void GetPrescribedItemsForPatient() {
        boolean correct = Boolean.TRUE;
        int patientID = 0;

        do {
            try {
                System.out.println("Enter patient ID:");
                GetPatientsByDoctorID(FormatEnum.SIMPLE_PATIENT_FORMAT);
                patientID = Integer.parseInt(scanner.nextLine());

                System.out.println("\n");
                System.out.println("Items");
                System.out.println("-------------------------------------");
                List<Item> items = doctorManager.GetPrescribedItemsForPatient(patientID);
                for (Item item : items) {
                    System.out.println(item.PrescribedItemInfo());
                }
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
                correct = Boolean.FALSE;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                correct = Boolean.FALSE;
            }
        } while (correct == Boolean.FALSE);
    }

}
