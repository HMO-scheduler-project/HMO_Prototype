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
    protected boolean arrived=false;
    protected LocalTime actual_time;
    protected LocalDate date;
    @ManyToOne(targetEntity = Clinic.class)
    protected Clinic clinic;
    @ManyToOne(targetEntity = Patient.class)
    Patient patient;
    @ManyToOne (targetEntity = Employee.class)
    Employee employee;
    protected String type="Appointment";

    public Appointment() {
    }

    public Appointment(LocalTime time, boolean arrived, LocalDate date, Employee employee) {
        this.time = time;
        this.arrived = arrived;
        this.date = date;
        this.employee = employee;
        this.actual_time=LocalTime.parse("00:00");
    }
    public Appointment(LocalTime time, LocalTime actual_time, boolean arrived, LocalDate date, Clinic clinic, Employee employee, String type,Patient patient) {
        this.time = time;
        this.actual_time = actual_time;
        this.arrived = arrived;
        this.date = date;
        this.clinic = clinic;
        this.employee = employee;
        this.type = type;
        this.patient=patient;
    }

    public Appointment(LocalTime time, boolean arrived, LocalDate date, Clinic clinic, Employee employee) {
        this.time = time;
        this.arrived = arrived;
        this.date = date;
        this.clinic = clinic;
        this.employee = employee;
        this.actual_time=LocalTime.parse("00:00");
    }
    public Appointment(LocalTime time, LocalTime actual_time, boolean arrived, LocalDate date, Clinic clinic, Employee employee) {
        this.time = time;
        this.actual_time = actual_time;
        this.arrived = arrived;
        this.date = date;
        this.clinic = clinic;
        this.employee = employee;
    }

    public Appointment(LocalTime time, LocalTime actual_time, boolean arrived, LocalDate date, Clinic clinic, String type, Patient patient) {
        this.time = time;
        this.actual_time = actual_time;
        this.arrived = arrived;
        this.date = date;
        this.clinic = clinic;
        this.type = type;
        this.patient=patient;

    }
    public Appointment(LocalTime time, LocalTime actual_time, boolean arrived, LocalDate date, Clinic clinic, Patient patient, Employee employee, String type) {
        this.time = time;
        this.actual_time = actual_time;
        this.arrived = arrived;
        this.date = date;
        this.clinic = clinic;
        this.patient = patient;
        this.employee = employee;
        this.type = type;
    }
    public Appointment(int appointment_id, LocalTime time, boolean arrived, LocalDate date, Employee employee, String type) {
        this.appointment_id = appointment_id;
        this.time = time;
        this.arrived = arrived;
        this.date = date;
        this.employee = employee;
        this.type = type;
        this.actual_time=LocalTime.parse("00:00");
    }
    public Appointment(Clinic clinic,int appointment_id, LocalTime time, boolean arrived, LocalDate date, Employee employee, String type) {
        this.clinic=clinic;
        this.appointment_id = appointment_id;
        this.time = time;
        this.arrived = arrived;
        this.date = date;
        this.employee = employee;
        this.type = type;
        this.actual_time=LocalTime.parse("00:00");
    }
    public Appointment(LocalTime time, LocalDate date, Clinic clinic, Patient patient,Employee employee) {
        this.time = time;
        this.date = date;
        this.clinic = clinic;
        this.patient = patient;
        this.employee = employee;
        this.actual_time=LocalTime.parse("00:00");
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

    public LocalTime getActual_time() {
        return actual_time;
    }

    public void setActual_time(LocalTime actual_time) {
        this.actual_time = actual_time;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

