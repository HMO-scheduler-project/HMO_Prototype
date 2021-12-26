package Reports;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class AwaitingTimeRep extends WeeklyReport  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num")
    public int ID;
    @Column(name = "Doctor")
    String DoctorName;
    @Column(name = "Average Waiting Time")
    String AverageWaitingTime;

    public AwaitingTimeRep() {
        super();
    }
    public AwaitingTimeRep(String doctorName, String averageWaitingTime) {
        DoctorName = doctorName;
        AverageWaitingTime = averageWaitingTime;
    }

    public int getID() {
        return ID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getAverageWaitingTime() {
        return AverageWaitingTime;
    }

    public void setAverageWaitingTime(String averageWaitingTime) {
        AverageWaitingTime = averageWaitingTime;
    }
}
