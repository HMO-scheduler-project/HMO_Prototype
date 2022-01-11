package il.cshaifasweng.OCSFMediatorExample.client.events;

public class logoutEvent {
    private String status;

    public logoutEvent(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
