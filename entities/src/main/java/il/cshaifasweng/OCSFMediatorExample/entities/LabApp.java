package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class LabApp extends Appointment {
    int counter=1;
    @ManyToOne(targetEntity = LabWorker.class)
    protected LabWorker labworker;

    public LabApp()
    {
        super();
    }

    public LabApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, LabWorker worker){
        super(time, date, clinic, patient);
        this.labworker = worker;
        this.counter++;
    }

    public LabWorker getLabworker() {
        return labworker;
    }

    public void setLabworker(LabWorker labworker) {
        this.labworker = labworker;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void incCounter() {
        this.counter++;
    }
}