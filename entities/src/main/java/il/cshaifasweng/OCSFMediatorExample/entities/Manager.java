package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class Manager extends Employee {
    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> managing_clinics;

    public Manager(String username, String password,String first_name,String last_name,int card,String Email,String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,"Manager",card,Email,main_clinic);
    }

    public Manager() {
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

    @Override
    public String toString() {
        return "manager[ "+super.toString() + " ]";
    }

}
