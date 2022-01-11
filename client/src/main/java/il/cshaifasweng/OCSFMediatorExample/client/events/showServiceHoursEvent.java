package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.time.LocalTime;

public class showServiceHoursEvent {
    LocalTime start;
    LocalTime finish;
    int room;

    public showServiceHoursEvent(LocalTime start,LocalTime finish,int room_num) {
        this.start = start;
        this.finish = finish;
        this.room = room_num;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getFinish() {
        return finish;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
