package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.security.NoSuchAlgorithmException;
import java.util.List;
@Entity
public class SpecialDoctor extends Doctor{

    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> docClinics;

    public SpecialDoctor(String username, String password, String first_name, String last_name, int card,
                  String email, String main_clinic, List<Clinic> doc_clinics) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,"Doctor",card,email,main_clinic);
        this.docClinics=doc_clinics;

    }
    public SpecialDoctor() {
        super();
        this.docClinics=null;
    }



}
