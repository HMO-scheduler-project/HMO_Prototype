package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.util.logging.Level;

import org.greenrobot.eventbus.Subscribe;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private static String username;
    private static String password;
    private static String type;
    private static Object currentController;
    private static Boolean isLogoutClicked = false;
    private static Stage appStage;
    private static SessionFactory sessionFactory = getSessionFactory();
    public static Session session;


    public static SessionFactory getSessionFactory() throws HibernateException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Clinic.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Manager.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }



    @Override
    public void start (Stage stage){
        try{
            Parent root= FXMLLoader.load(getClass().getResource("login.FXML"));
            Scene login = new Scene(root);
            //login.getStylesheets().add(getClass().getResource("il/cshaifasweng/OCSFMediatorExample/client/login_screen.css").toExternalForm());
            stage.setScene(login);
            this.appStage = stage;

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    @Override
    public void stop(){
        if(currentController!= null) {
            System.out.println("Stage is closing");
        }
        Platform.exit();
        System.exit(0);
    }

    @Subscribe
    public void onWarningEvent(WarningEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.WARNING,
                    String.format("Message: %s\nTimestamp: %s\n",
                            event.getWarning().getMessage(),
                            event.getWarning().getTime().toString())
            );
            alert.show();
        });
    }

    @Subscribe
    public void SetClient(Message msg) throws IOException {
        if(msg.getAction().equals("set client")) {
            client = SimpleClient.getClient();
        }
    }

    public static void logout(Boolean logoutClicked) {
        if(username == null || password == null) {
            Platform.exit();
            System.exit(0);
        }
        isLogoutClicked = logoutClicked;
        Message msg = new Message();
        msg.setAction("logout");
        msg.setUsername(username);
        msg.setPassword(password);
        try {
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        App.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        App.username = username;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        App.type = type;
    }

    public static Object getCurrentController() {
        return currentController;
    }

    public static Stage getAppStage(){
        return appStage;
    }

    public static void main(String[] args) {
        launch();
    }

}