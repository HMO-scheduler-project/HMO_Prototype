package il.cshaifasweng.OCSFMediatorExample.client;

import java.sql.Time;

public class ChangeHoursEvent {
    private Time openning_hour;
    private Time closing_hour;

    public ChangeHoursEvent(Time openning_hour,Time closing_hour) {
        this.openning_hour = openning_hour;
        this.closing_hour = closing_hour;
    }

    public Time getOpenning_hour() {
        return openning_hour;
    }

    public void setOpenning_hour(Time openning_hour) {
        this.openning_hour = openning_hour;
    }

    public Time getClosing_hour() {
        return closing_hour;
    }

    public void setClosing_hour(Time closing_hour) {
        this.closing_hour = closing_hour;
    }
}
