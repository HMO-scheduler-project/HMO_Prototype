package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
public class Manager extends Employee {

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
