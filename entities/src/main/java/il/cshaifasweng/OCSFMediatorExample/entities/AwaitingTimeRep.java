package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.HMO_Manager;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class AwaitingTimeRep implements Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "Doctor")
    protected String DoctorName;
    @Column(name = "Sunday")
    protected  double AverageWaitingTime_A;
    @Column(name = "NOP_A")
    protected int Number_Of_Patient_A=0;
    @Column(name = "Monday")
    protected  double AverageWaitingTime_B;
    @Column(name = "NOP_B")
    protected int Number_Of_Patient_B=0;
    @Column(name = "Tuesday")
    protected  double AverageWaitingTime_C;
    @Column(name = "NOP_C")
    protected int Number_Of_Patient_C=0;
    @Column(name = "Wednesday")
    protected  double AverageWaitingTime_D;
    @Column(name = "NOP_D")
    protected int Number_Of_Patient_D=0;
    @Column(name = "Thursday")
    protected  double AverageWaitingTime_E;
    @Column(name = "NOP_E")
    protected int Number_Of_Patient_E=0;
    @Column(name = "Friday")
    protected  double AverageWaitingTime_F;
    @Column(name = "NOP_F")
    protected int Number_Of_Patient_F=0;
    @OneToOne( targetEntity = Clinic.class )
    private Clinic Clinic;
    @ManyToOne( targetEntity = HMO_Manager.class )
    protected HMO_Manager Hmo_Manager;
    public AwaitingTimeRep() {
        AverageWaitingTime_A=0;
        AverageWaitingTime_B=0;
        AverageWaitingTime_C=0;
        AverageWaitingTime_D=0;
        AverageWaitingTime_E=0;
        AverageWaitingTime_F=0;
    }

    public HMO_Manager getHmo_Manager() {
        return Hmo_Manager;
    }

    public void setHmo_Manager(HMO_Manager hmo_Manager) {
        Hmo_Manager = hmo_Manager;
    }

    public Clinic getClinic() {
        return Clinic;
    }

    public void setClinic(Clinic clinic) {
        Clinic = clinic;
    }

    public AwaitingTimeRep(String doctorName) {
        DoctorName = doctorName;
        AverageWaitingTime_A=0;
        AverageWaitingTime_B=0;
        AverageWaitingTime_C=0;
        AverageWaitingTime_D=0;
        AverageWaitingTime_E=0;
        AverageWaitingTime_F=0;
    }

    public AwaitingTimeRep(String doctorName,Clinic clinic, double averageWaitingTime_A, double averageWaitingTime_B, double averageWaitingTime_C, double averageWaitingTime_D, double averageWaitingTime_E, double averageWaitingTime_F,
                           int number_Of_Patient_A ,int number_Of_Patient_B ,int number_Of_Patient_C ,int number_Of_Patient_D ,int number_Of_Patient_E ,int number_Of_Patient_F) {
        DoctorName = doctorName;
        AverageWaitingTime_A=averageWaitingTime_A;
        AverageWaitingTime_B=averageWaitingTime_B;
        AverageWaitingTime_C=averageWaitingTime_C;
        AverageWaitingTime_D=averageWaitingTime_D;
        AverageWaitingTime_E=averageWaitingTime_E;
        AverageWaitingTime_F=averageWaitingTime_F;
        Number_Of_Patient_A=number_Of_Patient_A;
        Number_Of_Patient_B=number_Of_Patient_B;
        Number_Of_Patient_C=number_Of_Patient_C;
        Number_Of_Patient_D=number_Of_Patient_D;
        Number_Of_Patient_E=number_Of_Patient_E;
        Number_Of_Patient_F=number_Of_Patient_F;
        this.Clinic=clinic;
    }
    public AwaitingTimeRep(String doctorName, double averageWaitingTime_A, double averageWaitingTime_B, double averageWaitingTime_C, double averageWaitingTime_D, double averageWaitingTime_E, double averageWaitingTime_F) {
        DoctorName = doctorName;
        AverageWaitingTime_A=averageWaitingTime_A;
        AverageWaitingTime_B=averageWaitingTime_B;
        AverageWaitingTime_C=averageWaitingTime_C;
        AverageWaitingTime_D=averageWaitingTime_D;
        AverageWaitingTime_E=averageWaitingTime_E;
        AverageWaitingTime_F=averageWaitingTime_F;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }


    public double getAverageWaitingTime_A() {
        return AverageWaitingTime_A;
    }

    public void setAverageWaitingTime_A(double averageWaitingTime_A) {
        AverageWaitingTime_A = averageWaitingTime_A;
    }

    public int getNumber_Of_Patient_A() {
        return Number_Of_Patient_A;
    }

    public void setNumber_Of_Patient_A(int number_Of_Patient_A) {
        Number_Of_Patient_A = number_Of_Patient_A;
    }

    public double getAverageWaitingTime_B() {
        return AverageWaitingTime_B;
    }

    public void setAverageWaitingTime_B(double averageWaitingTime_B) {
        AverageWaitingTime_B = averageWaitingTime_B;
    }

    public int getNumber_Of_Patient_B() {
        return Number_Of_Patient_B;
    }

    public void setNumber_Of_Patient_B(int number_Of_Patient_B) {
        Number_Of_Patient_B = number_Of_Patient_B;
    }

    public double getAverageWaitingTime_C() {
        return AverageWaitingTime_C;
    }

    public void setAverageWaitingTime_C(double averageWaitingTime_C) {
        AverageWaitingTime_C = averageWaitingTime_C;
    }

    public int getNumber_Of_Patient_C() {
        return Number_Of_Patient_C;
    }

    public void setNumber_Of_Patient_C(int number_Of_Patient_C) {
        Number_Of_Patient_C = number_Of_Patient_C;
    }

    public double getAverageWaitingTime_D() {
        return AverageWaitingTime_D;
    }

    public void setAverageWaitingTime_D(double averageWaitingTime_D) {
        AverageWaitingTime_D = averageWaitingTime_D;
    }

    public int getNumber_Of_Patient_D() {
        return Number_Of_Patient_D;
    }

    public void setNumber_Of_Patient_D(int number_Of_Patient_D) {
        Number_Of_Patient_D = number_Of_Patient_D;
    }

    public double getAverageWaitingTime_E() {
        return AverageWaitingTime_E;
    }

    public void setAverageWaitingTime_E(double averageWaitingTime_E) {
        AverageWaitingTime_E = averageWaitingTime_E;
    }

    public int getNumber_Of_Patient_E() {
        return Number_Of_Patient_E;
    }

    public void setNumber_Of_Patient_E(int number_Of_Patient_E) {
        Number_Of_Patient_E = number_Of_Patient_E;
    }

    public double getAverageWaitingTime_F() {
        return AverageWaitingTime_F;
    }

    public void setAverageWaitingTime_F(double averageWaitingTime_F) {
        AverageWaitingTime_F = averageWaitingTime_F;
    }

    public int getNumber_Of_Patient_F() {
        return Number_Of_Patient_F;
    }

    public void setNumber_Of_Patient_F(int number_Of_Patient_F) {
        Number_Of_Patient_F = number_Of_Patient_F;
    }

}
