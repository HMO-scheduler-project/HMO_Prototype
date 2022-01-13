package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class InfluenzaVaccineApp extends LabApp{

    public InfluenzaVaccineApp(){
        super();
    }

    public InfluenzaVaccineApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, LabWorker worker){
        super(time, date, clinic, patient, worker);
        this.type="Influenza vaccine appointment";
    }

    public int getDuration(){
        return 10;
    }
}
