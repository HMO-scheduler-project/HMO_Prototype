package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class doctorApp  extends Appointment{

    @ManyToOne (targetEntity = Doctor.class)
    Doctor doctor;

    public doctorApp(){
        super();
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
