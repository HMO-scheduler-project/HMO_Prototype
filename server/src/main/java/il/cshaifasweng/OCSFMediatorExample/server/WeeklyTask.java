package il.cshaifasweng.OCSFMediatorExample.server;

import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.TemporalAdjusters.previous;

public class WeeklyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("start of weekly report");
        initReports();
        System.out.println("end of weekly report");
    }
    private static void initReports() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Clinic> query = builder.createQuery(Clinic.class);
        query.from(Clinic.class);
        List<Clinic> ClinicList = Main.session.createQuery(query).getResultList();


        ClearAwaitingTimeReport();
        ClearMissedAppReport();
        ClearServicesTypeReport();
        for (Clinic clinic : ClinicList) {


            CreateServicesTypeReportForClinic(clinic);
            CreateAwaitingTimeRepForClinic(clinic);
            CreateMissedAppRepForClinic(clinic);


        }
    }

    public static int ClearServicesTypeReport(){
        //clear the report table
        CriteriaBuilder criteriaBuilder = Main.session.getCriteriaBuilder();
        CriteriaDelete<ServicesTypeRep> query = criteriaBuilder.createCriteriaDelete(ServicesTypeRep.class);
        Root<ServicesTypeRep> root = query.from(ServicesTypeRep.class);
        int result = Main.session.createQuery(query).executeUpdate();
        return result;
    }
    public static int ClearMissedAppReport(){
        //clear the report table
        CriteriaBuilder criteriaBuilder = Main.session.getCriteriaBuilder();
        CriteriaDelete<MissedAppRep> query = criteriaBuilder.createCriteriaDelete(MissedAppRep.class);
        Root<MissedAppRep> root = query.from(MissedAppRep.class);
        int result = Main.session.createQuery(query).executeUpdate();
        return result;
    }
    public static int ClearAwaitingTimeReport(){
        CriteriaBuilder criteriaBuilder = Main.session.getCriteriaBuilder();
        CriteriaDelete<AwaitingTimeRep> query = criteriaBuilder.createCriteriaDelete(AwaitingTimeRep.class);
        Root<AwaitingTimeRep> root = query.from(AwaitingTimeRep.class);
        int result = Main.session.createQuery(query).executeUpdate();
        return result;

    }


    public static void  CreateServicesTypeReportForClinic(Clinic clinic){

        ZoneId defaultZoneId1 = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(Date.from(today.atStartOfDay(defaultZoneId1).toInstant()));
        //FOR EVERY CLINIC HOW MANY PATIENTS FOR EACH TYPE EACH DAY
        ///first get today's date and sundays date
        // LocalDate nextSunday = today.with(next(SUNDAY));
        LocalDate thisPastSunday = today.with(previous(SUNDAY));

//gets the query with the needed data
        CriteriaBuilder builder1 = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query1 = builder1.createQuery(Appointment.class);
        Root<Appointment> root1 = query1.from(Appointment.class);
        query1.multiselect(root1.get("clinic"),root1.get("appointment_id"),root1.get("time"),  root1.get("arrived"), root1.get("date"),root1.get("employee"),root1.get("type"));
        query1.where(builder1.equal(root1.get("clinic"), clinic), builder1.equal(root1.get("arrived"), true), builder1.between(root1.<LocalDate>get("date"), thisPastSunday, today));
        query1.orderBy(builder1.asc(root1.get("date")));
        List<Appointment> appointments = Main.session.createQuery(query1).getResultList();


//1. initialise every field to zero
//        2. for every absence ++
        int[] covid_Test = {0, 0, 0, 0, 0, 0};
        int[] familyDoctor = {0, 0, 0, 0, 0, 0};
        int[] lab_Test_Appointments = {0, 0, 0, 0, 0, 0};
        int[] nurse_Care = {0, 0, 0, 0, 0, 0};
        int[] pediatrician = {0, 0, 0, 0, 0, 0};
        int[] vaccine_Appointments = {0, 0, 0, 0, 0, 0};

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

        }


        ServicesTypeRep[] ReadyReport = new ServicesTypeRep[6];
        ReadyReport[0] = new ServicesTypeRep("Sunday", familyDoctor[0], pediatrician[0], vaccine_Appointments[0], lab_Test_Appointments[0], covid_Test[0], nurse_Care[0],clinic);
        ReadyReport[1] = new ServicesTypeRep("Monday", familyDoctor[1], pediatrician[1], vaccine_Appointments[1], lab_Test_Appointments[1], covid_Test[1], nurse_Care[1],clinic);
        ReadyReport[2] = new ServicesTypeRep("Tuesday", familyDoctor[2], pediatrician[2], vaccine_Appointments[2], lab_Test_Appointments[2], covid_Test[2], nurse_Care[2],clinic);
        ReadyReport[3] = new ServicesTypeRep("Wednesday", familyDoctor[3], pediatrician[3], vaccine_Appointments[3], lab_Test_Appointments[3], covid_Test[3], nurse_Care[3],clinic);
        ReadyReport[4] = new ServicesTypeRep("Thursday", familyDoctor[4], pediatrician[4], vaccine_Appointments[4], lab_Test_Appointments[4], covid_Test[4], nurse_Care[4],clinic);
        ReadyReport[5] = new ServicesTypeRep("Friday", familyDoctor[5], pediatrician[5], vaccine_Appointments[5], lab_Test_Appointments[5], covid_Test[5], nurse_Care[5],clinic);


        //= new []ServicesTypeRep("Sunday", familyDoctor, pediatrician, vaccine_Appointments, lab_Test_Appointments, covid_Test, nurse_Care);
        Main.session.save(ReadyReport[0]);
        Main.session.save(ReadyReport[1]);
        Main.session.save(ReadyReport[2]);
        Main.session.save(ReadyReport[3]);
        Main.session.save(ReadyReport[4]);
        Main.session.save(ReadyReport[5]);
        Main.session.flush();
    }

    public static void CreateMissedAppRepForClinic(Clinic clinic) {
        ZoneId defaultZoneId1 = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(Date.from(today.atStartOfDay(defaultZoneId1).toInstant()));
        // LocalDate nextSunday = today.with(next(SUNDAY));
        LocalDate thisPastSunday = today.with(previous(SUNDAY));

        //gets the query with the needed data
        CriteriaBuilder builder1 = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query1 = builder1.createQuery(Appointment.class);
        Root<Appointment> root1 = query1.from(Appointment.class);
        query1.multiselect(root1.get("clinic"), root1.get("appointment_id"), root1.get("time"), root1.get("arrived"),root1.get("date"),root1.get("employee"),root1.get("type"));
        query1.where(builder1.equal(root1.get("clinic"), clinic), builder1.equal(root1.get("arrived"), false), builder1.between(root1.<LocalDate>get("date"), thisPastSunday, today));
        //builder1.equal(root1.get("clinic_Num"), clinic.getNum())
        //employee_user_id

        query1.orderBy(builder1.asc(root1.get("employee")));
        List<Appointment> appointments = Main.session.createQuery(query1).getResultList();
        //1. initialise every field to zero
//        2. for every absence ++
        int covid_Test = 0;
        int familyDoctor = 0;
        int lab_Test_Appointments = 0;
        int nurse_Care = 0;
        int pediatrician = 0;
        int vaccine_Appointments = 0;


        for (Appointment appointment : appointments) {

            if ((appointment.getType().equals("Doctor appointment")) && (appointment.getEmployee().getRole() == "pediatrician"))
                pediatrician++;
            if ((appointment.getType().equals("Doctor appointment")) && (appointment.getEmployee().getRole() == "family_doctor"))
                familyDoctor++;
            if ((appointment.getType().equals("Lab appointment")))
                lab_Test_Appointments++;
            if ((appointment.getType().equals("Nurse appointment")))
                nurse_Care++;
            if ((appointment.getType().equals("Covid test appointment")))
                covid_Test++;
            if (appointment.getEmployee().getRole().equals("Covid 19 vaccine appointment"))
                vaccine_Appointments++;
        }
        MissedAppRep ReadyReport = new MissedAppRep(clinic,familyDoctor, pediatrician, vaccine_Appointments, lab_Test_Appointments, covid_Test, nurse_Care);
        Main.session.save(ReadyReport);
        Main.session.flush();

    }
    public static void CreateAwaitingTimeRepForClinic(Clinic clinic) {
        ZoneId defaultZoneId1 = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(Date.from(today.atStartOfDay(defaultZoneId1).toInstant()));


        // LocalDate nextSunday = today.with(next(SUNDAY));
        LocalDate thisPastSunday = today.with(previous(SUNDAY));

//gets the query with the needed data
        CriteriaBuilder builder1 = Main.session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query1 = builder1.createQuery(Appointment.class);
        Root<Appointment> root1 = query1.from(Appointment.class);

        query1.multiselect(root1.get("time"),root1.get("actual_time"), root1.get("arrived"),root1.get("date"), root1.get("clinic"),root1.get("employee"));
        query1.where(builder1.equal(root1.get("clinic"), clinic), builder1.equal(root1.get("arrived"), true), builder1.between(root1.<LocalDate>get("date"), thisPastSunday, today));
        query1.orderBy(builder1.asc(root1.get("employee")));
//,builder1.notEqual(root1.get("employee"), null),builder1.notEqual(root1.get("employee"), "")
        List<Appointment> appointments = Main.session.createQuery(query1).getResultList();
// 1.sum actual_time-time for each doctor (only if actual_time is there)
//   2. count for each doctor how much arrivals were there
//    3.make average
//    4.place all in the awating time table and save
        int doctorId = -1;
        double[] averageOfTime = {0, 0, 0, 0, 0, 0};
        int[] counttheAppointments = {0, 0, 0, 0, 0, 0};
        ZoneId defaultZoneId = ZoneId.systemDefault();
        for (Appointment appointment : appointments) {

            LocalDate localDate = appointment.getDate();
            Calendar c = Calendar.getInstance();
            c.setTime(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            int CurrentWaitingTime = (int) MINUTES.between(appointment.getTime(),appointment.getActual_time());
            if ((doctorId == appointment.getEmployee().getUserId()) && (appointments.indexOf(appointment) != (appointments.size()-1))) {
                //while we are on the same doctor
                doctorId = appointment.getEmployee().getUserId();
                averageOfTime[dayOfWeek - 1] += CurrentWaitingTime;
                counttheAppointments[dayOfWeek - 1]++;
            } else if ((doctorId == -1)&&(appointments.indexOf(appointment) != (appointments.size()-1))) {//do the first time
                doctorId = appointment.getEmployee().getUserId();
                averageOfTime[dayOfWeek - 1] = CurrentWaitingTime;
                counttheAppointments[dayOfWeek - 1] = 1;

            } else {//do what happened each time we get to new doctor
                if ((appointments.indexOf(appointment) == (appointments.size()-1))) {
                    averageOfTime[dayOfWeek - 1] += CurrentWaitingTime;
                    counttheAppointments[dayOfWeek - 1]++;
                }
                for (int j = 0; j < 6; j++) {
                    if(counttheAppointments[j]!=0)
                        averageOfTime[j] = averageOfTime[j] / counttheAppointments[j];

                }
                //here we need to insert everything we want into the awatingtimerep raw
                AwaitingTimeRep ReadyReport = new AwaitingTimeRep(appointment.getEmployee().getFirstName() + " " + appointment.getEmployee().getLastName(),clinic, averageOfTime[0], averageOfTime[1], averageOfTime[2], averageOfTime[3], averageOfTime[4], averageOfTime[5], counttheAppointments[0], counttheAppointments[1], counttheAppointments[2], counttheAppointments[3], counttheAppointments[4], counttheAppointments[5]);
                Main.session.save(ReadyReport);
                Main.session.flush();
                if ((appointments.indexOf(appointment) != (appointments.size()-1))) {
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
                doctorId = appointment.getEmployee().getUserId();
            }

        }
    }
}



