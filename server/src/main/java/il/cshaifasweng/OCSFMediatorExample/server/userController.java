package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class userController {
    private static List<User> getAllUsersFromDB() {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        return Main.session.createQuery(query).getResultList();
    }

    public static void getUser(Message msg) throws NoSuchAlgorithmException {
        List<User> users = getAllUsersFromDB();
        boolean user_found = false;
        boolean wrong_password = false;
        for (User user : users) {
            if (user.getUsername().equals(msg.getUsername())) {
                user_found = true;
                if (user.checkPassword(msg.getPassword())) {
                    if (!user.isLoggedIn()) {
                        if(user instanceof HMO_Manager)
                        {
                            msg.setUserType("HMO_Manager");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                        }
                        if (user instanceof Manager) {
                            msg.setUserType("Manager");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                            msg.setFirst_name(user.getFirstName());
                        } else if (user instanceof Employee) {
                            msg.setUserType("Employee");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                            msg.setFirst_name(user.getFirstName());
                        }else if (user instanceof Patient){
                            msg.setUserType("Patient");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                            msg.setFirst_name(user.getFirstName());
                            System.out.println(msg.getUserType());
                        }
                    } else {
                        msg.setStatus("you are already logged in");
                    }
                } else {
                    wrong_password=true;
                    break;
                }
            }
        }
        if(!user_found || wrong_password) {
            msg.setStatus("Wrong username or password");
        }
    }

    public static void getUserWithCardNumber(Message msg) throws NoSuchAlgorithmException {
        List<User> users = getAllUsersFromDB();
        boolean user_found = false;
        for (User user : users) {
            if (user.checkCard(msg.getUserCardNumber())) {
                user_found = true;
                    if (!user.isLoggedIn()) {
                        if(user instanceof HMO_Manager)
                        {
                            msg.setUserType("HMO_Manager");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                        }else if (user instanceof Manager) {
                            msg.setUserType("Manager");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                        } else if (user instanceof Employee) {
                            msg.setUserType("Employee");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                        }else if (user instanceof Patient) {
                            msg.setUserType("Patient");
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                        }
                    } else {
                        msg.setStatus("you are already logged in");
                    }
            }
        }
        if(!user_found) {
            msg.setStatus("Wrong CardNumber");
        }
    }

    public static void logOut(Message msg) throws NoSuchAlgorithmException {
        List<User> users = getAllUsersFromDB();
        boolean user_found = false;
        for (User user : users) {
            if (user.getUsername().equals(msg.getUsername())) {
                user_found = true;
                if (user.isLoggedIn()) {
                    user.setLoggedIn(false);
                    msg.setStatus("logout");
                    msg.setUser(user);
                } else {
                    msg.setStatus("you are already logged out");
                }
            }
        }
        if(!user_found) {
            msg.setStatus("can't find user");
        }
    }

    public static List<Employee> getAllEmployeesFromDB(){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        query.from(Employee.class);
        return Main.session.createQuery(query).getResultList();
    }

    public static Employee getEmployee(int employeeId) {
        List<Employee> employees = getAllEmployeesFromDB();
        for (Employee employee : employees) {
            if (employee.getUserId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    public static List<Manager> getAllManagersFromDB(){
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Manager> query = builder.createQuery(Manager.class);
        query.from(User.class);
        return Main.session.createQuery(query).getResultList();
    }

    public static Manager getManagerById (int id) {
        List<Manager> managers =getAllManagersFromDB();
        for (Manager manager : managers) {
            if (manager.getUserId() == id) {
                return manager;
            }
        }
        return null;
    }

    public static Manager getManagerByUserName (String name) {
        List<Manager> managers =getAllManagersFromDB();
        for (Manager manager : managers) {
            if (manager.getUsername() == name) {
                return manager;
            }
        }
        return null;
    }



    public static User getUserByUsername (String username) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(builder.equal(root.get("username"), username));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static Manager getManagerByUsername (String username) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Manager> query = builder.createQuery(Manager.class);
        Root<Manager> root = query.from(Manager.class);
        query.select(root);
        query.where(builder.equal(root.get("username"), username));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static User getUserById (int id) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(builder.equal(root.get("user_id"), id));
        return Main.session.createQuery(query).getSingleResult();
    }


    public static Patient getPatientByCardNum (String card_num) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Patient> query = builder.createQuery(Patient.class);
        Root<Patient> root = query.from(Patient.class);
        query.select(root);
        query.where(builder.equal(root.get("card_num"), card_num));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static Doctor getDoctorByName (String name) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = builder.createQuery(Doctor.class);
        Root<Doctor> root = query.from(Doctor.class);
        query.select(root);
        String[] nameArr = name.split(" ");
        query.where(builder.equal(root.get("first_name"), nameArr[0]),builder.equal(root.get("last_name"),nameArr[1]));
        return Main.session.createQuery(query).getSingleResult();
    }

    public static List<Nurse> getNursesByClinic (String clinic_name) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Nurse> query = builder.createQuery(Nurse.class);
        Root<Nurse> root = query.from(Nurse.class);
        query.select(root);
        query.where(builder.equal(root.get("main_clinic"),clinic_name));
        return Main.session.createQuery(query).getResultList();
    }

    public static List<LabWorker> getLabWorkersByClinic (String clinic_name) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<LabWorker> query = builder.createQuery(LabWorker.class);
        Root<LabWorker> root = query.from(LabWorker.class);
        query.select(root);
        query.where(builder.equal(root.get("main_clinic"),clinic_name));
        return Main.session.createQuery(query).getResultList();
    }

    public static Patient getPatientByUsername (String username) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<Patient> query = builder.createQuery(Patient.class);
        Root<Patient> root = query.from(Patient.class);
        query.select(root);
        query.where(builder.equal(root.get("username"), username));
        return Main.session.createQuery(query).getSingleResult();
    }

}





