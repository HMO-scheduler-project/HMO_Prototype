package il.cshaifasweng.OCSFMediatorExample.entities;

import Reports.WeeklyReport;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class Manager extends Employee {
    protected String role = "Manager";
    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> managing_clinics;

    public Manager(String username, String password,String first_name,String last_name,String role,int card,String Email,String main_clinic,List<Clinic> managing_clinics) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,role,card,Email,main_clinic);
        this.managing_clinics = managing_clinics;
    }

    public Manager() {
        super();
    }

    //added to manager from WeeklyReport tell others tomorrow
    @OneToOne(cascade =CascadeType.ALL )
    @JoinColumn(name="WeeklyReport")
    protected WeeklyReport WeeklyReportOfClinic;

    public WeeklyReport getWeeklyReportOfClinic() {
        return WeeklyReportOfClinic;
    }

    public void setWeeklyReportOfClinic(WeeklyReport weeklyReportOfClinic) {
        WeeklyReportOfClinic = weeklyReportOfClinic;
    }

    public String getFullName(){
        return getFirstName()+getLastName();
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    public List<Clinic> getManaging_clinics() {
        return managing_clinics;
    }

    public void setManaging_clinics(List<Clinic> managing_clinics) {
        this.managing_clinics = managing_clinics;
    }

    @Override
    public String toString() {
        return "manager[ "+super.toString() + " ]";
    }


}
