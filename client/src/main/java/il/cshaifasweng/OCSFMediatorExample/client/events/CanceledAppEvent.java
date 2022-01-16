package il.cshaifasweng.OCSFMediatorExample.client.events;

public class CanceledAppEvent {
    boolean canceled;

    public CanceledAppEvent(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
