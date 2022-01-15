package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.WarningEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.logoutEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.logoutFromStationEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import org.greenrobot.eventbus.Subscribe;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private static String username;
    private static String user_type;
    private static String first_name;
    private static String clinic_name;
    private static Boolean isLogoutClicked = false;
    private static Stage appStage;
    private boolean isRegistered = false;
    private static boolean covid_vaccine=false;
    private static boolean influenza_vaccine=true;

    @Override
    public void start (Stage stage) throws IOException{
        try{
            if(!isRegistered) {
                EventBus.getDefault().register(this);
                isRegistered = true;
            }
            Parent root= loadFXML("chooseDevice.fxml");
            Scene start = new Scene(root);
            String cssPath = getClass().getResource("/style.css").toString();
            stage.setTitle("Welcome");
            root.getStylesheets().add(cssPath);
            scene = start;
            stage.setScene(start);
            appStage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    @Override
    public void stop(){
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

    public static void logout(Boolean logoutClicked,String device) {
        if(username == null) {
            Platform.exit();
            System.exit(0);
        }
        isLogoutClicked = logoutClicked;
        Message msg = new Message();
        if(device.equals("PC")) {
        msg.setAction("logout");
        }else if(device.equals("Station")){
            msg.setAction("logoutFromStation");
        }
        msg.setUsername(username);
        try {
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onlogoutEvent(logoutEvent event) throws IOException {
        username = null;
        user_type = null;
        isLogoutClicked = false;
        Platform.runLater(() -> {
            setWindowTitle("Welcome");
            try {
                setContent("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Subscribe
    public void onLogoutFromStationEvent(logoutFromStationEvent event) throws IOException {
        username = null;
        user_type = null;
        isLogoutClicked = false;
        Platform.runLater(() -> {
            setWindowTitle("Welcome");
            try {
                setContent("LoginWithCard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        App.username = username;
    }

    public static String getUserType() {
        return user_type;
    }

    public static void setUserType(String user_type) {
        App.user_type = user_type;
    }

    public static String getFirst_name() {
        return first_name;
    }

    public static void setFirst_name(String first_name) {
        App.first_name = first_name;
    }

    public static String getClinic_name() {
        return clinic_name;
    }

    public static void setClinic_name(String clinic_name) {
        App.clinic_name = clinic_name;
    }

    public static Stage getAppStage(){
        return appStage;
    }

    public static boolean isCovid_vaccine() {
        return covid_vaccine;
    }

    public static void setCovid_vaccine(boolean covid_vaccine) {
        App.covid_vaccine = covid_vaccine;
    }

    public static boolean isInfluenza_vaccine() {
        return influenza_vaccine;
    }

    public static void setInfluenza_vaccine(boolean influenza_vaccine) {
        App.influenza_vaccine = influenza_vaccine;
    }


    static void setWindowTitle(String title) {
        getAppStage().setTitle(title);
    }

    static void setContent(String pageName) throws IOException {
        Parent root= loadFXML(pageName+".fxml");
        scene = new Scene(root);
        String cssPath = Objects.requireNonNull(App.class.getResource("/style.css")).toString();
        root.getStylesheets().add(cssPath);
        appStage.setScene(scene);
        appStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}