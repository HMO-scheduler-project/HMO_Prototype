package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class familyDoctorApp extends doctorApp{
    @ManyToOne(targetEntity = Doctor.class)
    Doctor doctor;

    public familyDoctorApp() {
        super();
    }

    @Override
    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


}
