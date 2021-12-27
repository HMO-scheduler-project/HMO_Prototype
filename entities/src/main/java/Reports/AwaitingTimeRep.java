package Reports;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
public class AwaitingTimeRep extends WeeklyReport  {
    @Column(name = "Doctor")
    protected String DoctorName;
    @Column(name = "Average_Waiting_Time")
    protected  double AverageWaitingTime;
    @Column(name = "Number_Of_Patient")
    protected int Number_Of_Patient=0;
    public AwaitingTimeRep() {
        AverageWaitingTime=0;
    }

    public AwaitingTimeRep(String doctorName) {
        DoctorName = doctorName;
        AverageWaitingTime = 0;
    }

    public AwaitingTimeRep(String doctorName, double averageWaitingTime) {
        DoctorName = doctorName;
        AverageWaitingTime = averageWaitingTime;
    }


    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public double getAverageWaitingTime() {
        return AverageWaitingTime;
    }

    public void setAverageWaitingTime(double averageWaitingTime) {
        AverageWaitingTime = averageWaitingTime;
    }

    //all those below need to be on the server
    public void addAverageWaitingTime(double waitingtime) {
        AverageWaitingTime = (((AverageWaitingTime*Number_Of_Patient)+waitingtime)/(Number_Of_Patient+1));
        Number_Of_Patient++;
    }
    //remember that the Currentnumberofappointments needs to be increased by one in the server after this function

}
