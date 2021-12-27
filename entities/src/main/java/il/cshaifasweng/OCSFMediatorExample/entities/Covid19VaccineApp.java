package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Covid19VaccineApp extends LabApp implements Serializable {

    @OneToMany(targetEntity = Clinic.class)
    ArrayList<String> clinicList; // one covid19Vaccine Appointment can be done at several clinics

    //@ManyToOne
    //@JoinColumn(name = "clinic_num")
    private String clinicName; // one clinic can refer to several covid appointments

    protected LocalDate date;
    protected LocalDate dateOfApp;

    @Id
    public Long id;

    public Covid19VaccineApp(){
        super();
        this.date= LocalDate.now();
        // fillQuestionnaire();
    }

    public Covid19VaccineApp(String clinicName, LocalDate date) {
        clinicName = clinicName;
        this.date = date;
    }

    public List<String> getClinicList() {
        if(clinicList==null){ return new ArrayList(); }
        return clinicList;
    }

    public void setClinicList(ArrayList<String> clinicList) { this.clinicList = clinicList; }

    public String getClinicName() { return clinicName; }

    public void setClinicName(String clinicName) { this.clinicName = clinicName; }

//    public LocalDate getDate() { return date; }

    void getDate(String clinicName){
        // get the name of the clinic in order to set an appointment
        // show available appointments to choose from (in order)

    }

    public void setDate(LocalDate date){ this.date=date; }

    public void setDateOfApp(LocalDate date) { this.dateOfApp = date; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

/*
    // a drop_down showing available appointments
    @FXML
    void chooseDateOfApp() {
        String chosenClinic = ClinicsList.getSelectionModel().getSelectedItem();
        chosen_clinic = chosenClinic;
        clientMsg.setClinicName(chosenClinic);
        clientMsg.setAction("pull vaccine hours");
        try {
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

}
