package il.cshaifasweng.OCSFMediatorExample.client;

public class ChangeRoomEvent {
    int room;

    public ChangeRoomEvent(int room) {
        this.room = room;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
