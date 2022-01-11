package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.time.temporal.ChronoUnit;

public class appointmentTicketEvent {

    Appointment app;

    public appointmentTicketEvent(Appointment app) {
        app.setTime(app.getTime().truncatedTo(ChronoUnit.MINUTES));
        this.app = app;
    }

    public Appointment getApp() {
        return app;
    }

    public void setApp(Appointment app) {
        this.app = app;
    }
}
