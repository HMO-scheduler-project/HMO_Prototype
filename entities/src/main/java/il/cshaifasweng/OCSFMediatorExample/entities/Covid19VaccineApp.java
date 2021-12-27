package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Covid19VaccineApp extends LabApp implements Serializable {

    public Covid19VaccineApp(){ super(); }

    public Covid19VaccineApp(LocalTime time, LocalDate date, Clinic clinic, Patient patient, LabWorker worker) {
        super(time, date, clinic, patient, worker);
    }

    public int getDuration(){
        return 10;
    }
}
