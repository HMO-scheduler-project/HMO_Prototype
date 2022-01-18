package il.cshaifasweng.OCSFMediatorExample.client.events;

public class printMessageToScreenEvent {
    int room;
    long count;

    public printMessageToScreenEvent(int room, long count) {
        this.room = room;
        this.count = count;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
