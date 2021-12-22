package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class HMO_Manager extends Manager {
    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> managing_clinics;

    public HMO_Manager(String username, String password,String first_name,String last_name,int card,String Email,String main_clinic,List<Clinic> managing_clinics) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,"HMO_Manager",card,Email,main_clinic,managing_clinics);
    }

    public HMO_Manager() {
        super();
    }

    public String getFullName(){
        return getFirstName()+getLastName();
    }

    public List<Clinic> getManaging_clinics() {
        return managing_clinics;
    }

    public void setManaging_clinics(List<Clinic> managing_clinics) {
        this.managing_clinics = managing_clinics;
    }

    public void addClinic(Clinic clinic){
        managing_clinics.add(clinic);
    }

    @Override
    public String toString() {
        return "HMO manager[ "+super.toString() + " ]";
    }

}