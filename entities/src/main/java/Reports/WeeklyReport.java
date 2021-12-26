package Reports;

import java.io.Serializable;
import java.time.LocalDate;



// needs to only allow clinic manager to access the weekly report

public class WeeklyReport  implements Serializable {
  //Do We need OneToMany Anoations? I dont think so ask anyway
//    @OneToMany(targetEntity = Clinic.class)
//    protected List<Clinic> managing_clinics;
    // what clinic are we referring too in the report
    // the reports are available for  the administrative  manager of the clinic and the clinic manager alone
    //needs to connect each clinic to its reports
    protected String ClinicName;
    protected LocalDate date;

    public WeeklyReport() {
        this.date= java.time.LocalDate.now();
    }

    public WeeklyReport(String clinicName, LocalDate date) {
        ClinicName = clinicName;
        this.date = date;
    }

    public String getClinicName() {
        return ClinicName;
    }

    public void setClinicName(String clinicName) {
        ClinicName = clinicName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
