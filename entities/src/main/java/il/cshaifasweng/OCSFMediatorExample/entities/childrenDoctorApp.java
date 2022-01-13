package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class childrenDoctorApp extends doctorApp{

    public childrenDoctorApp() {
        super();
    }

    public childrenDoctorApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, Doctor doctor) {
        super(time, date, clinic, patient, doctor);
        this.type="Children doctor appointment";

    }


    public int getDuration(){
        return 15;
    }
}
