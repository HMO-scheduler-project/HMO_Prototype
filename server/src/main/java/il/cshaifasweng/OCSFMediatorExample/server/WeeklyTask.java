package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.AwaitingTimeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.MissedAppRep;
import il.cshaifasweng.OCSFMediatorExample.entities.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.SpecialDoctor;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.server.clinicController.getAllClinicsFromDB;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.TemporalAdjusters.previous;

/**
 * WeeklyTask
 * Creates new reports once a week when WeeklyReportService calls this class.
 */

public class WeeklyTask implements Runnable {

    @Override
    public void run() {

        System.out.println("start of weekly report");

        try {
            SessionFactory sessionFactory = Main.getSessionFactory();
            Main.session = sessionFactory.openSession();
            Main.session.beginTransaction();
            initReports();
            Main.session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (Main.session != null) {
                Main.session.getTransaction().rollback();
            }
            System.err.println("An error occurred, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if (Main.session != null) {
                Main.session.close();
            }
        }

        System.out.println("end of weekly report");


    }
    private static void initReports() {

        List<Clinic> ClinicList = getAllClinicsFromDB();
        LocalDate Saturday=GetLastSaturday();
        LocalDate Sunday=GetLastSunday(Saturday);
        ClearAwaitingTimeReport();
        ClearMissedAppReport();
        ClearServicesTypeReport();
        System.out.println("Getting Report Between the dates:"+Sunday+"And"+Saturday );
        for (Clinic clinic : ClinicList) {
            System.out.println(clinic.getNum()+"num");
            CreateMissedAppRepForClinic(clinic,Sunday,Saturday);
            CreateServicesTypeReportForClinic(clinic,Sunday,Saturday);
            CreateAwaitingTimeRepForClinic(clinic,Sunday,Saturday);
        }
    }

    public static void ClearServicesTypeReport() {

        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<ServicesTypeRep> query = builder.createQuery(ServicesTypeRep.class);
        query.from(ServicesTypeRep.class);
        int i=1;
        List<ServicesTypeRep> servicesTypeRep= Main.session.createQuery(query).getResultList();
        for( ServicesTypeRep item : servicesTypeRep){
           ;
             Main.session.delete(item);
        }
        Main.session.flush();
    }

