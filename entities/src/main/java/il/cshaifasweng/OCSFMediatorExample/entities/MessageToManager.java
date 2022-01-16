package il.cshaifasweng.OCSFMediatorExample.entities;

import jdk.jfr.Enabled;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class MessageToManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    String from;
    String to;
    String title;
    String body;
    boolean read;
    LocalDate date;

    public MessageToManager(String from,String to, String title, String body) {
        this.from = from;
        this.to = to;
        this.title = title;
        this.body = body;
        this.read = false;
        this.date = LocalDate.now();
    }

    public MessageToManager() {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
