package Reports;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
//need extend WeeklyReport?
public class AwaitingTimeRep implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num")
    public int ID;
    @Column(name = "Doctor")
    String DoctorName;
    @Column(name = "Average_Waiting_Time")
    double AverageWaitingTime;

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

    public int getID() {
        return ID;
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
    public void addAverageWaitingTime(double waitingtime,int Currentnumberofappointments) {
        AverageWaitingTime = (((AverageWaitingTime*Currentnumberofappointments)+waitingtime)/(Currentnumberofappointments+1));
    }
    //remember that the Currentnumberofappointments needs to be increased by one in the server after this function

}
