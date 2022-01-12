package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class familyDoctorApp extends doctorApp{
    @ManyToOne(targetEntity = Doctor.class)
    protected Doctor doctor;

    public familyDoctorApp() {
        super();
    }

    public familyDoctorApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, Doctor doctor) {
        super(time, date, clinic, patient, doctor);
        this.type="Family doctor appointment";
    }


    public int getDuration(){
        return 15;
    }
}
