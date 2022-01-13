package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.SpecialDoctor;
import il.cshaifasweng.OCSFMediatorExample.entities.specialDoctorApp;

import java.util.List;

public class SpecialDocClinicsEvent {
    private List<specialDoctorApp> appointments;
    private List<Clinic> clinics;
    private SpecialDoctor specialDoctor;

    public List<specialDoctorApp> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<specialDoctorApp> appointments) {
        this.appointments = appointments;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public SpecialDoctor getSpecialDoctor() {
        return specialDoctor;
    }

    public void setSpecialDoctor(SpecialDoctor specialDoctor) {
        this.specialDoctor = specialDoctor;
    }

    public SpecialDocClinicsEvent(List<specialDoctorApp> appointments, List<Clinic> clinics, SpecialDoctor specialDoctor) {
        this.appointments = appointments;
        this.clinics = clinics;
        this.specialDoctor = specialDoctor;
    }
}
