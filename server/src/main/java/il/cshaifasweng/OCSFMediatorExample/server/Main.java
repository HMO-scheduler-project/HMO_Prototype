package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
        configuration.addAnnotatedClass(Appointment.class);
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
            if (currMsg.getAction().equals("pull openning hours")) {
                try {
                    serverMsg = currMsg;
                    currMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    serverMsg.setOpenningHour(clinicController.getOpenningHourByClinic(currMsg.getClinic()));
                    serverMsg.setClosingHour(clinicController.getClosingHourByClinic(currMsg.getClinic()));
                    serverMsg.setAction("got openning hours");
                    client.sendToClient(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (currMsg.getAction().equals("change hours")) {
                try {
                    serverMsg = currMsg;
                    serverMsg.setClinic(clinicController.getClinicByName(currMsg.getClinicName()));
                    if(serverMsg.getOpenningHour()!=null) {
                        serverMsg.getClinic().setOpenningHour(serverMsg.getOpenningHour());
                    }
                    if(serverMsg.getClosingHour()!=null) {
                        serverMsg.getClinic().setClosingHour(serverMsg.getClosingHour());
                    }
                    updateCellInDB(serverMsg.getClinic());
                    serverMsg.setAction("saved new hours");
                    serverMsg.setOpenningHour(serverMsg.getClinic().getOpenningHour());
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
            session = sessionFactory.openSession();
            session.beginTransaction();
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
        } finally {
            session.close();
        }
    }

    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException {
        server = new Main(3002);
        server.listen();
        System.out.println("server says: hello!");

    }
}

