package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class NurseApp extends Appointment {
    @ManyToOne(targetEntity = Nurse.class)
    Nurse nurse;
    public NurseApp()
    {
        super();
    }
}