package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.AwaitingTimeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.MissedAppRep;
import il.cshaifasweng.OCSFMediatorExample.entities.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Reportscontroller
 * helper controller that contains helper function to search reports from database.
 *
 */

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






