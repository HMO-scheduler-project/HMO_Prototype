package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class nearestAppsEvent {
    List<Appointment> appsList;

    public nearestAppsEvent(List<Appointment> appsList) {
        for(Appointment app : appsList){
            app.setTime(app.getTime().truncatedTo(ChronoUnit.MINUTES));
        }
        this.appsList = appsList;
    }

    public List<Appointment> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<Appointment> appsList) {
        this.appsList = appsList;
    }
}
