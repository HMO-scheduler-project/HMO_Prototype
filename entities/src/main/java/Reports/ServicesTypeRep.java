package Reports;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.HMO_Manager;

import javax.persistence.*;

import java.time.LocalDate;


//save last week we need to do that
//save at the server last week and start a new one


@Entity
public class ServicesTypeRep extends WeeklyReport {

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
      @OneToOne( targetEntity = Clinic.class )
      private Clinic Clinic;
      public ServicesTypeRep(Clinic clinic, LocalDate date, HMO_Manager hmo_manager, int familyDoctorPatientNumber, int pediatricianPatientNumber,
                             int vaccineAppointment, int labResults, int covidTest, int nurseCare,String day) {
          super(clinic,date,hmo_manager);
          this.DayOfTheWeek=day;
          FamilyDoctorPatientNumber = familyDoctorPatientNumber;
          PediatricianPatientNumber = pediatricianPatientNumber;
          VaccineAppointment = vaccineAppointment;
          LabResults = labResults;
          CovidTest = covidTest;
          NurseCare = nurseCare;
      }

    public ServicesTypeRep() {
    }

    private int WorkingDaysOfTheWeek() {
        int WorkingDays=6;
        return WorkingDays;
    }
    //acts as the start of a new day/week
      public ServicesTypeRep(Clinic clinic, LocalDate date, HMO_Manager hmo_manager,String day) {
          super(clinic,date,hmo_manager);
          this.DayOfTheWeek=day;
          FamilyDoctorPatientNumber=0;
          PediatricianPatientNumber=0;
          VaccineAppointment=0;
          LabResults=0;
          CovidTest=0;
          NurseCare=0;
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