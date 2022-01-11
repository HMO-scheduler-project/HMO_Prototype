package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.util.ArrayList;
import java.util.List;

public class ClinicListStationUpdateEvent {
    private List<String> clinicNames;

    public ClinicListStationUpdateEvent(List<String> clinicNames) {
        this.clinicNames = clinicNames;
    }

    public List<String> getClinicNames() {
        return clinicNames;
    }

    public void setClinicNames(ArrayList<String> clinicNames) {
        this.clinicNames = clinicNames;
    }
}
