package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class SpecialDoctor extends Doctor{

    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> docClinics; //one special doctor works in several clinics

    public SpecialDoctor(String username, String password, String first_name, String last_name,String role, String card,
                  String email,String phone_number, String main_clinic, List<Clinic> doc_clinics) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,role,card,email,phone_number,main_clinic);
        this.docClinics=doc_clinics;

    }
    public SpecialDoctor() {
        super();
        this.docClinics=null;
    }

    public void addClinic(Clinic clinic){
        this.docClinics.add(clinic);
    }

    public void removeClinic(Clinic clinic){
        this.docClinics.remove(clinic);
    }

}
