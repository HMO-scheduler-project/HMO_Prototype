package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.AwaitingTimeRep;

public class AwaitingTimeRepEvent {
private AwaitingTimeRep awaitingTimeRep;

    public AwaitingTimeRepEvent() {
    }

    public AwaitingTimeRepEvent(AwaitingTimeRep awaitingTimeRep) {
        this.awaitingTimeRep = awaitingTimeRep;
    }

    public AwaitingTimeRep getAwaitingTimeRep() {
        return awaitingTimeRep;
    }

    public void setAwaitingTimeRep(AwaitingTimeRep awaitingTimeRep) {
        this.awaitingTimeRep = awaitingTimeRep;
    }
}
