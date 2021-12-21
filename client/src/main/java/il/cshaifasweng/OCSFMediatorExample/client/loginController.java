package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class loginController {
    private SimpleClient client;
    private static int sessionID = 0;

    @FXML
    private TextField userTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button loginButton;
    @FXML
    private Label emptyUsernameWarning;
    @FXML
    private Label emptyPasswordWarning;
    @FXML
    private Label loginFailedWarning;

    public void initialize() {
        String sessionID = null;
        try {
            sessionID = authorize();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check authorization credentials.
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private String authorize() throws NoSuchAlgorithmException {
        try {
            hideWarnings();
            String username = userTF.getText();
            if (username.equals("")) {
                emptyUsernameWarning.setVisible(true);
                return null;
            }
            String password = passwordTF.getText();
            if (password.equals("")) {
                emptyPasswordWarning.setVisible(true);
                return null;
            }
            SimpleClient.setClientNull();
            client = SimpleClient.getClient();
            if(client == null) {}
            else {
                try {
                    client.openConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sendMessageToServer(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generateSessionID();
    }

    private String generateSessionID() {
        sessionID++;
        return "session: " + sessionID;
    }

    void hideWarnings() {
        emptyUsernameWarning.setVisible(false);
        emptyPasswordWarning.setVisible(false);
        loginFailedWarning.setVisible(false);
    }

    public void sendMessageToServer(String username, String password) {
        try {
            Message msg = new Message();
            msg.setUsername(username);
            msg.setPassword(password);
            msg.setAction("login");
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onMessageEvent(Message msg) throws Exception {
        if (msg.getAction().equals("login")) {
            Platform.runLater(() -> {
                String type = msg.getType();
                if (type != null) {
                    if (type.equals("you are already logged in")) {
                        loginFailedWarning.setText("Login Failed- user is already logged in");
                        loginFailedWarning.setVisible(true);
                        return;
                    }
                    if (type.equals("This user does not exist")) {
                        loginFailedWarning.setText("Login Failed- incorrect username or password.Please try again or go to the main office");
                        loginFailedWarning.setVisible(true);
                        return;
                    }

                    App.setUsername(msg.getUsername());
                    App.setPassword(msg.getPassword());
                    App.setUserType(msg.getUserType());
                    try{
                        Parent root= FXMLLoader.load(getClass().getResource("openningHoursScreen.fxml"));
                        Scene hoursScreen = new Scene(root);
                        hoursScreen.getStylesheets().add(getClass().getResource("/hour_screen.css").toExternalForm());
                        Stage stage = App.getAppStage();
                        stage.setScene(hoursScreen);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }




    }
}

