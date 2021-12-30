package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class nearestAppsEvent {
    List<Appointment> appsList;

    public nearestAppsEvent(List<Appointment> appsList) {
        this.appsList = appsList;
    }

    public List<Appointment> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<Appointment> appsList) {
        this.appsList = appsList;
    }
}
