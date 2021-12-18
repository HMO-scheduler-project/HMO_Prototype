package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class clinicController {

    public static void InitClinicTable() throws NoSuchAlgorithmException {
        Clinic clinic1 = new Clinic("Denia","Haifa",LocalTime.parse("07:00:00"), LocalTime.parse("17:00:00"),2,(Manager) userController.getEmployee(2));
        Main.session.save(clinic1);
        Main.session.flush();
        Clinic clinic2 = new Clinic("Neve shaanan","Haifa",LocalTime.parse("07:00:00"),LocalTime.parse("20:00:00"),3,(Manager) userController.getEmployee(3));
        Main.session.save(clinic2);
        Main.session.flush();
        Clinic clinic3 = new Clinic("Hadar","Haifa",LocalTime.parse("07:00:00"),LocalTime.parse("19:00:00"),6,(Manager) userController.getEmployee(6));
        Main.session.save(clinic3);
        Main.session.flush();
        Clinic clinic4 = new Clinic("Nesher","Nesher",LocalTime.parse("07:00:00"),LocalTime.parse("19:00:00"),5,(Manager) userController.getEmployee(5));
        Main.session.save(clinic4);
        Main.session.flush();
        Clinic clinic5 = new Clinic("Carmel","Haifa",LocalTime.parse("07:00:00"),LocalTime.parse("20:00:00"),8,(Manager) userController.getEmployee(8));
        Main.session.save(clinic5);
        Main.session.flush();
        Clinic clinic6 = new Clinic("Tirat Carmel","Tirat Carmel",LocalTime.parse("07:00:00"),LocalTime.parse("17:00:00"),10,(Manager) userController.getEmployee(10));
        Main.session.save(clinic6);
        Main.session.flush();
    }


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
        if(clinicsNames.isEmpty()){
            System.out.println("clinicNames is empty");
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
}
