package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

public class nurseAppCounterEvent extends nurseAppEvent{

    Appointment app;
    long appCount;

    public nurseAppCounterEvent(Appointment app, long appCount) {
        super(app);
        this.appCount = appCount; }

    public long getAppCount() {
        return appCount;
    }

    public void setAppCount(long appCount) {
        this.appCount = appCount;
    }
}
