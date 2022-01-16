package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.time.LocalDate;
import java.time.LocalTime;

public class SavedAppEvent {
    private boolean saved;
    private LocalTime time;
    private LocalDate date;
    private String clinicName;

    public SavedAppEvent(boolean saved, LocalTime time, LocalDate date, String clinicName) {
        this.saved = saved;
        this.time = time;
        this.date = date;
        this.clinicName = clinicName;
    }

    public SavedAppEvent(boolean saved) {
        this.saved=saved;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
