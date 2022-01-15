package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import java.time.LocalDate;

public class BirthDateClinicEvent {
    private LocalDate birthDate;
    private Clinic clinic;
    private int age;

    public BirthDateClinicEvent(LocalDate birthDate, Clinic clinic,int age) {
        this.birthDate = birthDate;
        this.clinic = clinic;
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
