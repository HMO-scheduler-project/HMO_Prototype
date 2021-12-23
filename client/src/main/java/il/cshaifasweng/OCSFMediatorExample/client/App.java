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
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import java.net.URL;

import org.greenrobot.eventbus.Subscribe;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private static String username;
    private static String user_type;
    private static Boolean isLogoutClicked = false;
    private static Stage appStage;
    private boolean isRegistered = false;

    @Override
    public void start (Stage stage) throws IOException{
        try{
            if(!isRegistered) {
                EventBus.getDefault().register(this);
                isRegistered = true;
            }
            Parent root= loadFXML("login.fxml");
            Scene start = new Scene(root);
            String cssPath = getClass().getResource("/login_screen.css").toString();
            stage.setTitle("Welcome");
//            Parent root= loadFXML("openningHoursScreen.fxml");
//            Scene start = new Scene(root);
//            String cssPath = getClass().getResource("openningHoursScreen.css").toExternalForm();
            root.getStylesheets().add(cssPath);
            scene = start;
            stage.setScene(start);
            appStage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Parent loadFXML(String fxml) throws IOException {
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

    public static void logout(Boolean logoutClicked) {
        if(username == null) {
            Platform.exit();
            System.exit(0);
        }
        isLogoutClicked = logoutClicked;
        Message msg = new Message();
        msg.setAction("logout");
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
            setWindowTitle("login");
            try {
                setContent("login");
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

    public static Stage getAppStage(){
        return appStage;
    }

    static void setWindowTitle(String title) {
        getAppStage().setTitle(title);
    }

    static void setContent(String pageName) throws IOException {
        Parent root= loadFXML(pageName+".fxml");
        scene = new Scene(root);
        appStage.setScene(scene);
        String cssPath = App.class.getResource("/"+pageName+".css").toString();
        appStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}