package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;
import java.util.List;

public class ClinicListUpdateEvent {
    private List<String> clinicNames;

    public ClinicListUpdateEvent(List<String> clinicNames) {
        this.clinicNames = clinicNames;
    }

    public List<String> getClinicNames() {
        return clinicNames;
    }

    public void setClinicNames(ArrayList<String> clinicNames) {
        this.clinicNames = clinicNames;
    }
}
