package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class GreenPass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int greenpass_id;
    @OneToOne(targetEntity = User.class)
    protected User user;
    LocalDateTime issue_date;
    LocalDateTime expiration_date;

    public GreenPass(User user,LocalDateTime issue_date,LocalDateTime expiration_date) {
        this.user = user;
        this.issue_date = issue_date;
        this.expiration_date = expiration_date;
    }
    public GreenPass() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(LocalDateTime issue_date) {
        this.issue_date = issue_date;
    }

    public LocalDateTime getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDateTime expiration_date) {
        this.expiration_date = expiration_date;
    }
}
