package Reports;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.HMO_Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class MissedAppRep implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne( targetEntity = HMO_Manager.class )
    protected HMO_Manager Hmo_Manager;
    @Column(name = "FamilyDoctor")
    private int FamilyDoctorPatientNumber;
    @Column(name = "Pediatrician")
    private int PediatricianPatientNumber;
    @Column(name = "Vaccine_Appointments")
    private int VaccineAppointment;
    @Column(name = "Lab_Test_Appointments")
    private int LabResults;
    @Column(name = "Covid_Test")
    private int CovidTest;
    @Column(name = "Nurse_Care")
    private int NurseCare;
    @Column(name = "Special_Doctor")
    private int Special_Doctor;
    @OneToOne( targetEntity = Clinic.class )
    private Clinic Clinic;

    public MissedAppRep(HMO_Manager hmo_Manager, int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare, Clinic clinic) {
        Hmo_Manager = hmo_Manager;
        FamilyDoctorPatientNumber = familyDoctorPatientNumber;
        PediatricianPatientNumber = pediatricianPatientNumber;
        VaccineAppointment = vaccineAppointment;
        LabResults = labResults;
        CovidTest = covidTest;
        NurseCare = nurseCare;
        Clinic = clinic;
    }

    public MissedAppRep(Clinic clinic,int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare,int specialdoctor) {
        FamilyDoctorPatientNumber = familyDoctorPatientNumber;
        PediatricianPatientNumber = pediatricianPatientNumber;
        VaccineAppointment = vaccineAppointment;
        LabResults = labResults;
        CovidTest = covidTest;
        NurseCare = nurseCare;
        this.Clinic=clinic;
        Special_Doctor= specialdoctor;
    }

    public MissedAppRep(Clinic clinic, HMO_Manager hmo_manager) {
        Hmo_Manager = hmo_manager;
        Clinic = clinic;
        FamilyDoctorPatientNumber=0;
        PediatricianPatientNumber=0;
        VaccineAppointment=0;
        LabResults=0;
        CovidTest=0;
        NurseCare=0;
    }

    public MissedAppRep() {

    }

    public HMO_Manager getHmo_Manager() {
        return Hmo_Manager;
    }

    public void setHmo_Manager(HMO_Manager hmo_Manager) {
        Hmo_Manager = hmo_Manager;
    }

    public Clinic getClinic() {
        return Clinic;
    }

    public void setClinic(Clinic clinic) {
        Clinic = clinic;
    }

    public int getFamilyDoctorPatientNumber() {
        return FamilyDoctorPatientNumber;
    }

    public void setFamilyDoctorPatientNumber(int familyDoctorPatientNumber) {
        FamilyDoctorPatientNumber = familyDoctorPatientNumber;
    }

    public int getPediatricianPatientNumber() {
        return PediatricianPatientNumber;
    }

    public void setPediatricianPatientNumber(int pediatricianPatientNumber) {
        PediatricianPatientNumber = pediatricianPatientNumber;
    }

    public int getVaccineAppointment() {
        return VaccineAppointment;
    }

    public void setVaccineAppointment(int vaccineAppointment) {
        VaccineAppointment = vaccineAppointment;
    }

    public int getLabResults() {
        return LabResults;
    }

    public void setLabResults(int labResults) {
        LabResults = labResults;
    }

    public int getCovidTest() {
        return CovidTest;
    }

    public void setCovidTest(int covidTest) {
        CovidTest = covidTest;
    }

    public int getNurseCare() {
        return NurseCare;
    }

    public void setNurseCare(int nurseCare) {
        NurseCare = nurseCare;
    }
}

