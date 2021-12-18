package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;

@Entity
@Table(name = "clinics", schema = "project")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num")
    public int Counter;
    @Column(name="Name")
    protected String name;
    @Column(name="City")
    protected String city;
    @Column(name="OpenningHour")
    protected Time openning_hour;
    @Column(name="ClosingHour")
    protected Time closing_hour ;
    @ManyToOne(targetEntity = Manager.class)
    protected Manager manager;

    public Clinic() { }
    public Clinic(String name, String city, Time start,Time end,Manager manager) throws NoSuchAlgorithmException {
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}

