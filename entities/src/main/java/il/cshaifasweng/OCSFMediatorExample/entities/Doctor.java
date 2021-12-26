package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import java.security.NoSuchAlgorithmException;
import java.util.List;
@Entity
public class Doctor extends Employee {


    public Doctor(String username, String password, String first_name, String last_name, int card,
                 String email, String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,"Doctor",card,email,main_clinic);

    }
    public Doctor() {
        super();
    }

    public Doctor(String username, String password, String first_name, String last_name, String doctor,
                  int card, String email, String main_clinic) throws NoSuchAlgorithmException {
        super(username, password,first_name,last_name,"Doctor",card,email,main_clinic);

    }
}
