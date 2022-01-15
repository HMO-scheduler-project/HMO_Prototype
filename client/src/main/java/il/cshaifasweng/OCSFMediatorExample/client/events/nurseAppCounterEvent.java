package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

public class nurseAppCounterEvent{

    Appointment app;
    long appCount;

    public nurseAppCounterEvent(Appointment app, long appCount) {
        this.app = app;
        this.appCount = appCount; }

    public long getAppCount() {
        return appCount;
    }

    public void setAppCount(long appCount) {
        this.appCount = appCount;
    }

    public Appointment getApp() {
        return app;
    }

    public void setApp(Appointment app) {
        this.app = app;
    }
}
