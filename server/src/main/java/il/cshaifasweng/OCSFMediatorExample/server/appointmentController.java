package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class appointmentController {

    public static List<Appointment> getNearestAppsFromDB(User user) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(root);
        query.where(builder.equal(root.get("patient"), user),builder.equal(root.get("arrived"),
                false),builder.greaterThanOrEqualTo(root.get("date"),LocalDate.now()));
        query.orderBy(builder.asc(root.get("date")),builder.asc(root.get("time")));
        return Main.session.createQuery(query).getResultList();
    }

    public static Appointment findAppinDB(String card_num, LocalDate date){
        Patient patient = userController.getPatientByCardNum(card_num);
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(root);
        query.where(builder.equal(root.get("patient"), patient),builder.equal(root.get("date"),date));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static List<Appointment> getPatientListFromDB(Employee employee){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(root);
        query.where(builder.equal(root.get("employee"), employee),builder.equal(root.get("date"),LocalDate.now()));
        query.orderBy(builder.asc(root.get("time")));
        return Main.session.createQuery(query).getResultList();
    }

    public static Long countAppPerDay(Employee employee,LocalDate date){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(builder.count(root));
        query.where(builder.equal(root.get("employee"), employee),builder.equal(root.get("date"),date));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static Appointment getAppointments(String clinic_name,String username){
        Patient patient = userController.getPatientByUsername(username);
        Clinic clinic = clinicController.getClinicByName(clinic_name);
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root<Appointment> root = query.from(Appointment.class);
        query.select(root);
        query.where(builder.equal(root.get("patient"), patient),builder.equal(root.get("clinic"),clinic),builder.equal(root.get("date"),LocalDate.now()),builder.equal(root.get("arrived"),false));
        query.orderBy(builder.asc(root.get("time")));
        return Main.session.createQuery(query).getResultList().get(0);
    }
}
