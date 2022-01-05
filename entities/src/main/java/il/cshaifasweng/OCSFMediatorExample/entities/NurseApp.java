package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class NurseApp extends Appointment {
    int counter=1;
    @ManyToOne(targetEntity = Nurse.class)
    Nurse nurse;

    public NurseApp()
    {
        super();
    }

    public NurseApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, Nurse nurse) {
        super(time, date, clinic, patient);
        this.nurse = nurse;
        this.counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void incCounter() {
        this.counter++;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
}