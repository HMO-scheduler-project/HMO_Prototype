package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Employee;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        for (User user : users) {
            if (user.getUsername().equals(msg.getUsername())) {
                user_found = true;
                if (user.checkPassword(msg.getPassword())) {
                    if (!user.isLoggedIn()) {
                        if (user instanceof Manager) {
                            msg.setUserType("Manager");
                            System.out.println(msg.getUserType());
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                        } else if (user instanceof Employee) {
                            msg.setUserType("Employee");
                            System.out.println(msg.getUserType());
                            user.setLoggedIn(true);
                            msg.setStatus("logged in");
                            msg.setUser(user);
                        }
                    } else {
                        msg.setStatus("you are already logged in");
                    }
                } else {
                    msg.setStatus("wrong password");
                }
            }
        }
        if(!user_found) {
            msg.setStatus("This user does not exist");
        }
    }

    public static void logOut(Message msg) throws NoSuchAlgorithmException {
        List<User> users = getAllUsersFromDB();
        System.out.println("about to log out");
        for (User user : users) {
            if (user.getUsername().equals(msg.getUsername()) && user.isLoggedIn()) {
                user.setLoggedIn(false);
            } else if (user.getUsername().equals(msg.getUsername()) && !user.isLoggedIn()) {
                msg.setStatus("you are already logged out");
            }
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
}





