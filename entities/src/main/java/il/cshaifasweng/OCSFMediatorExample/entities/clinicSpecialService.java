package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class clinicSpecialService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int service_id;
    protected String service;
    protected String clinic;
    protected LocalTime start;
    protected LocalTime end;
    protected int room_num;

    public clinicSpecialService(String service, String clinic, LocalTime start, LocalTime end,int room_num) {
        this.service = service;
        this.clinic = clinic;
        this.start = start;
        this.end = end;
        this.room_num = room_num;
    }

    public clinicSpecialService() {

    }

    public int getService_id() {
        return service_id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }
}

