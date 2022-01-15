package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.util.ArrayList;
import java.util.List;

public class DoctorListUpdateEvent {
    private List<String> doctorsList;

    public DoctorListUpdateEvent(List<String> doctorsList) {
        this.doctorsList = doctorsList;
    }

    public List<String> getDoctorsList() {
        return doctorsList;
    }

    public void setDoctorsList(ArrayList<String> doctorsList) {
        this.doctorsList = doctorsList;
    }
}
