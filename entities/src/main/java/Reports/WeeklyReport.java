package Reports;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


// needs to only allow clinic manager to access the weekly report

public class WeeklyReport  implements Serializable {
    // what clinic are we referring too in the report
    // the reports are available for  the administrative  manager of the clinic and the clinic manager alone

   //needs to connect each clinic to its reports
    @OneToMany
    protected String ClinicName;
    protected Date date;

    public WeeklyReport() {
    }

    public WeeklyReport(String clinicName, Date date) {
        ClinicName = clinicName;
        this.date = date;
    }

    public String getClinicName() {
        return ClinicName;
    }

    public void setClinicName(String clinicName) {
        ClinicName = clinicName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
