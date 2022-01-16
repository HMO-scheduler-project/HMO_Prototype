package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
public class WeeklyReport  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected LocalDate date;
    @OneToOne( targetEntity = Clinic.class )
    private Clinic Clinic;
    @ManyToOne( targetEntity = HMO_Manager.class )
    protected HMO_Manager Hmo_Manager;

    public WeeklyReport() {
        this.date= LocalDate.now();
    }
    public WeeklyReport(Clinic clinic, HMO_Manager hmo_Manager) {
        this.date=LocalDate.now();
        Clinic = clinic;
        Hmo_Manager = hmo_Manager;
    }

    public WeeklyReport( Clinic clinic,LocalDate date) {
        this.date = date;
        Clinic = clinic;
    }

    public WeeklyReport( Clinic clinic,LocalDate date, HMO_Manager hmo_Manager) {
        this.date = date;
        Clinic = clinic;
        Hmo_Manager = hmo_Manager;
    }
    public HMO_Manager getHmo_Manager() {
        return Hmo_Manager;
    }
    public void setHmo_Manager(HMO_Manager hmo_Manager) {
        Hmo_Manager = hmo_Manager;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public Clinic getClinic() {
        return Clinic;
    }
    public void setClinic(Clinic clinic) {
        Clinic = clinic;
    }
}
