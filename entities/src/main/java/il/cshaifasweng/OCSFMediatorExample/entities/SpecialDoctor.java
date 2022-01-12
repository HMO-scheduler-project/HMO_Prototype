package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.List;

@Entity
public class SpecialDoctor extends Doctor{

    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> docClinics; //one special doctor works in several clinics

    public SpecialDoctor(String username, String password, String first_name, String last_name, String role, String card,
                         String email, String phone_num, String main_clinic, List<Clinic> doc_clinics, int room_num, LocalTime start, LocalTime end) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,role,card,email,phone_num,main_clinic,room_num,start,end);
        this.docClinics=doc_clinics;

    }
    public SpecialDoctor() {
        super();
        this.docClinics=null;
    }

    public List<Clinic> getDocClinics() {
        return docClinics;
    }

    public void setDocClinics(List<Clinic> docClinics) {
        this.docClinics = docClinics;
    }

    public void addClinic(Clinic clinic){
        this.docClinics.add(clinic);
    }

    public void removeClinic(Clinic clinic){
        this.docClinics.remove(clinic);
    }

}
