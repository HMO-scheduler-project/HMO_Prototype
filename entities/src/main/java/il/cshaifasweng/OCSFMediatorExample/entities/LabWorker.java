package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class LabWorker extends Employee{
    @OneToMany(targetEntity = LabApp.class)
    List<LabApp> labApps;

    public LabWorker() {
        super();
        labApps=null;
    }

    public LabWorker(String username, String password, String first_name, String last_name, int card, String Email, String main_clinic, List<LabApp> labApps) throws NoSuchAlgorithmException {
        super(username, password, first_name, last_name, "lab worker", card, Email, main_clinic);
        this.labApps = labApps;
    }

    public List<LabApp> getLabApps() {
        return labApps;
    }

    public void setLabApps(List<LabApp> labApps) {
        this.labApps = labApps;
    }
    @Override
    public String toString() {
        return "Lab Worker[ "+super.toString() + " ]";
    }
}
