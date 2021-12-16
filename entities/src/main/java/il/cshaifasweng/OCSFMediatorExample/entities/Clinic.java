package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;

@Entity
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Counter;
    protected String name;
    protected String city;
    protected Time openning_hour;
    protected Time closing_hour ;
    protected String manager;

    public Clinic() { }
    public Clinic(String name, String city, Time start,Time end,String manager) throws NoSuchAlgorithmException {
        this.name = name;
        this.city = city;
        this.openning_hour = start;
        this.closing_hour = end;
        this.manager = manager;
        Counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Time getOpenningHour() {
        return openning_hour;
    }

    public void setOpenningHour(Time start) {
        this.openning_hour = start;
    }

    public Time getClosingHour() {
        return closing_hour;
    }

    public void setClosingHour(Time end) {
        this.closing_hour = end;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}

