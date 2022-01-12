package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Doctor;

import java.util.List;

public class GotDoctorsEvent {
    private List<Doctor> doctorList;
    private List<Appointment> clinicAppointments;

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public GotDoctorsEvent(List<Doctor> doctorList , List<Appointment> clinicAppointments) {
        this.doctorList = doctorList;
        this.clinicAppointments=clinicAppointments;
    }

    public List<Appointment> getClinicAppointments() {
        return clinicAppointments;
    }

    public void setClinicAppointments(List<Appointment> clinicAppointments) {
        this.clinicAppointments = clinicAppointments;
    }
}
