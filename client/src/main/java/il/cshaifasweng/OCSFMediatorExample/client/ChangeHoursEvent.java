package il.cshaifasweng.OCSFMediatorExample.client;

import java.time.LocalTime;

public class ChangeHoursEvent {
    private LocalTime openning_hour;
    private LocalTime closing_hour;

    public ChangeHoursEvent(LocalTime openning_hour,LocalTime closing_hour) {
        this.openning_hour = openning_hour;
        this.closing_hour = closing_hour;
    }

    public LocalTime getOpenning_hour() {
        return openning_hour;
    }

    public void setOpenning_hour(LocalTime openning_hour) {
        this.openning_hour = openning_hour;
    }

    public LocalTime getClosing_hour() {
        return closing_hour;
    }

    public void setClosing_hour(LocalTime closing_hour) {
        this.closing_hour = closing_hour;
    }
}
