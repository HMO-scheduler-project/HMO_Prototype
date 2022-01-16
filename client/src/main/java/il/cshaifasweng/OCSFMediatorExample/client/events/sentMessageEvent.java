package il.cshaifasweng.OCSFMediatorExample.client.events;

public class sentMessageEvent {
    boolean sent;

    public sentMessageEvent(boolean sent) {
        this.sent = sent;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
