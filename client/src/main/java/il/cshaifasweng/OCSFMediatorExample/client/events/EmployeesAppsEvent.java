package il.cshaifasweng.OCSFMediatorExample.client.events;


import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class EmployeesAppsEvent {
    private List<Appointment> appointmentList;

    public EmployeesAppsEvent(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}

