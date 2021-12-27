package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int appointment_id;
    protected LocalTime time;
    protected LocalDate date;
    @ManyToOne(targetEntity = Clinic.class)
    protected Clinic clinic;
    @ManyToOne(targetEntity = Patient.class)
    Patient patient;
    @ManyToOne(targetEntity = Employee.class)
    Employee employee;

    public Appointment() {
    }

    public Appointment(int appointment_id, LocalTime time, LocalDate date, Clinic clinic, Patient patient,Employee employee) {
        //this.appointment_id = appointment_id;
        this.time = time;
        this.date = date;
        this.clinic = clinic;
        this.patient = patient;
        this.employee = employee;
    }


    public int getAppointment_id() {
        return appointment_id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

