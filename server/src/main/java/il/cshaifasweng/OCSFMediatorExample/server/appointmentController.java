package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Employee;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class appointmentController {

    public static List<Appointment> getNearestAppsFromDB(User user) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(root);
        query.where(builder.equal(root.get("patient"), user));
        return Main.session.createQuery(query).getResultList();
    }

    public static Appointment findAppinDB(int card_num, LocalDate date){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(root);
        query.where(builder.equal(root.get("card num"), card_num),builder.equal(root.get("date"),date));
        return Main.session.createQuery(query).getSingleResult();
    }

//    public static List<Appointment> getPatientListFromDB(Employee employee){
//
//    }
}
