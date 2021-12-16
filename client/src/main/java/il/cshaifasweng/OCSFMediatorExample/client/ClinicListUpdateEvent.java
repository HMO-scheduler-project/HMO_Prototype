package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.ArrayList;

public class ClinicListUpdateEvent {
    private ArrayList<String> clinicNames;

    public ClinicListUpdateEvent(ArrayList<String> clinicNames) {
        this.clinicNames = clinicNames;
    }

    public ArrayList<String> getClinicNames() {
        return clinicNames;
    }

    public void setClinicNames(ArrayList<String> clinicNames) {
        this.clinicNames = clinicNames;
    }
}
