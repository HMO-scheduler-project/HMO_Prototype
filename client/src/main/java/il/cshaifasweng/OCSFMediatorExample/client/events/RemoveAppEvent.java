package il.cshaifasweng.OCSFMediatorExample.client.events;

public class RemoveAppEvent {

    protected boolean isRemoved;

    public RemoveAppEvent(boolean isremoved) {
        this.isRemoved = isremoved;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}
