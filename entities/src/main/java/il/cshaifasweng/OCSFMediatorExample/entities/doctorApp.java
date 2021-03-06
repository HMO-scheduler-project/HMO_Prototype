package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class doctorApp  extends Appointment{
    @ManyToOne(targetEntity = SpecialDoctor.class)
    protected Doctor doctor;

    public doctorApp(){
        super();
    }

    public doctorApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, Doctor doctor) {
        super(time, date, clinic, patient,doctor);
        this.doctor = doctor;
        this.type="Doctor appointment";

    }

}
