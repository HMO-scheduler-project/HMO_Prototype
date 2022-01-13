package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class ViewAppsEvent {
    List<Appointment> appsList;

    public ViewAppsEvent(List<Appointment> appsList) {
        this.appsList = appsList;
    }

    public List<Appointment> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<Appointment> appsList) {
        this.appsList = appsList;
    }
}
