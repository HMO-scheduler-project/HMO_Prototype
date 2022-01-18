package il.cshaifasweng.OCSFMediatorExample.client.events;

public class clinicClientEvent {
    private boolean listed;

    public boolean isListed() {
        return listed;
    }
    public void setListed(boolean listed) {
        this.listed = listed;
    }
    public clinicClientEvent(boolean listed) {
        this.listed = listed;
    }
}