    public static void ClearMissedAppReport() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<MissedAppRep> query = builder.createQuery(MissedAppRep.class);
        query.from(MissedAppRep.class);
        int i=1;
        List<MissedAppRep> missedAppReps= Main.session.createQuery(query).getResultList();
        for( MissedAppRep item : missedAppReps){
            Main.session.delete(item);
        }
        Main.session.flush();
    }

    public static void ClearAwaitingTimeReport() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<AwaitingTimeRep> query = builder.createQuery(AwaitingTimeRep.class);
        query.from(AwaitingTimeRep.class);
        int i=1;
        List<AwaitingTimeRep> awaitingTimeRep= Main.session.createQuery(query).getResultList();
        for( AwaitingTimeRep item : awaitingTimeRep){
            Main.session.delete(item);
        }
        Main.session.flush();
    }
    public static LocalDate GetLastSaturday(){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(today.atStartOfDay(defaultZoneId).toInstant()));
        int dayOfWeek8 = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek8<7)
            return today.with(previous(SATURDAY));
        return today;


    }
    public static LocalDate GetLastSunday(LocalDate Saturday){
        return Saturday.with(previous(SUNDAY));
    }
    public static void CreateServicesTypeReportForClinic(Clinic clinic,LocalDate Sunday,LocalDate Saturday) {
        CriteriaBuilder builder1 = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query1 = builder1.createQuery(Appointment.class);
        Root<Appointment> root1 = query1.from(Appointment.class);
        query1.multiselect(root1.get("clinic"), root1.get("appointment_id"), root1.get("time"), root1.get("arrived"), root1.get("date"), root1.get("employee"), root1.get("type"));
        query1.where(builder1.equal(root1.get("clinic"), clinic), builder1.equal(root1.get("arrived"), true), builder1.between(root1.<LocalDate>get("date"), Sunday,Saturday ));
        query1.orderBy(builder1.asc(root1.get("date")));
        List<Appointment> appointments = Main.session.createQuery(query1).getResultList();

        int[] covid_Test = {0, 0, 0, 0, 0, 0};
        int[] familyDoctor = {0, 0, 0, 0, 0, 0};
        int[] lab_Test_Appointments = {0, 0, 0, 0, 0, 0};
        int[] nurse_Care = {0, 0, 0, 0, 0, 0};
        int[] pediatrician = {0, 0, 0, 0, 0, 0};
        int[] vaccine_Appointments = {0, 0, 0, 0, 0, 0};
        int[] Special_doctor = {0, 0, 0, 0, 0, 0};
        ZoneId defaultZoneId = ZoneId.systemDefault();
        for (Appointment appointment : appointments) {
            LocalDate localDate = appointment.getDate();
            Calendar c = Calendar.getInstance();
            c.setTime(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));

            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if ((appointment.getType().equals("Doctor appointment")) && (appointment.getEmployee().getRole() == "pediatrician"))
                pediatrician[dayOfWeek - 1]++;
            if ((appointment.getType().equals("Doctor appointment")) && (appointment.getEmployee().getRole() == "family_doctor"))
                familyDoctor[dayOfWeek - 1]++;
            if ((appointment.getType().equals("Lab appointment")))
                lab_Test_Appointments[dayOfWeek - 1]++;
            if ((appointment.getType().equals("Nurse appointment")))
                nurse_Care[dayOfWeek - 1]++;
            if ((appointment.getType().equals("Covid test appointment")))
                covid_Test[dayOfWeek - 1]++;
            if (appointment.getEmployee().getRole().equals("Covid 19 vaccine appointment"))
                vaccine_Appointments[dayOfWeek - 1]++;
            if (appointment.getEmployee() instanceof SpecialDoctor) {
                Special_doctor[dayOfWeek - 1]++;
            }
        }


        ServicesTypeRep[] ReadyReport = new ServicesTypeRep[6];
        ReadyReport[0] = new ServicesTypeRep("Sunday", familyDoctor[0], pediatrician[0], vaccine_Appointments[0], lab_Test_Appointments[0], covid_Test[0], nurse_Care[0], Special_doctor[0], clinic);
        ReadyReport[1] = new ServicesTypeRep("Monday", familyDoctor[1], pediatrician[1], vaccine_Appointments[1], lab_Test_Appointments[1], covid_Test[1], nurse_Care[1], Special_doctor[1], clinic);
        ReadyReport[2] = new ServicesTypeRep("Tuesday", familyDoctor[2], pediatrician[2], vaccine_Appointments[2], lab_Test_Appointments[2], covid_Test[2], nurse_Care[2], Special_doctor[2], clinic);
        ReadyReport[3] = new ServicesTypeRep("Wednesday", familyDoctor[3], pediatrician[3], vaccine_Appointments[3], lab_Test_Appointments[3], covid_Test[3], nurse_Care[3], Special_doctor[3], clinic);
        ReadyReport[4] = new ServicesTypeRep("Thursday", familyDoctor[4], pediatrician[4], vaccine_Appointments[4], lab_Test_Appointments[4], covid_Test[4], nurse_Care[4], Special_doctor[4], clinic);
        ReadyReport[5] = new ServicesTypeRep("Friday", familyDoctor[5], pediatrician[5], vaccine_Appointments[5], lab_Test_Appointments[5], covid_Test[5], nurse_Care[5], Special_doctor[5], clinic);

        Main.session.save(ReadyReport[0]);
        Main.session.save(ReadyReport[1]);
        Main.session.save(ReadyReport[2]);
        Main.session.save(ReadyReport[3]);
        Main.session.save(ReadyReport[4]);
        Main.session.save(ReadyReport[5]);
        Main.session.flush();
    }

    public static void CreateMissedAppRepForClinic(Clinic clinic,LocalDate Sunday,LocalDate Saturday) {
        CriteriaBuilder builder1 = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query1 = builder1.createQuery(Appointment.class);
        Root<Appointment> root1 = query1.from(Appointment.class);
        query1.multiselect(root1.get("clinic"), root1.get("appointment_id"), root1.get("time"), root1.get("arrived"), root1.get("date"), root1.get("employee"), root1.get("type"));
        query1.where(builder1.equal(root1.get("clinic"), clinic), builder1.equal(root1.get("arrived"), false), builder1.between(root1.<LocalDate>get("date"), Sunday, Saturday));
        query1.orderBy(builder1.asc(root1.get("employee")));
        List<Appointment> appointments = Main.session.createQuery(query1).getResultList();
        int covid_Test = 0;
        int familyDoctor = 0;
        int lab_Test_Appointments = 0;
        int nurse_Care = 0;
        int pediatrician = 0;
        int vaccine_Appointments = 0;
        int Special_doctor = 0;

        for (Appointment appointment : appointments) {
            if ((appointment.getType().equals("Doctor appointment")) && (appointment.getEmployee().getRole() == "pediatrician"))
                pediatrician++;
            else if ((appointment.getType().equals("Doctor appointment")) && (appointment.getEmployee().getRole() == "family_doctor"))
                familyDoctor++;
            else if ((appointment.getType().equals("Lab appointment")))
                lab_Test_Appointments++;
            else if ((appointment.getType().equals("Nurse appointment")))
                nurse_Care++;
            else if ((appointment.getType().equals("Covid test appointment")))
                covid_Test++;
            else if (appointment.getEmployee().getRole().equals("Covid 19 vaccine appointment"))
                vaccine_Appointments++;
            if (appointment.getEmployee() instanceof SpecialDoctor) {
                Special_doctor++;
            }
        }
        System.out.println(clinic.getNum());
        MissedAppRep ReadyReport = new MissedAppRep(clinic, familyDoctor, pediatrician, vaccine_Appointments, lab_Test_Appointments, covid_Test, nurse_Care, Special_doctor);
        Main.session.save(ReadyReport);
        Main.session.flush();

    }

    public static void CreateAwaitingTimeRepForClinic(Clinic clinic,LocalDate Sunday,LocalDate Saturday) {
        CriteriaBuilder builder1 = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query1 = builder1.createQuery(Appointment.class);
        Root<Appointment> root1 = query1.from(Appointment.class);

        query1.multiselect(root1.get("time"), root1.get("actual_time"), root1.get("arrived"), root1.get("date"), root1.get("clinic"), root1.get("employee"));
        query1.where(builder1.equal(root1.get("clinic"), clinic), builder1.equal(root1.get("arrived"), true), builder1.between(root1.<LocalDate>get("date"), Sunday, Saturday));
        query1.orderBy(builder1.asc(root1.get("employee")));
        List<Appointment> appointments = Main.session.createQuery(query1).getResultList();

        int doctorId = -1;
        double[] averageOfTime = {0, 0, 0, 0, 0, 0};
        int[] counttheAppointments = {0, 0, 0, 0, 0, 0};
        ZoneId defaultZoneId = ZoneId.systemDefault();
        String CurUsername = null;
        System.out.println("arrived");
        for (Appointment appointment : appointments) {
            System.out.println(appointment.isArrived()+"arrived");
            LocalDate localDate = appointment.getDate();
            Calendar c = Calendar.getInstance();
            c.setTime(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);


            int CurrentWaitingTime = (int) MINUTES.between(appointment.getTime(), appointment.getActual_time());
            //first time and not last time
            if ((doctorId == -1) && (appointments.indexOf(appointment) != (appointments.size() - 1))) {
                doctorId = appointment.getEmployee().getUserId();
                averageOfTime[dayOfWeek - 1] = CurrentWaitingTime;
                counttheAppointments[dayOfWeek - 1] = 1;
                CurUsername = (appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName());
            }
            else if ((appointments.indexOf(appointment) == (appointments.size() - 1)) && (CurUsername == null)) {

                averageOfTime[dayOfWeek - 1] += CurrentWaitingTime;
                counttheAppointments[dayOfWeek - 1]++;
                CurUsername = (appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName());
                doctorId = appointment.getEmployee().getUserId();
                AwaitingTimeRep ReadyReport = new AwaitingTimeRep(CurUsername, clinic, averageOfTime[0], averageOfTime[1], averageOfTime[2], averageOfTime[3], averageOfTime[4], averageOfTime[5], counttheAppointments[0], counttheAppointments[1], counttheAppointments[2], counttheAppointments[3], counttheAppointments[4], counttheAppointments[5]);
                Main.session.save(ReadyReport);
                Main.session.flush();

            }
            else if ((doctorId == appointment.getEmployee().getUserId()) && (appointments.indexOf(appointment) != (appointments.size() - 1))) {
                averageOfTime[dayOfWeek - 1] += CurrentWaitingTime;
                counttheAppointments[dayOfWeek - 1]++;

                CurUsername = (appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName());
                doctorId = appointment.getEmployee().getUserId();

            }
            else if ((appointments.indexOf(appointment) != (appointments.size() - 1)) && (doctorId != appointment.getEmployee().getUserId())) {
                for (int j = 0; j < 6; j++) {
                    if (counttheAppointments[j] != 0)
                        averageOfTime[j] = averageOfTime[j] / counttheAppointments[j];
                }
                AwaitingTimeRep ReadyReport = new AwaitingTimeRep(CurUsername, clinic, averageOfTime[0], averageOfTime[1], averageOfTime[2], averageOfTime[3], averageOfTime[4], averageOfTime[5], counttheAppointments[0], counttheAppointments[1], counttheAppointments[2], counttheAppointments[3], counttheAppointments[4], counttheAppointments[5]);
                Main.session.save(ReadyReport);
                Main.session.flush();
                doctorId = appointment.getEmployee().getUserId();
                CurUsername = (appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName());
                for (int j = 0; j < 6; j++) {
                    if (j == (dayOfWeek - 1)) {
                        averageOfTime[dayOfWeek - 1] = CurrentWaitingTime;
                        counttheAppointments[dayOfWeek - 1] = 1;
                    } else {
                        averageOfTime[dayOfWeek - 1] = 0;
                        counttheAppointments[dayOfWeek - 1] = 0;
                    }
                }

            }
            else if (((appointments.indexOf(appointment) == (appointments.size() - 1)) && (doctorId != appointment.getEmployee().getUserId()))) {
                for (int j = 0; j < 6; j++) {
                    if (counttheAppointments[j] != 0)
                        averageOfTime[j] = averageOfTime[j] / counttheAppointments[j];
                }
                AwaitingTimeRep ReadyReport = new AwaitingTimeRep(CurUsername, clinic, averageOfTime[0], averageOfTime[1], averageOfTime[2], averageOfTime[3], averageOfTime[4], averageOfTime[5], counttheAppointments[0], counttheAppointments[1], counttheAppointments[2], counttheAppointments[3], counttheAppointments[4], counttheAppointments[5]);
                Main.session.save(ReadyReport);
                Main.session.flush();
                doctorId = appointment.getEmployee().getUserId();
                CurUsername = (appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName());
                for (int j = 0; j < 6; j++) {
                    if (j == (dayOfWeek - 1)) {
                        averageOfTime[dayOfWeek - 1] = CurrentWaitingTime;
                        counttheAppointments[dayOfWeek - 1] = 1;
                    } else {
                        averageOfTime[dayOfWeek - 1] = 0;
                        counttheAppointments[dayOfWeek - 1] = 0;
                    }
                }
                ReadyReport = new AwaitingTimeRep(CurUsername, clinic, averageOfTime[0], averageOfTime[1], averageOfTime[2], averageOfTime[3], averageOfTime[4], averageOfTime[5], counttheAppointments[0], counttheAppointments[1], counttheAppointments[2], counttheAppointments[3], counttheAppointments[4], counttheAppointments[5]);
                Main.session.save(ReadyReport);
                Main.session.flush();

            }
            else if ((appointments.indexOf(appointment) == (appointments.size() - 1)) && (doctorId == appointment.getEmployee().getUserId())) {
                averageOfTime[dayOfWeek - 1] += CurrentWaitingTime;
                counttheAppointments[dayOfWeek - 1]++;

                for (int j = 0; j < 6; j++) {
                    if (counttheAppointments[j] != 0)
                        averageOfTime[j] = averageOfTime[j] / counttheAppointments[j];
                }
                AwaitingTimeRep ReadyReport = new AwaitingTimeRep(CurUsername, clinic, averageOfTime[0], averageOfTime[1], averageOfTime[2], averageOfTime[3], averageOfTime[4], averageOfTime[5], counttheAppointments[0], counttheAppointments[1], counttheAppointments[2], counttheAppointments[3], counttheAppointments[4], counttheAppointments[5]);
                Main.session.save(ReadyReport);
                Main.session.flush();

            }

            doctorId = appointment.getEmployee().getUserId();
        }
}
}



