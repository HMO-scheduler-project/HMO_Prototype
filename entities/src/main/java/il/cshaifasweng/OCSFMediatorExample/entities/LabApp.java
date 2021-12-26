package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LabApp extends Appointment {
    @ManyToOne(targetEntity = LabWorker.class)
    LabWorker labworker;
    public LabApp()
    {
        super();
    }
}