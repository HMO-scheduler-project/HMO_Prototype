package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Manager extends Employee {
    protected String role = "Manager";
    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> managing_clinics;

    public Manager(String username, String password, String first_name, String last_name, String role, String card, String Email, String phone_num, String main_clinic, List<Clinic> managing_clinics, int room_num, LocalTime start, LocalTime end) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,role,card,Email,phone_num,main_clinic,room_num,start,end);
        this.managing_clinics = managing_clinics;
    }

    public Manager() {
        super();
    }

    public String getFullName(){
        return getFirstName()+" "+getLastName();
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

    public void addManagingClinic(Clinic clinic) { this.managing_clinics.add(clinic);}

    @Override
    public String toString() {
        return "manager[ "+super.toString() + " ]";
    }


}
