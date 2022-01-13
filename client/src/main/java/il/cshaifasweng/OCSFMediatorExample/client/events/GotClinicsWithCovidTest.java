package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import java.util.List;

public class GotClinicsWithCovidTest {
    List<String> clinicNames;

    public GotClinicsWithCovidTest(List<String> clinicNames) {
        this.clinicNames = clinicNames;
    }

    public List<String> getClinicNames() {
        return clinicNames;
    }

    public void setClinicNames(List<String> clinicNames) {
        this.clinicNames = clinicNames;
    }
}
