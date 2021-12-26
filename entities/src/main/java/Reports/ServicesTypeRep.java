package Reports;
import javax.persistence.*;
import java.io.Serializable;

//  static final int WorkingWeekDays = 6;

@Entity
public class ServicesTypeRep extends WeeklyReport  implements Serializable {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "Num")
      public int ID;
      @Column(name = "Day")
      private String DayOfTheWeek;
      @Column(name = "FamilyDoctor")
      private int FamilyDoctorPatientNumber;
      @Column(name = "Pediatrician")
      private int PediatricianPatientNumber;
      @Column(name = "Vaccine Appointments")
      private int VaccineAppointment;
      @Column(name = "Lab Test Appointments")
      private int LabResults;
      @Column(name = "Covid Test")
      private int CovidTest;
      @Column(name = "Nurse Care")
      private int NurseCare;

      public ServicesTypeRep(int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare) {
          if(ID%WorkingDaysOfTheWeek()==0)
              DayOfTheWeek="Sunday";
          if(ID%WorkingDaysOfTheWeek()==1)
              DayOfTheWeek="Monday";
          if(ID%WorkingDaysOfTheWeek()==2)
              DayOfTheWeek="Tuesday";
          if(ID%WorkingDaysOfTheWeek()==3)
              DayOfTheWeek="Wednesday";
          if(ID%WorkingDaysOfTheWeek()==4)
              DayOfTheWeek="thursday";
          if(ID%WorkingDaysOfTheWeek()==5)
              DayOfTheWeek="Friday";

          FamilyDoctorPatientNumber = familyDoctorPatientNumber;
          PediatricianPatientNumber = pediatricianPatientNumber;
          VaccineAppointment = vaccineAppointment;
          LabResults = labResults;
          CovidTest = covidTest;
          NurseCare = nurseCare;
      }
    private int WorkingDaysOfTheWeek() {
        int WorkingDays=6;
        return WorkingDays;
    }
      public ServicesTypeRep() {
          super();
      }

      public int getID() {
          return ID;
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
  }