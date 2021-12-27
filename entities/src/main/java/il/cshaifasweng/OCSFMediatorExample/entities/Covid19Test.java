package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Covid19Test extends LabApp{

    @OneToMany(targetEntity = Clinic.class)
    List<Clinic> clinicList; // one Covid19Test can be done at several clinics

    @OneToOne(targetEntity = Patient.class)
    protected Patient covidStatus; // one covid status refers to one patient

    public Covid19Test() {
        super();
        covidStatus=null;
        clinicList=null;
        // fillQuestionnaire();
    }

    public Patient getCovidStatus() {
        return covidStatus;
    }

    public void setCovidStatus(Patient covidStatus) {
        this.covidStatus = covidStatus;
    }

    /*
    public void fillQuestionnaire(){
        // the ques. checks whether the patient encountered a covid patient
        // and asks if there are any covid symptoms

    }
    */

}
