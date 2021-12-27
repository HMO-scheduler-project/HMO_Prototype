package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class Nurse extends Employee {

    protected String role = "Nurse";

    @OneToMany(targetEntity = NurseApp.class)
    protected List<NurseApp> nurseApps; // one Nurse can refer to several Nurse Appointments

    public Nurse(String username, String password,String first_name,String last_name,int card,String Email,String main_clinic,List<NurseApp> nurseApps) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,"nurse",card,Email,main_clinic);
        this.nurseApps = nurseApps;
    }

    public Nurse() {
        super();
        nurseApps=null;
    }

    public List<NurseApp> getNurseApps() {
        return nurseApps;
    }

    public void setNurseApps(List<NurseApp> nurseApps) {
        this.nurseApps = nurseApps;
    }

    public void addNurseApp(NurseApp new_app){
        this.nurseApps.add(new_app);
    }

    public void removeNurseApp(NurseApp app_to_delete){
        this.nurseApps.remove(app_to_delete);
    }

    @Override
    public String toString() {
        return "Nurse[ " + super.toString() + " ]";
    }
}

