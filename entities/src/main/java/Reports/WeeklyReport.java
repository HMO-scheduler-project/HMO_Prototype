package Reports;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.HMO_Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.SpecialDoctor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


// needs to only allow clinic manager to access the weekly report
@Entity
public class WeeklyReport  implements Serializable {
  //Do We need OneToMany Anoations? I dont think so ask anyway
//    @OneToMany(targetEntity = Clinic.class)
//    protected List<Clinic> managing_clinics;
    // what clinic are we referring too in the report
    // the reports are available for  the administrative  manager of the clinic to see the current clinic
    // and the clinic manager alone to all clinics
    //needs to connect each clinic to its reports



    //will add to manager for tell others tomorrow
    //each report has one manager and and one HMOMANAGER
    //each manager has one Report and each HMOManager has alot of reports one from each clinic
    //did i write the code correctly?
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
