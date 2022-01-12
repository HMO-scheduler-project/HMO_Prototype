package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class specialDoctorApp extends doctorApp {

    @ManyToOne(targetEntity = SpecialDoctor.class)
    protected SpecialDoctor doctor;

    public specialDoctorApp() {
        super();
    }

    public specialDoctorApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, SpecialDoctor doctor) {
        super(time, date, clinic, patient, doctor);
        this.doctor = doctor;
        this.type= doctor.role+" appointment";
    }


    public int getDuration(){
        return 20;
    }

    public SpecialDoctor getSpecialDoctor(){
        return doctor;
    }
}
