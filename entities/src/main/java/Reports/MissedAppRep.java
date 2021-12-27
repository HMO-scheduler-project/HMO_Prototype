package Reports;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import javax.persistence.*;
import java.io.Serializable;
//need extend WeeklyReport?
@Entity
public class MissedAppRep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num")
    public int ID;
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

    public MissedAppRep(int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare) {
        super();
        FamilyDoctorPatientNumber = familyDoctorPatientNumber;
        PediatricianPatientNumber = pediatricianPatientNumber;
        VaccineAppointment = vaccineAppointment;
        LabResults = labResults;
        CovidTest = covidTest;
        NurseCare = nurseCare;
    }

    public MissedAppRep() {
        super();
        FamilyDoctorPatientNumber=0;
        PediatricianPatientNumber=0;
        VaccineAppointment=0;
        LabResults=0;
        CovidTest=0;
        NurseCare=0;
    }

    public int getID() {
        return ID;
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


