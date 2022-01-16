package il.cshaifasweng.OCSFMediatorExample.client.events;

public class showMessageEvent {
    String message;

    public showMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
