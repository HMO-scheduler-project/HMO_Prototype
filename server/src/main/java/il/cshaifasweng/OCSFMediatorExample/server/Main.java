package il.cshaifasweng.OCSFMediatorExample.server;

import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import Reports.WeeklyReport;
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
import java.util.List;

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
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
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
                    if (currMsg.getUsername().equals("") || currMsg.getPassword().equals("")) {
                    } else {
                        userController.getUser(currMsg);
                        updateCellInDB(currMsg.getUser());
                        serverMsg = currMsg;
                        serverMsg.setAction("login done");
                        System.out.println(currMsg.getUser().getUsername());
                        client.sendToClient(serverMsg);
                    }
                } catch (IOException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("login with card number")) {
                try {
                    if (currMsg.getUserCardNumber().equals("")){
                    } else {
                        userController.getUserWithCardNumber(currMsg);
                        updateCellInDB(currMsg.getUser());
                        serverMsg = currMsg;
                        serverMsg.setAction("loginByCard done");
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
//            if (currMsg.getAction().equals("Pull Reports")) {
//                try {
//                    serverMsg = currMsg;
//                    currMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
//                    serverMsg.setAwaitingTimeRep(clinicController.getAwaitingTimeRepByClinic(currMsg.getClinic()));
//                    serverMsg.setMissedAppRep(clinicController.getMissedAppRepByClinic(currMsg.getClinic()));
//                    serverMsg.setServicesTypeRep(clinicController.getServicesTypeRepByClinic(currMsg.getClinic()));
//                    serverMsg.setAction("Got Reports");
//                    client.sendToClient(serverMsg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            if (currMsg.getAction().equals("change hours")) {
                try {
                    serverMsg = currMsg;
                    serverMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    if(serverMsg.getOpeningHour()!=null) {
                        serverMsg.getClinic().setOpeningHour(serverMsg.getOpeningHour());
                    }
                    if(serverMsg.getClosingHour()!=null) {
                        serverMsg.getClinic().setClosingHour(serverMsg.getClosingHour());
                    }
                    updateCellInDB(serverMsg.getClinic());
                    serverMsg.setAction("saved new hours");
                    serverMsg.setOpeningHour(serverMsg.getClinic().getOpeningHour());
                    serverMsg.setClosingHour(serverMsg.getClinic().getClosingHour());
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
//            if(currMsg.getAction().equals("GetPatientsList")){
//                try {
//                    serverMsg.setNearest_apps(appointmentController.getPatientListFromDB(currMsg.getUsername()));
//                    serverMsg.setAction("got patient apps");
//                    client.sendToClient(serverMsg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(currMsg.getAction().equals("updateArrivedTime")){
//                try {
//                    Appointment app = appointmentController.findAppinDB(currMsg.getCardNum(), LocalDate.now());
//                    app.setActual_time(LocalTime.now());
//                    app.setArrived(currMsg.patientArrived());
//                    updateCellInDB(app);
//                    serverMsg.setAction("got nearest apps");
//                    client.sendToClient(serverMsg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(currMsg.getAction().equals("GetAllManagedClinics")){
//                try {
//                    List<String> clinicNames = getManagedClinicNames(userController.getManagerByUsername(currMsg.getUsername()));
//                    serverMsg.setClinicList(clinicNames);
//                    serverMsg.setAction("ShowManagedClinics");
//                    client.sendToClient(serverMsg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(currMsg.getAction().equals("pull services")){
//                try {
//                    List<String> servicesNames = clinicController.getServicesList(currMsg.getClinicName());
//                    serverMsg.setClinicList(servicesNames);
//                    serverMsg.setAction("ShowServices");
//                    client.sendToClient(serverMsg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(currMsg.getAction().equals("pull doctors")){
//                try {
//                    List<String> doctors = clinicController.getDoctorsofClinic(currMsg.getClinicName());
//                    serverMsg.setClinicList(doctors);
//                    serverMsg.setAction("ShowDoctors");
//                    client.sendToClient(serverMsg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
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
            System.err.println("An error occured, changes have been rolled back.");
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
            System.err.println("An error occured, changes have been rolled back.");
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException {
        server = new Main(3004);
        server.listen();
        System.out.println("server says: hello!");

    }

    private List<String> getManagedClinicNames(Manager manager){
        List<String> names = null;
        for(Clinic clinic : manager.getManaging_clinics()){
            names.add(clinic.getName());
        }
        return names;
    }
}

