package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.util.ArrayList;
import java.util.List;

public class ManagedClinicListUpdateEvent {
    private List<String> clinicNames;

    public ManagedClinicListUpdateEvent(List<String> clinicNames) {
        this.clinicNames = clinicNames;
    }

    public List<String> getClinicNames() {
        return clinicNames;
    }

    public void setClinicNames(ArrayList<String> clinicNames) {
        this.clinicNames = clinicNames;
    }
}
