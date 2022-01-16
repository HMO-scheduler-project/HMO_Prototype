package il.cshaifasweng.OCSFMediatorExample.entities;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.HMO_Manager;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;


//save last week we need to do that
//save at the server last week and start a new one


@Entity
public class ServicesTypeRep implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @ManyToOne(targetEntity = HMO_Manager.class)
    protected HMO_Manager Hmo_Manager;
    @Column(name = "Day")
    private String DayOfTheWeek;
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
    //Do We need those one to one clinic class we ahve them on weekly report
    @OneToOne(targetEntity = Clinic.class)
    private Clinic Clinic;

    public int getSpecial_Doctor() {
        return Special_Doctor;
    }

    public void setSpecial_Doctor(int special_Doctor) {
        Special_Doctor = special_Doctor;
    }

    public ServicesTypeRep(String dayOfTheWeek, int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare,int special_doctor, Clinic clinic) {
        DayOfTheWeek = dayOfTheWeek;
        FamilyDoctorPatientNumber = familyDoctorPatientNumber;
        PediatricianPatientNumber = pediatricianPatientNumber;
        VaccineAppointment = vaccineAppointment;
        LabResults = labResults;
        CovidTest = covidTest;
        NurseCare = nurseCare;
        Clinic = clinic;
        Special_Doctor=special_doctor;
    }

    public ServicesTypeRep(String dayOfTheWeek, int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare) {
        DayOfTheWeek = dayOfTheWeek;
        FamilyDoctorPatientNumber = familyDoctorPatientNumber;
        PediatricianPatientNumber = pediatricianPatientNumber;
        VaccineAppointment = vaccineAppointment;
        LabResults = labResults;
        CovidTest = covidTest;
        NurseCare = nurseCare;
    }

    public ServicesTypeRep(HMO_Manager hmo_Manager, String dayOfTheWeek, int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare,int special_doctor, Clinic clinic) {
        Hmo_Manager = hmo_Manager;
        DayOfTheWeek = dayOfTheWeek;
        FamilyDoctorPatientNumber = familyDoctorPatientNumber;
        PediatricianPatientNumber = pediatricianPatientNumber;
        VaccineAppointment = vaccineAppointment;
        LabResults = labResults;
        CovidTest = covidTest;
        NurseCare = nurseCare;

        Clinic = clinic;
    }

    public ServicesTypeRep() {
    }

    private int WorkingDaysOfTheWeek() {
        int WorkingDays = 6;
        return WorkingDays;
    }

    //acts as the start of a new day/week
    public ServicesTypeRep(Clinic clinic, HMO_Manager hmo_manager, String day) {

        Clinic = clinic;
        Hmo_Manager = hmo_manager;
        this.DayOfTheWeek = day;
        FamilyDoctorPatientNumber = 0;
        PediatricianPatientNumber = 0;
        VaccineAppointment = 0;
        LabResults = 0;
        CovidTest = 0;
        NurseCare = 0;
    }

    public HMO_Manager getHmo_Manager() {
        return Hmo_Manager;
    }

    public void setHmo_Manager(HMO_Manager hmo_Manager) {
        Hmo_Manager = hmo_Manager;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        DayOfTheWeek = dayOfTheWeek;
    }

    public Clinic getClinic() {
        return Clinic;
    }

    public void setClinic(Clinic clinic) {
        Clinic = clinic;
    }

    public String getDayOfTheWeek() {
        return DayOfTheWeek;
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

    //all those below need to be on the server
    //remmeber you need to add to current day
    //and to have another function to add to whateverday you want
    //also set/get to whatever day you want tho that may happen alone
// maybe as we use the parameter we will come from the right day
    public void AddAppointmentFamilyDoctor() {
        FamilyDoctorPatientNumber++;
    }

    public void AddAppointmentPediatrician() {
        PediatricianPatientNumber++;
    }

    public void AddAppointmentVaccine() {
        VaccineAppointment++;
    }

    public void AddAppointmentLabResults() {
        LabResults++;
    }


    public void AddAppointmentCovidTest() {
        CovidTest++;
    }


    public void AddAppointmentNurseCare() {
        NurseCare++;
    }

//
//
// if(id%WorkingDaysOfTheWeek()==0)
//    DayOfTheWeek="Sunday";
//          if(id%WorkingDaysOfTheWeek()==1)
//    DayOfTheWeek="Monday";
//          if(id%WorkingDaysOfTheWeek()==2)
//    DayOfTheWeek="Tuesday";
//          if(id%WorkingDaysOfTheWeek()==3)
//    DayOfTheWeek="Wednesday";
//          if(id%WorkingDaysOfTheWeek()==4)
//    DayOfTheWeek="Thursday";
//          if(id%WorkingDaysOfTheWeek()==5)
//    DayOfTheWeek="Friday";
}