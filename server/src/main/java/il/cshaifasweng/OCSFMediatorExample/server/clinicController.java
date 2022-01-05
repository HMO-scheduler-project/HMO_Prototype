package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalTime;
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
                .map((Clinic::getName)).collect(Collectors.toList());
    }


    public static Clinic getClinicByName (String name) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        Root<Clinic> root = query.from(Clinic.class);
        query.select(root);
        query.where(builder.equal(root.get("name"), name));
        return Main.session.createQuery(query).getSingleResult();
    }



    public static List<String> getServicesList(String name) {
        Clinic clinic = getClinicByName(name);
        List<String> services = null;
        services.add("doctor appointments");
        services.add("nurse");
        if(clinic.hasLabServices()){
            services.add("lab");
        }
        if(clinic.hasCovidTestService()){
            services.add("covid test");
        }
        if(clinic.hasCovidVaccine()){
            services.add("covid vaccine");
        }
        if(clinic.hasInfluenzaVaccine()){
            services.add("influenza vaccine");
        }
        if(clinic.hasSpecialists()){
            services.add("specialists");
        }
        return services;
    }

//    public static List<String> getDoctorsofClinic(String name){
//        Clinic clinic = getClinicByName(name);
//
//    }

    public static LocalTime getOpeningHourByClinic(Clinic clinic){
        return clinic.getOpeningHour();
    }

    public static LocalTime getClosingHourByClinic(Clinic clinic){
        return clinic.getClosingHour();
    }

    public static String getAddressOfClinic(Clinic clinic){ return clinic.getAddress();}

    public static String getPhoneNumOfClinic(Clinic clinic){ return clinic.getPhoneNum();}
}
