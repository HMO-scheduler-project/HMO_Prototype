package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.LabApp;


public class labAppEvent extends LabApp{

    Appointment app;

    public labAppEvent(Appointment app) { this.app = app; }

    public Appointment getApp() { return app; }

    public void setApp(Appointment app) { this.app = app; }
}
