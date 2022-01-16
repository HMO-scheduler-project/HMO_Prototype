package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.time.temporal.ChronoUnit;

public class appointmentTicketEvent {

    private Appointment app;
    private long ticketNum;

    public appointmentTicketEvent(Appointment app,long ticketNum) {
        app.setTime(app.getTime().truncatedTo(ChronoUnit.MINUTES));
        this.app = app;
        this.ticketNum=ticketNum;
    }

    public long getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(long ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Appointment getApp() {
        return app;
    }

    public void setApp(Appointment app) {
        this.app = app;
    }
}
