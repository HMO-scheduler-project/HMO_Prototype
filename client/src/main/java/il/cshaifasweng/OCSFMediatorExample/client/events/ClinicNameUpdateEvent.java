package il.cshaifasweng.OCSFMediatorExample.client.events;

public class ClinicNameUpdateEvent {
    private String clinicName;

    public ClinicNameUpdateEvent(String clinicName) {
        this.clinicName = clinicName;
    }

    public ClinicNameUpdateEvent() {
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
