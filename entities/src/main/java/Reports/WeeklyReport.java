package Reports;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.HMO_Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;

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


    protected String ClinicName;
    protected LocalDate date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,mappedBy ="WeeklyReport" )
    private Manager manager;
    @ManyToOne(targetEntity = HMO_Manager.class,cascade = CascadeType.ALL)
    protected HMO_Manager Hmo_Manager;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public HMO_Manager getHmo_Manager() {
        return Hmo_Manager;
    }

    public void setHmo_Manager(HMO_Manager hmo_Manager) {
        Hmo_Manager = hmo_Manager;
    }



    public WeeklyReport() {
        this.date= LocalDate.now();
    }

    public WeeklyReport(String clinicName, LocalDate date) {
        ClinicName = clinicName;
        this.date = date;
    }

    public String getClinicName() {
        return ClinicName;
    }

    public void setClinicName(String clinicName) {
        ClinicName = clinicName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
