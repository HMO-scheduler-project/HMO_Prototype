package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class Doctor extends Employee {
    @OneToMany(targetEntity = Patient.class)
    protected List<Patient> patientList;

    public Doctor(String username, String password, String first_name, String last_name,String role, String card,
                 String email,String phone_number, String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,role,card,email,phone_number,main_clinic);
        this.patientList = null;

    }
    public Doctor() {
        super();
        this.patientList = null;
    }

    public void addPatient(Patient patient){
        this.patientList.add(patient);
    }

    public void removePatient(Patient patient) {
        this.patientList.remove(patient);
    }

}
