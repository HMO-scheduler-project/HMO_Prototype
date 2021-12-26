package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;

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
    protected LocalTime openning_hour;
    @Column(name="ClosingHour")
    protected LocalTime closing_hour ;
    protected String address;
    protected String phone_number;
    @ManyToOne(targetEntity = Manager.class)
    protected Manager manager;
//    @OneToMany(targetEntity = Appointment.class)
//    protected List<Appointment> clinicAppointments=new ArrayList<>();
//

    public Clinic() { }
    public Clinic(String name, String city, LocalTime start,LocalTime end,Manager manager,String address,String phone_number) throws NoSuchAlgorithmException {
        this.name = name;
        this.city = city;
        this.openning_hour = start;
        this.closing_hour = end;
        this.manager = manager;
        this.address = address;
        this.phone_number = phone_number;
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

    public LocalTime getOpenningHour() {
        return openning_hour;
    }

    public void setOpenningHour(LocalTime start) {
        this.openning_hour = start;
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

    public LocalTime getOpenning_hour() {
        return openning_hour;
    }

    public void setOpenning_hour(LocalTime openning_hour) {
        this.openning_hour = openning_hour;
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

//    public List<Appointment> getClinicAppointments() {
//        return clinicAppointments;
//    }
//
//    public void setClinicAppointments(List<Appointment> clinicAppointments) {
//        this.clinicAppointments = clinicAppointments;
//    }
}

