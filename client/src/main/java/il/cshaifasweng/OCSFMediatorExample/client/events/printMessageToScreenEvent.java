package il.cshaifasweng.OCSFMediatorExample.client.events;

public class printMessageToScreenEvent {
    int room;
    String patient_name;

    public printMessageToScreenEvent(int room, String patient_name) {
        this.room = room;
        this.patient_name = patient_name;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
}
