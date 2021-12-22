package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;


public class loginController {
    private SimpleClient client;
    private static int sessionID = 0;
    private String sessionIDStr;

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

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        emptyUsernameWarning.setVisible(false);
        emptyPasswordWarning.setVisible(false);
        loginFailedWarning.setVisible(false);
    }

    @FXML
    void authorize(ActionEvent event)  {
        try {
            String username = userTF.getText();
            if (username.equals("")) {
                emptyUsernameWarning.setVisible(true);
            } else {
                String password = passwordTF.getText();
                if (password.equals("")) {
                    emptyPasswordWarning.setVisible(true);
                } else {
                    SimpleClient.setClientNull();
                    client = SimpleClient.getClient();
                    if (client != null) {
                        try {
                            client.openConnection();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateSessionID() {
        sessionID++;
        return "session: " + sessionID;
    }


    @Subscribe
    public void onLoginEvent(loginEvent event) {
        String user_type = event.getUserType();
        String status = event.getStatus();
        if (status != null) {
            if (status.equals("you are already logged in")) {
                loginFailedWarning.setText("Login Failed- user is already logged in");
                loginFailedWarning.setVisible(true);
                return;
            }
            if (status.equals("This user does not exist")) {
                loginFailedWarning.setText("Login Failed- incorrect username or password.Please try again or go to the main office");
                loginFailedWarning.setVisible(true);
                return;
            }
            App.setUsername(event.getUsername());
            App.setUserType(event.getUserType());
            try {
                ChangeScreens.changeToOpenningHoursScreen();
                //ChangeScreens.changeToMainPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

