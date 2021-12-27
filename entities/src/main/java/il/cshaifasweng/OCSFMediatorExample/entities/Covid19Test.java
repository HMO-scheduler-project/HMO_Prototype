package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Covid19Test extends LabApp{

    protected boolean result;

    public Covid19Test() {
        super();
        result=false;
    }

    public Covid19Test(LocalTime time, LocalDate date, Clinic clinic, Patient patient, LabWorker worker){
        super(time, date, clinic, patient, worker);
        this.result=false;
    }

    public boolean getResult() { return result; }

    public void setResult(boolean result) { this.result = result; }

}
