package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Entity
public class Manager extends Employee {
    @OneToMany(targetEntity = Clinic.class)
    protected List<Clinic> managing_clinics;

    public Manager(String username, String password,int card,String Email,String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,"Manager",card,Email,main_clinic);
    }

    public Manager() {
        super();
    }

    public String getFullName(){
        return first_name+last_name;
    }

    @Override
    public String toString() {
        return "manager[ "+super.toString() + " ]";
    }

}
