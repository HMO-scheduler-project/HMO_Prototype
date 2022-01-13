package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.LabWorker;

import java.util.List;

public class GotLabWorkersVaccineEven {
    private List<LabWorker> labworkers;
    private List<Appointment> appointmentList;
    private Clinic clinic;

    public GotLabWorkersVaccineEven(List<LabWorker> labworkers, List<Appointment> appointmentList,Clinic clinic) {
        this.labworkers = labworkers;
        this.appointmentList = appointmentList;
        this.clinic=clinic;
    }

    public List<LabWorker> getLabworkers() {
        return labworkers;
    }

    public void setLabworkers(List<LabWorker> labworkers) {
        this.labworkers = labworkers;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}


