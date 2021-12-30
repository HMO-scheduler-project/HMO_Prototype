package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class appointmentController {

    public static List<Appointment> getNearestAppsFromDB(int user_id) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(root);
        query.where(builder.equal(root.get("patient_user_id"), user_id));
        return Main.session.createQuery(query).getResultList();
    }
}
