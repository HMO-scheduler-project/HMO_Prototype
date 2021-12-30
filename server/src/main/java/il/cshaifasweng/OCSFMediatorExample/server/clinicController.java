package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Employee;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class clinicController {

    public static List<Clinic> getAllClinicsFromDB() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        query.from(Clinic.class);
        return Main.session.createQuery(query).getResultList();
    }

    public static List<String> getAllClinicNamesFromDB() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        Root<Clinic> root = query.from(Clinic.class);
        query.select(root);
        return Main.session.createQuery(query).getResultList().stream()
                .map((clinic -> clinic.getName())).collect(Collectors.toList());
    }

    public static Clinic getClinicByName (String name) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        Root<Clinic> root = query.from(Clinic.class);
        query.select(root);
        query.where(builder.equal(root.get("name"), name));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static LocalTime getOpenningHourByClinic(Clinic clinic){
        return clinic.getOpenningHour();
    }

    public static LocalTime getClosingHourByClinic(Clinic clinic){
        return clinic.getClosingHour();
    }

    public static String getAddressOfClinic(Clinic clinic){ return clinic.getAddress();}

    public static String getPhoneNumOfClinic(Clinic clinic){ return clinic.getPhoneNum();}
}
