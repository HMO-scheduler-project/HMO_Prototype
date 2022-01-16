package il.cshaifasweng.OCSFMediatorExample.server;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previous;
import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import java.time.ZoneId;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Reportscontroller {
    public static List<ServicesTypeRep> getServicesTypeRepFromDB(String clinicName) {
        Clinic clinic = clinicController.getClinicByName(clinicName);

        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<ServicesTypeRep> query = builder.createQuery(ServicesTypeRep.class);
        Root<ServicesTypeRep> root = query.from(ServicesTypeRep.class);
        query.select(root);
        query.where(builder.equal(root.get("Clinic"), clinic));
        return Main.session.createQuery(query).getResultList();
    }
    public static List<MissedAppRep> getMissedAppRepFromDB(String clinicName) {
    Clinic clinic = clinicController.getClinicByName(clinicName);

        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<MissedAppRep> query = builder.createQuery(MissedAppRep.class);
        Root<MissedAppRep> root = query.from(MissedAppRep.class);
        query.select(root);
        query.where(builder.equal(root.get("Clinic"), clinic));
        return Main.session.createQuery(query).getResultList();
 }

    public static List<AwaitingTimeRep> getAwaitingTimeRepFromDB(String clinicName) {
        Clinic clinic = clinicController.getClinicByName(clinicName);
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<AwaitingTimeRep> query = builder.createQuery(AwaitingTimeRep.class);
        Root<AwaitingTimeRep> root = query.from(AwaitingTimeRep.class);
        query.select(root);
        query.where(builder.equal(root.get("Clinic"), clinic));
        return Main.session.createQuery(query).getResultList();
    }
}






