package il.cshaifasweng.OCSFMediatorExample.client.events;

public class logoutFromStationEvent {
    private String status;

    public logoutFromStationEvent(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
