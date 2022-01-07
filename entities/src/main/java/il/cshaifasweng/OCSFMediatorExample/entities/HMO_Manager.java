package il.cshaifasweng.OCSFMediatorExample.entities;

import Reports.WeeklyReport;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.List;

@Entity
public class HMO_Manager extends Manager {
    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> managing_clinics;

    @OneToMany(targetEntity = WeeklyReport.class)
    protected List<WeeklyReport> Weekly_Reports;


    public HMO_Manager(String username, String password, String first_name, String last_name, String card, String Email, String phone_num, String main_clinic, List<Clinic> managing_clinics, int room_num, LocalTime start, LocalTime end) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,"HMO_Manager",card,Email,phone_num,main_clinic,managing_clinics,room_num,start,end);
    }

    public List<WeeklyReport> getWeekly_Reports() {
        return Weekly_Reports;
    }

    public void setWeekly_Reports(List<WeeklyReport> weekly_Reports) {
        Weekly_Reports = weekly_Reports;
    }

    public HMO_Manager() {
        super();
    }

    public String getFullName(){
        return getFirstName()+getLastName();
    }

    public List<Clinic> getManaging_clinics() {
        return managing_clinics;
    }

    public void setManaging_clinics(List<Clinic> managing_clinics) {
        this.managing_clinics = managing_clinics;
    }

    public void addClinic(Clinic clinic){
        managing_clinics.add(clinic);
    }

    @Override
    public String toString() {
        return "HMO manager[ "+super.toString() + " ]";
    }

}
