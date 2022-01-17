package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.AwaitingTimeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.MissedAppRep;
import il.cshaifasweng.OCSFMediatorExample.entities.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.WeeklyReport;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends SimpleServer {
    private static SessionFactory sessionFactory = getSessionFactory();
    public static Session session;
    private Message serverMsg;
    private static SimpleServer server;

    public Main(int port) {
        super(port);
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Clinic.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.addAnnotatedClass(HMO_Manager.class);
        configuration.addAnnotatedClass(GreenPass.class);
        configuration.addAnnotatedClass(Appointment.class);
        configuration.addAnnotatedClass(LabApp.class);
        configuration.addAnnotatedClass(LabWorker.class);
        configuration.addAnnotatedClass(Nurse.class);
        configuration.addAnnotatedClass(NurseApp.class);
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(childrenDoctorApp.class);
        configuration.addAnnotatedClass(Covid19Test.class);
        configuration.addAnnotatedClass(Covid19VaccineApp.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(doctorApp.class);
        configuration.addAnnotatedClass(familyDoctorApp.class);
        configuration.addAnnotatedClass(InfluenzaVaccineApp.class);
        configuration.addAnnotatedClass(specialDoctorApp.class);
        configuration.addAnnotatedClass(SpecialDoctor.class);
        configuration.addAnnotatedClass(AwaitingTimeRep.class);
        configuration.addAnnotatedClass(MissedAppRep.class);
        configuration.addAnnotatedClass(ServicesTypeRep.class);
        configuration.addAnnotatedClass(WeeklyReport.class);
        configuration.addAnnotatedClass(CovidQuestionnaire.class);
        configuration.addAnnotatedClass(clinicSpecialService.class);
//  ADDED AS COMMENT CURRENTLY BECAUSE IT'S GIVING ERROR
//        configuration.addAnnotatedClass(MessageToManager.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        new WeeklyReportService();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Message currMsg = ((Message) msg);
            serverMsg = new Message();
            if (currMsg.getAction().equals("login")) {
                try {
                    if (!currMsg.getUsername().equals("") && !currMsg.getPassword().equals("")) {
                        userController.getUser(currMsg);
                        if(currMsg.getUser()!=null) {
                            updateCellInDB(currMsg.getUser());
                        }
                        serverMsg = currMsg;
                        serverMsg.setAction("login done");
                        client.sendToClient(serverMsg);
                    }
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("login with card number")) {
                try {
                    if (!currMsg.getUserCardNumber().equals("")){
                        userController.getUserWithCardNumber(currMsg);
                        if(currMsg.getUser()!=null) {
                            updateCellInDB(currMsg.getUser());
                        }
                        serverMsg = currMsg;
                        serverMsg.setAction("loginByCarddone");
                        client.sendToClient(serverMsg);
                    }
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("logout")) {
                try {
                    userController.logOut(currMsg);
                    updateCellInDB(currMsg.getUser());
                    serverMsg = currMsg;
                    serverMsg.setAction("logged out");
                    client.sendToClient(serverMsg);
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("logoutFromStation")) {
                try {
                    userController.logOut(currMsg);
                    updateCellInDB(currMsg.getUser());
                    serverMsg = currMsg;
                    serverMsg.setAction("logged out from station");
                    client.sendToClient(serverMsg);
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("pull opening hours")) {
                try {
                    serverMsg = currMsg;
                    currMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    serverMsg.setOpeningHour(clinicController.getOpeningHourByClinic(currMsg.getClinic()));
                    serverMsg.setClosingHour(clinicController.getClosingHourByClinic(currMsg.getClinic()));
                    serverMsg.setAction("got opening hours");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("GetAllClinics")) {
                try {
                    serverMsg.setClinicList(clinicController.getAllClinicNamesFromDB());
                    serverMsg.setAction("ShowClinics");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("GetAllClinicsForStation")) {
                try {
                    serverMsg.setClinicList(clinicController.getAllClinicNamesFromDB());
                    serverMsg.setAction("ShowClinicsForStation");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("GetAllClinicsWithCovidTest")) {
                try {
                    List<String> clinicNames = clinicController.getClinicWithService("covid test");
                    serverMsg.setClinicList(clinicNames);
                    serverMsg.setAction("GetAllClinicsWithCovidTest");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("GetAllClinicsWithVaccine")) {
                try {
                    List<String> clinicNames = clinicController.getClinicWithService(currMsg.getService_name());
                    serverMsg.setClinicList(clinicNames);
                    serverMsg.setAction("GetAllClinicsWithVaccine");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (currMsg.getAction().equals("GetClinicFromName")) {
                try {
                    serverMsg = currMsg;
                    serverMsg.setClinic(clinicController.getClinicByName(serverMsg.getClinicName()));
                    serverMsg.setAction("Chosen clinic");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("pull contact info")){
                try {
                    serverMsg = currMsg;
                    currMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    serverMsg.setAddress(clinicController.getAddressOfClinic(currMsg.getClinic()));
                    serverMsg.setPhoneNum(clinicController.getPhoneNumOfClinic(currMsg.getClinic()));
                    serverMsg.setAction("got contact info");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("change address")){
                try {
                    serverMsg = currMsg;
                    serverMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    if(serverMsg.getAddress()!=null) {
                        serverMsg.getClinic().setAddress(serverMsg.getAddress());
                    }
                    updateCellInDB(serverMsg.getClinic());
                    serverMsg.setAction("saved new address");
                    serverMsg.setAddress(serverMsg.getClinic().getAddress());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("change phone number")){
                try {
                    serverMsg = currMsg;
                    serverMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    if(serverMsg.getPhoneNum()!=null) {
                        serverMsg.getClinic().setPhoneNum(serverMsg.getPhoneNum());
                    }
                    updateCellInDB(serverMsg.getClinic());
                    serverMsg.setAction("saved new phone");
                    serverMsg.setPhoneNum(serverMsg.getClinic().getPhoneNum());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("GetNearestApps")){
                try {
                    serverMsg.setNearest_apps(appointmentController.getNearestAppsFromDB(userController.getUserByUsername(currMsg.getUsername())));
                    serverMsg.setAction("got nearest apps");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("newNurseApp")){
                try{
                    Clinic clinic = clinicController.getClinicByName(currMsg.getClinicName());
                    NurseApp app = new NurseApp(LocalTime.now(), LocalDate.now(),clinic, (Patient)userController.getUserByUsername(currMsg.getUsername()), clinicController.getNurseByClinic(currMsg.getClinicName()));
                    saveRowInDB(app);
                    serverMsg.setAppointment(app);
                    serverMsg.setAction("got nurse app");
                    client.sendToClient(serverMsg);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("nurseAppCounter")){
                try{
                    serverMsg.setAppCount(appointmentController.countAppPerDay(currMsg.getAppointment().getEmployee(), currMsg.getAppointment().getDate()));
                    serverMsg.setAppointment(currMsg.getAppointment());
                    serverMsg.setAction("got nurseAppCounter");
                    client.sendToClient(serverMsg);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("newLabApp")){
                try{
                    Clinic clinic = clinicController.getClinicByName(currMsg.getClinicName());
                    LabApp app = new LabApp(LocalTime.now(), LocalDate.now(),clinic, (Patient)userController.getUserByUsername(currMsg.getUsername()), clinicController.getLabWorkerByClinic(currMsg.getClinicName()));
                    saveRowInDB(app);
                    serverMsg.setAppointment(app);
                    serverMsg.setAction("got lab app");
                    client.sendToClient(serverMsg);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("labAppCounter")){
                try{
                    serverMsg.setAppCount(appointmentController.countAppPerDay(currMsg.getAppointment().getEmployee(), currMsg.getAppointment().getDate()));
                    serverMsg.setAppointment(currMsg.getAppointment());
                    serverMsg.setAction("got labAppCounter");
                    client.sendToClient(serverMsg);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("Provide Ticket")){
                try{
                    Appointment appointment=appointmentController.getAppointments(currMsg.getClinicName(),currMsg.getUsername());
                    serverMsg.setAppointment(appointment);
                    if (appointment!=null) {
                        serverMsg.setAppCount(appointmentController.PatientTicket(appointment));
                    }
                    else
                    serverMsg.setAppCount(0);
                    serverMsg.setAction("got Appointment");
                    client.sendToClient(serverMsg);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("GetPatientsList")){
                try {
                    serverMsg.setNearest_apps(appointmentController.getPatientListFromDB((Employee)userController.getUserByUsername(currMsg.getUsername())));
                    serverMsg.setAction("got patient apps");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("updateArrivedTime")){
                try {
                    Appointment app = appointmentController.findAppinDB(currMsg.getUserCardNumber(), LocalDate.now());
                    app.setActual_time(LocalTime.now());
                    app.setArrived(true);
                    updateCellInDB(app);
                    serverMsg.setAction("updated arrival time");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("GetAllManagedClinics")){
                try {
                    List<String> clinicNames = clinicController.getManagedClinicNames(userController.getManagerByUsername(currMsg.getUsername()));
                    serverMsg.setClinicList(clinicNames);
                    serverMsg.setAction("ShowManagedClinics");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("pull services")){
                try {
                    List<String> servicesNames = clinicController.getServicesList(currMsg.getClinicName());
                    serverMsg.setServices(servicesNames);
                    serverMsg.setAction("ShowServices");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("pull doctors")){
                try {
                    List<String> doctors = clinicController.getDoctorsofClinic(currMsg.getClinicName());
                    serverMsg.setDoctors(doctors);
                    serverMsg.setAction("ShowDoctors");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("pull doctor hours")){
                try {
                    Doctor doctor = userController.getDoctorByName(currMsg.getDoctor());
                    serverMsg.setOpeningHour(doctor.getStart_working_hour());
                    serverMsg.setClosingHour(doctor.getFinish_working_hour());
                    serverMsg.setRoom(doctor.getRoom_num());
                    serverMsg.setAction("ShowHours");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("pull specialists")){
                try {
                    List<String> doctors = clinicController.getSpecialDoctorsofClinic(currMsg.getClinicName());
                    serverMsg.setDoctors(doctors);
                    serverMsg.setAction("ShowDoctors");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("pull service hours")) {
                if (currMsg.getService_name().equals("clinic")) {
                    Clinic clinic = clinicController.getClinicByName(currMsg.getClinicName());
                    serverMsg.setOpeningHour(clinic.getOpeningHour());
                    serverMsg.setClosingHour(clinic.getClosingHour());
                    serverMsg.setRoom(-1);
                    serverMsg.setAction("ShowHours");
                    client.sendToClient(serverMsg);
                } else if (currMsg.getService_name().equals("nurse")) {
                    try {
                        List<Nurse> nursesList = userController.getNursesByClinic(currMsg.getClinicName());
                        for (Nurse nurse : nursesList) {
                            serverMsg.setOpeningHour(nurse.getStart_working_hour());
                            serverMsg.setClosingHour(nurse.getFinish_working_hour());
                            serverMsg.setRoom(nurse.getRoom_num());
                            serverMsg.setAction("ShowHours");
                            client.sendToClient(serverMsg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (currMsg.getService_name().equals("lab")) {
                    try {
                        List<LabWorker> labWorkersList = userController.getLabWorkersByClinic(currMsg.getClinicName());
                        for (LabWorker worker : labWorkersList) {
                            serverMsg.setOpeningHour(worker.getStart_working_hour());
                            serverMsg.setClosingHour(worker.getFinish_working_hour());
                            serverMsg.setRoom(worker.getRoom_num());
                            serverMsg.setAction("ShowHours");
                            client.sendToClient(serverMsg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (currMsg.getService_name().equals("covid test") || currMsg.getService_name().equals("covid vaccine") || currMsg.getService_name().equals("influenza vaccine")) {
                    try {
                        clinicSpecialService service = clinicController.getService(currMsg.getService_name(), currMsg.getClinicName());
                        serverMsg.setOpeningHour(service.getStart());
                        serverMsg.setClosingHour(service.getEnd());
                        serverMsg.setRoom(service.getRoom_num());
                        serverMsg.setAction("ShowHours");
                        client.sendToClient(serverMsg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (currMsg.getAction().equals("change hours")) {
                try {
                    if(currMsg.getService_name().equals("clinic")){
                        serverMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                        if(currMsg.getOpeningHour()!=null) {
                            serverMsg.getClinic().setOpeningHour(currMsg.getOpeningHour());
                        }
                        if(currMsg.getClosingHour()!=null) {
                            serverMsg.getClinic().setClosingHour(currMsg.getClosingHour());
                        }
                        updateCellInDB(serverMsg.getClinic());
                        serverMsg.setAction("saved new hours");
                        serverMsg.setOpeningHour(serverMsg.getClinic().getOpeningHour());
                        serverMsg.setClosingHour(serverMsg.getClinic().getClosingHour());
                    }else if(currMsg.getService_name().equals("doctors") || currMsg.getService_name().equals("specialists")){
                        Doctor doctor = userController.getDoctorByName(currMsg.getDoctor());
                        if(currMsg.getOpeningHour()!=null) {
                            doctor.setStart_working_hour(currMsg.getOpeningHour());
                        }
                        if(currMsg.getClosingHour()!=null) {
                            doctor.setFinish_working_hour(currMsg.getClosingHour());
                        }
                        updateCellInDB(doctor);
                        serverMsg.setAction("saved new hours");
                        serverMsg.setOpeningHour(doctor.getStart_working_hour());
                        serverMsg.setClosingHour(doctor.getFinish_working_hour());
                    }else if(currMsg.getService_name().equals("nurse")) {
                        List<Nurse> nurseList = userController.getNursesByClinic(currMsg.getClinicName());
                        for (Nurse nurse : nurseList) {
                            if (currMsg.getOpeningHour() != null) {
                                nurse.setStart_working_hour(currMsg.getOpeningHour());
                            }
                            if (currMsg.getClosingHour() != null) {
                                nurse.setFinish_working_hour(currMsg.getClosingHour());
                            }
                            updateCellInDB(nurse);
                            serverMsg.setAction("saved new hours");
                            serverMsg.setOpeningHour(nurse.getStart_working_hour());
                            serverMsg.setClosingHour(nurse.getFinish_working_hour());
                        }
                    }else if(currMsg.getService_name().equals("lab")) {
                        List<LabWorker> labWorkerList = userController.getLabWorkersByClinic(currMsg.getClinicName());
                        for (LabWorker worker : labWorkerList) {
                            if (currMsg.getOpeningHour() != null) {
                                worker.setStart_working_hour(currMsg.getOpeningHour());
                            }
                            if (currMsg.getClosingHour() != null) {
                                worker.setFinish_working_hour(currMsg.getClosingHour());
                            }
                            updateCellInDB(worker);
                            serverMsg.setAction("saved new hours");
                            serverMsg.setOpeningHour(worker.getStart_working_hour());
                            serverMsg.setClosingHour(worker.getFinish_working_hour());
                        }
                    }else if(currMsg.getService_name().equals("covid test") || currMsg.getService_name().equals("covid vaccine") || currMsg.getService_name().equals("influenza vaccine")) {
                        clinicSpecialService service = clinicController.getService(currMsg.getService_name(),currMsg.getClinicName());
                        if (currMsg.getOpeningHour() != null) {
                            service.setStart(currMsg.getOpeningHour());
                        }
                        if (currMsg.getClosingHour() != null) {
                            service.setEnd(currMsg.getClosingHour());
                        }
                        updateCellInDB(service);
                        serverMsg.setAction("saved new hours");
                        serverMsg.setOpeningHour(service.getStart());
                        serverMsg.setClosingHour(service.getEnd());
                    }
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("getAllRep")) {
                try {
                    serverMsg = currMsg;
                    serverMsg.setServicesTypeRep(Reportscontroller.getServicesTypeRepFromDB(serverMsg.getClinicName()));
                    serverMsg.setMissedAppRep(Reportscontroller.getMissedAppRepFromDB(serverMsg.getClinicName()));
                    serverMsg.setAwaitingTimeRep(Reportscontroller.getAwaitingTimeRepFromDB(serverMsg.getClinicName()));

                    serverMsg.setAction("AllRepToRep");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("getClinicNameFromUserName")) {
                try {
                    serverMsg = currMsg;
                    serverMsg.setClinicName(userController.getManagerByUsername(serverMsg.getUsername()).getMain_clinic());
                    serverMsg.setAction("clinicNameFromUserName");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("change room")){
                try {
                    if(currMsg.getService_name().equals("doctors") || currMsg.getService_name().equals("specialists")){
                        Doctor doctor = userController.getDoctorByName(currMsg.getDoctor());
                        if(currMsg.getRoom()>0) {
                            doctor.setRoom_num(currMsg.getRoom());
                        }
                        updateCellInDB(doctor);
                        serverMsg.setAction("saved new room");
                        serverMsg.setRoom(doctor.getRoom_num());
                    }else if(currMsg.getService_name().equals("nurse")) {
                        List<Nurse> nurseList = userController.getNursesByClinic(currMsg.getClinicName());
                        for (Nurse nurse : nurseList) {
                            if (currMsg.getRoom()>0) {
                                nurse.setRoom_num(currMsg.getRoom());
                            }
                            updateCellInDB(nurse);
                            serverMsg.setAction("saved new room");
                            serverMsg.setRoom(nurse.getRoom_num());
                        }
                    }else if(currMsg.getService_name().equals("lab")) {
                        List<LabWorker> labWorkerList = userController.getLabWorkersByClinic(currMsg.getClinicName());
                        for (LabWorker worker : labWorkerList) {
                            if (currMsg.getRoom()>0) {
                                worker.setRoom_num(currMsg.getRoom());
                            }
                            updateCellInDB(worker);
                            serverMsg.setAction("saved new room");
                            serverMsg.setRoom(worker.getRoom_num());
                        }
                    }else if(currMsg.getService_name().equals("covid test") || currMsg.getService_name().equals("covid vaccine") || currMsg.getService_name().equals("influenza vaccine")) {
                        clinicSpecialService service = clinicController.getService(currMsg.getService_name(),currMsg.getClinicName());
                        if (currMsg.getRoom() >0) {
                            service.setRoom_num(currMsg.getRoom());
                        }
                        updateCellInDB(service);
                        serverMsg.setAction("saved new room");
                        serverMsg.setRoom(service.getRoom_num());
                    }
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("get employee from username")) {
                try {
                    serverMsg.setEmployee(userController.getEmployeeFromUserName(currMsg.getUsername()));
                    serverMsg.setAction("got employee");
                    client.sendToClient(serverMsg);
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("get patient from username")) {
                try {
                    serverMsg.setEmployee(userController.getEmployeeFromUserName(currMsg.getUsername()));
                    serverMsg.setAction("got patient");
                    client.sendToClient(serverMsg);
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("call next patient")){
                try {
                    serverMsg.setRoom(currMsg.getEmployee().getRoom_num());
                    serverMsg.setPatientName(currMsg.getPatientName());
                    serverMsg.setAction("print message to screen");
                    client.sendToClient(serverMsg);
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get employees")) {
                try {
                    serverMsg.setEmployeeList(userController.getDoctorsByRole(currMsg.getRole(), currMsg.getClinicName()));
                    serverMsg.setNearest_apps(appointmentController.ClinicAppointments(clinicController.getClinicByName(currMsg.getClinicName())));
                    serverMsg.setAction("got employees");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get special doctor")) {
                try {
                    serverMsg.setSpecialDoctorList(userController.getSpecialDoctor(currMsg.getRole(), (Patient) userController.getUserByUsername(currMsg.getUsername())));
                    serverMsg.setAction("got special doctors");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get employee appointments")) {
                try {
                    serverMsg.setNearest_apps(appointmentController.EmployeeAppointments(currMsg.getClinic(), currMsg.getAppDate()));
                    serverMsg.setAction("got employee appointments");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Add questionnaire")) {
                try {
                    userController.addQuestionnaire((Patient) userController.getUserByUsername(currMsg.getUsername()), currMsg.isMet(), currMsg.isFever(), currMsg.isCough(), currMsg.isTired(), currMsg.isTaste(), currMsg.isSmell());
                    serverMsg.setAction("Questionnaire added");
                    serverMsg.setSaved(true);
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("ViewApps")) {
                try {
                    serverMsg.setNearest_apps(appointmentController.getNearestAppsFromDB(userController.getUserByUsername(currMsg.getUsername())));
                    serverMsg.setAction("got patient appointments");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("remove app")) {
                try {
                    Appointment app = currMsg.getAppointment();
                    Patient patient = userController.getPatientByUsername(currMsg.getUsername());
                    serverMsg.setRemoved(appointmentController.RemoveApp(app, patient));
                    serverMsg.setAction("removed app");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("cancel appointment")){
                try {
                    Appointment app = currMsg.getAppointment();
                    Patient patient = userController.getPatientByUsername(currMsg.getUsername());
                    serverMsg.setRemoved(appointmentController.RemoveApp(app, patient));
                    serverMsg.setAction("canceled app");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Add new doctor appointment")) {
                try {
                    serverMsg.setSaved(appointmentController.AddNewDoctorAppointment(currMsg.getAppTime(), currMsg.getAppDate(), clinicController.getClinicByName(currMsg.getClinicName()), (Patient) userController.getUserByUsername(currMsg.getUsername()), (Doctor) userController.getEmployee(currMsg.getEmployee_id())));
                    serverMsg.setAction("Doctor appointment added");
                    serverMsg.setAppTime(currMsg.getAppTime());
                    serverMsg.setAppDate(currMsg.getAppDate());
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Add covid test appointment")) {
                try {
                    serverMsg.setSaved(appointmentController.AddNewCovidTestApp(currMsg.getAppTime(), currMsg.getAppDate(), clinicController.getClinicByName(currMsg.getClinicName()), userController.getPatientByUsername(currMsg.getUsername()), (LabWorker) userController.getEmployee(currMsg.getEmployee_id())));
                    serverMsg.setAction("Covid test appointment added");
                    serverMsg.setAppTime(currMsg.getAppTime());
                    serverMsg.setAppDate(currMsg.getAppDate());
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get LabWorkers and clinic apps")) {
                try {
                    serverMsg.setLabWorkerList(userController.getLabWorkersByClinic(currMsg.getClinicName()));
                    serverMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    serverMsg.setNearest_apps(appointmentController.ClinicAppointments(clinicController.getClinicByName(currMsg.getClinicName())));
                    serverMsg.setAction("Got LabWorkers and clinic apps");
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get vaccine")) {
                try {
                    serverMsg.setCovid19VaccineApp(appointmentController.getNearestCovidVaccineApp(userController.getPatientByUsername(currMsg.getUsername())));
                    serverMsg.setInfluenzaVaccineApp(appointmentController.getNearestInfluenzaVaccineApp(userController.getPatientByUsername(currMsg.getUsername())));
                    Patient patient=userController.getPatientByUsername(currMsg.getUsername());
                    serverMsg.setCovid_vaccine(patient.isCovid_vaccinated());
                    serverMsg.setInfluenza_vaccine(patient.isInfluenza_vaccinated());
                    serverMsg.setAction("Got vaccines");
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Add covid vaccine appointment")) {
                try {
                    serverMsg.setSaved(appointmentController.AddNewCovidVaccineApp(currMsg.getAppTime(), currMsg.getAppDate(), clinicController.getClinicByName(currMsg.getClinicName()), (Patient) userController.getUserByUsername(currMsg.getUsername()), (LabWorker) userController.getEmployee(currMsg.getEmployee_id())));
                    serverMsg.setAction("Covid vaccine appointment added");
                    serverMsg.setAppTime(currMsg.getAppTime());
                    serverMsg.setAppDate(currMsg.getAppDate());
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Add influenza vaccine appointment")) {
                try {
                    serverMsg.setSaved(appointmentController.AddNewInfluenzaVaccineApp(currMsg.getAppTime(), currMsg.getAppDate(), clinicController.getClinicByName(currMsg.getClinicName()), (Patient) userController.getUserByUsername(currMsg.getUsername()), (LabWorker) userController.getEmployee(currMsg.getEmployee_id())));
                    serverMsg.setAction("Influenza vaccine appointment added");
                    serverMsg.setAppTime(currMsg.getAppTime());
                    serverMsg.setAppDate(currMsg.getAppDate());
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get special doctor appointments and clinics")) {
                try {
                    SpecialDoctor specialDoctor= userController.getSpecialDoctorByUsername(currMsg.getUsername());
                    serverMsg.setSpecialDoctorAppList(appointmentController.getAppointments(specialDoctor));
                    serverMsg.setSpecialDoctor(specialDoctor);
                    serverMsg.setClinics(clinicController.getSpecialDoctorsClinic(specialDoctor.getMain_clinic()));
                    serverMsg.setAction("Got special doctor appointments and clinics");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (currMsg.getAction().equals("Add new special doctor appointment")) {
                try {
                    serverMsg.setSaved(appointmentController.AddSpecialDoctorAppointment(currMsg.getAppTime(), currMsg.getAppDate(), clinicController.getClinicByName(currMsg.getClinicName()), (Patient) userController.getUserByUsername(currMsg.getUsername()), (SpecialDoctor) userController.getEmployee(currMsg.getEmployee_id())));
                    serverMsg.setAction("Special doctor appointment added");
                    serverMsg.setAppTime(currMsg.getAppTime());
                    serverMsg.setAppDate(currMsg.getAppDate());
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get birthdate and clinic")) {
                try {
                    Patient patient=userController.getPatientByUsername(currMsg.getUsername());
                    serverMsg.setBirthDate(patient.getDate_of_birth());
                    serverMsg.setClinic(patient.getClinic());
                    serverMsg.setAge(patient.getAge());
                    serverMsg.setAction("Got birthdate and clinic");
                    serverMsg.setAppTime(currMsg.getAppTime());
                    serverMsg.setAppDate(currMsg.getAppDate());
                    serverMsg.setClinicName(currMsg.getClinicName());
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("Get green pass")) {
                try {
                    User user=userController.getUserByUsername(currMsg.getUsername());
                    serverMsg.setGreenPass(userController.getUserGreenPass(user));
                    serverMsg.setAction("Got green pass");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("GetAllUnreadMessages")){
                try {
                    User user=userController.getUserByUsername(currMsg.getUsername());
                    serverMsg.setMessagesToManagerList(MessagesToManagerController.getUnreadMessagesOfManager(user));
                    serverMsg.setAction("GotMessagesToManager");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("show message")){
                try {
                    MessageToManager message = currMsg.getMessageToManager();
                    message.setRead(true);
                    serverMsg.setMessageToManager(message);
                    serverMsg.setAction("GotChosenMessage");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("pull managers")){
                try {
                    List<String> managers = userController.getAllManagersNamesFromDB();
                    serverMsg.setManagers(managers);
                    serverMsg.setAction("ShowManagers");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(currMsg.getAction().equals("send message")){
                try {
                    MessageToManager message = currMsg.getMessageToManager();
                    message.setFrom(userController.getUserByUsername(currMsg.getUsername()).getFullName());
                    saveRowInDB(message);
                    serverMsg.setMessageToManager(message);
                    serverMsg.setAction("sentMessageSuccessfully");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            assert session != null;
            session.close();
        }
    }

    public static<T> void updateCellInDB(T objectType) {
        try {
            session.update(objectType);
            session.flush();
            session.getTransaction().commit();
            session.clear();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occurred, changes have been rolled back.");
            e.printStackTrace();
        }
    }

    public static <T> void saveRowInDB(T objectType) {
        try {
            session.save(objectType);
            session.flush();
            session.getTransaction().commit();
            session.clear();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occurred, changes have been rolled back.");
            e.printStackTrace();
        }
    }

    public static <T> void deleteRowInDB(T objectType) {
        try {
            session.delete(objectType);
            session.flush();
            session.getTransaction().commit();
            session.clear();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occurred, changes have been rolled back.");
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) throws IOException {
        server = new Main(3004);
        server.listen();
        System.out.println("server says: hello!");
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        //You can here change the reminding time
//        date.set(Calendar.HOUR, 16);
//        date.set(Calendar.MINUTE, 0);
//        date.set(Calendar.SECOND, 0);
//        date.set(Calendar.MILLISECOND, 0);
        System.out.println(date.getTime());
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("Sending reminders");
                        appointmentController.sendReminders();
                    }
                },
                date.getTime(),
                //The period in milliseconds - defined as daily
                1000 * 60 * 60 * 24
        );

    }
}

