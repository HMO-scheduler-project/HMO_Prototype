package il.cshaifasweng.OCSFMediatorExample.entities;

import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import Reports.WeeklyReport;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Clinic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num")
    public int Counter;
    @Column(name="Name")
    protected String name;
    @Column(name="City")
    protected String city;
    @Column(name="OpenningHour")
    protected LocalTime opening_hour;
    @Column(name="ClosingHour")
    protected LocalTime closing_hour ;
    protected String address;
    protected String phone_number;
    @ManyToOne(targetEntity = Manager.class)
    protected Manager manager;
    @OneToMany(targetEntity = Appointment.class)
    protected List<Appointment> clinicAppointments;
    @OneToOne(targetEntity = WeeklyReport.class)
    protected WeeklyReport WeeklyReportOfClinic;
    protected boolean labServices;
    protected boolean covidTestService;
    protected boolean covidVaccine;
    protected boolean influenzaVaccine;
    protected boolean specialists;

    @OneToOne(targetEntity = AwaitingTimeRep.class)
    protected AwaitingTimeRep awaitingTimeRep;
    @OneToOne(targetEntity = MissedAppRep.class)
    protected MissedAppRep missedAppRep;
    @OneToOne(targetEntity = ServicesTypeRep.class)
    protected ServicesTypeRep servicesTypeRep;

    public int getNum() {
        return Counter;
    }

    public Clinic() { }
    public Clinic(String name, String city, LocalTime start,LocalTime end,Manager manager,String address,String phone_number) throws NoSuchAlgorithmException {
        this.name = name;
        this.city = city;
        this.opening_hour = start;
        this.closing_hour = end;
        this.manager = manager;
        this.address = address;
        this.phone_number = phone_number;
        this.labServices = false;
        this.covidTestService = false;
        this.covidVaccine = false;
        this.influenzaVaccine = false;
        this.specialists = false;
    }
    public AwaitingTimeRep getAwaitingTimeRep() {
        return awaitingTimeRep;
    }

    public int getCounter() {
        return Counter;
    }
    public void setAwaitingTimeRep(AwaitingTimeRep awaitingTimeRep) {
        this.awaitingTimeRep = awaitingTimeRep;
    }

    public MissedAppRep getMissedAppRep() {
        return missedAppRep;
    }

    public void setMissedAppRep(MissedAppRep missedAppRep) {
        this.missedAppRep = missedAppRep;
    }

    public ServicesTypeRep getServicesTypeRep() {
        return servicesTypeRep;
    }

    public void setServicesTypeRep(ServicesTypeRep servicesTypeRep) {
        this.servicesTypeRep = servicesTypeRep;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalTime getOpeningHour() {
        return opening_hour;
    }

    public void setOpeningHour(LocalTime start) {
        this.opening_hour = start;
    }

    public LocalTime getClosingHour() {
        return closing_hour;
    }

    public void setClosingHour(LocalTime end) {
        this.closing_hour = end;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phone_number;
    }

    public void setPhoneNum(String phone_number) {
        this.phone_number = phone_number;
    }

    public LocalTime getClosing_hour() {
        return closing_hour;
    }

    public void setClosing_hour(LocalTime closing_hour) {
        this.closing_hour = closing_hour;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<Appointment> getClinicAppointments() {
        return clinicAppointments;
    }

    public void setClinicAppointments(List<Appointment> clinicAppointments) {
        this.clinicAppointments = clinicAppointments;
    }

    public WeeklyReport getWeeklyReportOfClinic() {
        return WeeklyReportOfClinic;
    }

    public void setWeeklyReportOfClinic(WeeklyReport weeklyReportOfClinic) {
        WeeklyReportOfClinic = weeklyReportOfClinic;
    }

    public boolean hasLabServices() {
        return labServices;
    }

    public void setLabServices(boolean labServices) {
        this.labServices = labServices;
    }

    public boolean hasCovidTestService() {
        return covidTestService;
    }

    public void setCovidTestService(boolean covidTestService) {
        this.covidTestService = covidTestService;
    }

    public boolean hasCovidVaccine() {
        return covidVaccine;
    }

    public void setCovidVaccine(boolean covidVaccine) {
        this.covidVaccine = covidVaccine;
    }

    public boolean hasInfluenzaVaccine() {
        return influenzaVaccine;
    }

    public void setInfluenzaVaccine(boolean influenzaVaccine) {
        this.influenzaVaccine = influenzaVaccine;
    }

    public boolean hasSpecialists() {
        return specialists;
    }

    public void setSpecialists(boolean specialists) {
        this.specialists = specialists;
    }

    public boolean isSpecialists() {
        return specialists;
    }
}

