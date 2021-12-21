package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Employee;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class clinicController {

    public static List<Clinic> getAllClinicsFromDB() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        query.from(Clinic.class);
        return Main.session.createQuery(query).getResultList();
    }

    public static ArrayList<String> getAllClinicNamesFromDB() {
        ArrayList<String> clinicsNames = new ArrayList();
        List<Clinic> clinics = getAllClinicsFromDB();
        for (Clinic clinic : clinics) {
            clinicsNames.add(clinic.getName());
        }
        return clinicsNames;
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

    public static LocalTime getOpenningHourByClinic(Clinic clinic){
        return clinic.getOpenningHour();
    }

    public static LocalTime getClosingHourByClinic(Clinic clinic){
        return clinic.getClosingHour();
    }

    public static String getAddressOfClinic(Clinic clinic){ return clinic.getAddress();}

    public static String getPhoneNumOfClinic(Clinic clinic){ return clinic.getPhoneNum();}
}
