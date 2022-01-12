package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.SpecialDoctor;

import java.util.List;

public class GotSpecialDocEvent {
    private List<SpecialDoctor> specialDoctorList;

    public GotSpecialDocEvent(List<SpecialDoctor> specialDoctorList) {
        this.specialDoctorList = specialDoctorList;
    }

    public List<SpecialDoctor> getSpecialDoctorList() {
        return specialDoctorList;
    }

    public void setSpecialDoctorList(List<SpecialDoctor> specialDoctorList) {
        this.specialDoctorList = specialDoctorList;
    }
}
