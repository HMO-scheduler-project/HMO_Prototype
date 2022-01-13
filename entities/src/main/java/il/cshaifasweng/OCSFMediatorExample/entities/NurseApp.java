package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class NurseApp extends Appointment {

    @ManyToOne(targetEntity = Nurse.class)
    Nurse nurse;

    public NurseApp()
    {
        super();
    }

    public NurseApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, Nurse nurse) {
        super(time, date, clinic, patient,nurse);
        this.nurse = nurse;
        this.type="Nurse appointment";
    }
}