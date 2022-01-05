package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient extends User implements Serializable {
    @OneToMany(targetEntity = Appointment.class)
    protected List<Appointment> patientAppointments;
    //family/children doctor assigned.
    @ManyToOne(targetEntity = Doctor.class)
    protected Doctor doctor;
    @ManyToOne(targetEntity = Appointment.class)
    protected Appointment next_appointment;
    @ManyToOne(targetEntity = Clinic.class)
    protected Clinic clinic;
    LocalDate date_of_birth;
    @OneToOne(targetEntity = GreenPass.class)
    protected GreenPass green_pass;
    @OneToMany(targetEntity = SpecialDoctor.class)
    protected List<SpecialDoctor> special_doctors;


    public Patient(String username, String password,String first_name,String last_name,Doctor doctor
            ,LocalDate date_of_birth,String card,String Email,Clinic clinic,
                   GreenPass green_pass) throws NoSuchAlgorithmException {
        super(username, password,card,first_name,last_name);
        this.patientAppointments = null;
        this.doctor = doctor;
        this.next_appointment = null;
        this.clinic = clinic;
        this.date_of_birth = date_of_birth;
        this.green_pass=green_pass;
        this.special_doctors= null;

    }

    public Patient() {
        super();
        this.patientAppointments = null;
        this.doctor = null;
        this.next_appointment = null;
        this.clinic = null;
        this.date_of_birth = null;
        this.green_pass = null;
        this.special_doctors= null;

    }

    public void addFamilyDoctorAppointment(Appointment new_app) {
        this.next_appointment=new_app;
        addAppointment(new_app);
    }

    public void addAppointment(Appointment new_app){
        this.patientAppointments.add(new_app);
    }

    public void addSpecialAppointment(Appointment new_app,SpecialDoctor doctor){
        addAppointment(new_app);
        if (this.special_doctors.contains(doctor)) {
            this.special_doctors.remove(doctor);
        }
        this.special_doctors.add(0, doctor);
    }

    public void cancelFamilyDoctorAppointment(Appointment app){
        this.next_appointment=null;
        cancelAppointment(app);
    }

    public void cancelAppointment(Appointment app){
        this.patientAppointments.remove(app);
    }

    public void changeFamilyDoctorAppointment(Appointment app_to_change,Appointment new_app){
        this.next_appointment=new_app;
        changeAppointment(app_to_change,new_app);
    }

    public void changeAppointment(Appointment app_to_change,Appointment new_app){
        cancelAppointment(app_to_change);
        addAppointment(new_app);
    }

    public GreenPass getGreen_pass() {
        return green_pass;
    }

    public void setGreen_pass(GreenPass green_pass) {
        this.green_pass = green_pass;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getFullName(){
        return getFirstName()+getLastName();
    }



}

