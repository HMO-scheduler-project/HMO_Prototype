package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

@Entity
public class Patient extends User implements Serializable {
//    @OneToMany(targetEntity = Appointment.class)
//    private List<Appointment> patientAppointments;

    protected String doctor;
    @ManyToOne(targetEntity = Appointment.class)
    protected Appointment next_appointment;
    protected String clinic_name;
    protected Boolean is_adult;
    @OneToOne(targetEntity = GreenPass.class)
    protected GreenPass greeny;
    @OneToMany(targetEntity = SpecialDoctor.class)
    protected SpecialDoctor special_doc;


    public Patient(String username, String password,String first_name,String last_name,String doctor,
                   Appointment next_appointment,Boolean is_adult, SpecialDoctor spec_doc,
            ,int card,String Email,String clinic_name,GreenPass is_green) throws NoSuchAlgorithmException {
        super(username, password,card,first_name,last_name);
        this.doctor = doctor;
        this.next_appointment = next_appointment;
        this.clinic_name = clinic_name;
        this.is_adult=is_adult;
        this.greeny=is_green;
        this.special_doc=spec_doc;

    }

    public Patient() {
        super();
        this.doctor = null;
        this.clinic_name = null;
        this.next_appointment = null;
        this.is_adult=null;
        this.greeny=null;
        this.special_doc=null;

    }

    public void newFamilyDoctorAppointment(Appointment new_app) {
        this.next_appointment=new_app;
    }

    public void cancelAppointment(){
        this.next_appointment=null;
    }

    public void changeAppointment(Appointment new_app){
        this.next_appointment=new_app;
    }

    public void setGreenPass(GreenPass is_green){
        this.greeny=is_green;
    }





}
