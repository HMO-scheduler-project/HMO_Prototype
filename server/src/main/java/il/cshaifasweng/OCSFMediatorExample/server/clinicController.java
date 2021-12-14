package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Time;
import java.util.List;

public class clinicController {

    public static List<Clinic> getAllClinicsFromDB() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        query.from(Clinic.class);
        return Main.session.createQuery(query).getResultList();
    }

    public static Clinic getClinicByName (String name) {
        List<Clinic> clinics = getAllClinicsFromDB();
        for (Clinic clinic : clinics) {
            if (clinic.getName().equals(name)) {
                return clinic;
            }
        }
        return null;
    }

    public static Time getOpenningHourByClinic(Clinic clinic){
        return clinic.getOpenningHour();
    }

    public static Time getClosingHourByClinic(Clinic clinic){
        return clinic.getClosingHour();
    }
}
