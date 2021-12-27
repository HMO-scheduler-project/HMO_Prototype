package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class LabWorker extends Employee{

    @OneToMany(targetEntity = LabApp.class)
    List<LabApp> labApps; // one lab worker has several lab appointments

    public LabWorker() {
        super();
        labApps=null;
    }

    public LabWorker(String username, String password, String first_name, String last_name, int card, String Email,
                     String main_clinic, List<LabApp> labApps) throws NoSuchAlgorithmException {
        super(username, password, first_name, last_name, "lab worker", card, Email, main_clinic);
        this.labApps = labApps;
    }

    public List<LabApp> getLabApps() {
        return labApps;
    }

    public void setLabApps(List<LabApp> labApps) {
        this.labApps = labApps;
    }

    public void addLabApp(LabApp new_app){
        this.labApps.add(new_app);
    }

    public void removeLabApp(LabApp app_to_delete){
        this.labApps.remove(app_to_delete);
    }

    @Override
    public String toString() {
        return "Lab Worker[ "+super.toString() + " ]";
    }
}
