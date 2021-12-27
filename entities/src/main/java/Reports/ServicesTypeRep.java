package Reports;
import javax.persistence.*;
import java.io.Serializable;

//  static final int WorkingWeekDays = 6;
//if we decide we want to restart the week every week/ save last week we need to do that
//-save at the server last week and start a new one
//if we get empty report should we initialise the report to 0 at all items instead of the super thing?
@Entity
public class ServicesTypeRep extends WeeklyReport  implements Serializable {
//do i need to place ID? i allready have id and day in weekly report
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
      @Column(name = "Vaccine_Appointments")
      private int VaccineAppointment;
      @Column(name = "Lab_Test_Appointments")
      private int LabResults;
      @Column(name = "Covid_Test")
      private int CovidTest;
      @Column(name = "Nurse Care")
      private int NurseCare;

      public ServicesTypeRep(int familyDoctorPatientNumber, int pediatricianPatientNumber, int vaccineAppointment, int labResults, int covidTest, int nurseCare) {
          super();
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
    //acts as the start of a new day/week
      public ServicesTypeRep() {
          super();
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




}