package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Patient extends User {
    @OneToMany(targetEntity = Appointment.class)
    private List<Appointment> patientAppointments;

}
