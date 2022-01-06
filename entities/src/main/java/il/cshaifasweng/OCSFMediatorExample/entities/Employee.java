package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class Employee extends User {
    protected String role;
    protected String main_clinic;
    @OneToMany(targetEntity = Appointment.class)
    protected List<Appointment> appointments;

    public Employee(String username, String password,String first_name,String last_name,String role,String card,String Email,String phone_number,String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,card,first_name,last_name,Email,phone_number);
        this.role = role;
        this.main_clinic = main_clinic;
    }

    public Employee() {
        super();
        this.Email = null;
        this.main_clinic = null;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMain_clinic() {
        return main_clinic;
    }

    public void setMain_clinic(String main_clinic) {
        this.main_clinic = main_clinic;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment){
        this.appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment){
        this.appointments.remove(appointment);
    }

    @Override
    public String toString() {
        return "employee[ "+super.toString() + "Email: "+Email+"main clinic: "+main_clinic+" ]";
    }

}
