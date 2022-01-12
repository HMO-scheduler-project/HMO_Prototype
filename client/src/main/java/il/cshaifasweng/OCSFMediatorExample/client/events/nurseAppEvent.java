package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

public class nurseAppEvent{

    Appointment app;

    public nurseAppEvent(Appointment app) { this.app = app; }

    public Appointment getApp() {
        return app;
    }

    public void setApp(Appointment app) {
        this.app = app;
    }
}
