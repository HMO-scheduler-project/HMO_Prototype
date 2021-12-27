package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class specialDoctorApp extends doctorApp {
    @ManyToOne(targetEntity = SpecialDoctor.class)
    SpecialDoctor specialDoctor;

    public specialDoctorApp() {
        super();
    }

    @Override
    public SpecialDoctor getDoctor() {
        return specialDoctor;
    }

   // @Override
    public void setSpecialDoctor(SpecialDoctor specialDoctor) {
        this.specialDoctor = specialDoctor;
    }


}
