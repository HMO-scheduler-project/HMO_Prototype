package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class childrenDoctorApp extends doctorApp{

    @ManyToOne (targetEntity = Doctor.class)
    Doctor Doctor;

    public childrenDoctorApp() {
        super();
    }
    @Override
    public il.cshaifasweng.OCSFMediatorExample.entities.Doctor getDoctor() {
        return Doctor;
    }

    @Override
    public void setDoctor(il.cshaifasweng.OCSFMediatorExample.entities.Doctor doctor) {
        Doctor = doctor;
    }


}
