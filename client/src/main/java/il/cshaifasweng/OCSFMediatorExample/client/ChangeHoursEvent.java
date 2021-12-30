package il.cshaifasweng.OCSFMediatorExample.client;

import java.time.LocalTime;

public class ChangeHoursEvent {
    private LocalTime opening_hour;
    private LocalTime closing_hour;

    public ChangeHoursEvent(LocalTime opening_hour,LocalTime closing_hour) {
        this.opening_hour = opening_hour;
        this.closing_hour = closing_hour;
    }

    public LocalTime getOpening_hour() {
        return opening_hour;
    }

    public void setOpening_hour(LocalTime opening_hour) {
        this.opening_hour = opening_hour;
    }

    public LocalTime getClosing_hour() {
        return closing_hour;
    }

    public void setClosing_hour(LocalTime closing_hour) {
        this.closing_hour = closing_hour;
    }
}
