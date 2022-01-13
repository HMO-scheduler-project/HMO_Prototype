package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.util.List;

public class GotClinicsWithVaccine {
    List<String> clinicNames;

    public GotClinicsWithVaccine(List<String> clinicNames) {
        this.clinicNames = clinicNames;
    }

    public List<String> getClinicNames() {
        return clinicNames;
    }

    public void setClinicNames(List<String> clinicNames) {
        this.clinicNames = clinicNames;
    }
}
