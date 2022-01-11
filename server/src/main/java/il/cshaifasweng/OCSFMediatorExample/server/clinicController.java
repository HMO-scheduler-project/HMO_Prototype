package il.cshaifasweng.OCSFMediatorExample.server;

import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Employee;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.print.Doc;
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

    public static List<String> getManagedClinicNames(Manager manager){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        Root<Clinic> root = query.from(Clinic.class);
        query.select(root);
        query.where(builder.equal(root.get("manager"), manager));
        return Main.session.createQuery(query).getResultList().stream()
                .map((Clinic::getName)).collect(Collectors.toList());
    }

    public static List<String> getServicesList(String name) {
        Clinic clinic = getClinicByName(name);
        List<String> services = new ArrayList<>();
        services.add(("clinic"));
        services.add("doctors");
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

    public static List<String> getDoctorsofClinic(String name){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = builder.createQuery(Doctor.class);
        Root<Doctor> root = query.from(Doctor.class);
        query.select(root);
        query.where(builder.equal(root.get("main_clinic"), name));
        return Main.session.createQuery(query).getResultList().stream()
                .map((Doctor::getFullName)).collect(Collectors.toList());

    }

    public static List<String> getSpecialDoctorsofClinic(String name){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<SpecialDoctor> query = builder.createQuery(SpecialDoctor.class);
        Root<SpecialDoctor> root = query.from(SpecialDoctor.class);
        query.select(root);
        query.where(builder.like(root.get("main_clinic"), "%"+name+"%"));
        return Main.session.createQuery(query).getResultList().stream()
                .map((SpecialDoctor::getFullName)).collect(Collectors.toList());
    }

    public static clinicSpecialService getService(String service,String clinic_name){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<clinicSpecialService> query = builder.createQuery(clinicSpecialService.class);
        Root<clinicSpecialService> root = query.from(clinicSpecialService.class);
        query.select(root);
        query.where(builder.equal(root.get("service"), service),builder.equal(root.get("clinic"),clinic_name));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static LabWorker getLabWorkerByClinic(String clinic_name){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<LabWorker> query = builder.createQuery(LabWorker.class);
        Root<LabWorker> root = query.from(LabWorker.class);
        query.select(root);
        query.where(builder.equal(root.get("main_clinic"),clinic_name));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static Nurse getNurseByClinic(String clinic_name){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Nurse> query = builder.createQuery(Nurse.class);
        Root<Nurse> root = query.from(Nurse.class);
        query.select(root);
        query.where(builder.equal(root.get("main_clinic"),clinic_name));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static LocalTime getOpeningHourByClinic(Clinic clinic){
        return clinic.getOpeningHour();
    }

    public static LocalTime getClosingHourByClinic(Clinic clinic){
        return clinic.getClosingHour();
    }

    public static String getAddressOfClinic(Clinic clinic){ return clinic.getAddress();}

    public static String getPhoneNumOfClinic(Clinic clinic){ return clinic.getPhoneNum();}


    public static AwaitingTimeRep getAwaitingTimeRepByClinic(Clinic clinic){ return clinic.getAwaitingTimeRep();}
    public static MissedAppRep getMissedAppRepByClinic(Clinic clinic){ return clinic.getMissedAppRep();}
    public static ServicesTypeRep getServicesTypeRepByClinic(Clinic clinic){ return clinic.getServicesTypeRep();}
}
