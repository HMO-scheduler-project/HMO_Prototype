package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.stationLoginEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.fxml.Initializable;

import javax.swing.*;

public class LoginWithCardController  implements Initializable {
    private SimpleClient client;
    private static int sessionID = 0;
    private String sessionIDStr;

    @FXML
    private TextField CardNumber;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginFailedWarning;

    @FXML
    private Label Textbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventBus.getDefault().register(this);
        loginFailedWarning.setVisible(false);
        try {
            SimpleClient.setClientNull();
            client = SimpleClient.getClient();
            client.openConnection();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void pressOnLogin(ActionEvent event) {
        loginFailedWarning.setVisible(false);
        try {
            String card_number = CardNumber.getText();
            if (card_number.equals("")) {
                loginFailedWarning.setVisible(true);
                loginFailedWarning.setText("Please enter Card Number");
            } else {
                try {
                    loginFailedWarning.setVisible(false);
                    Message msg = new Message();
                    msg.setUserCardNumber(card_number);
                    msg.setAction("login with card number");
                    SimpleClient.getClient().sendToServer(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Subscribe
    public void onLoginWithCardEvent(stationLoginEvent event) {
        String status = event.getStatus();

        if (status != null) {
            if (status.equals("you are already logged in")) {
                loginFailedWarning.setVisible(true);
                loginFailedWarning.setText("Login Failed- user is already logged in");
                System.out.println("you are already logged in");

            } else if (status.equals(("Wrong CardNumber"))){
                loginFailedWarning.setVisible(true);
                loginFailedWarning.setText("Login Failed- incorrect Card Number.\nPlease try again or go to the main office");


            }else {
                App.setUsername(event.getUsername());
                App.setUserType(event.getUserType());
                App.setFirst_name(event.getFirst_name());
                EventBus.getDefault().unregister(this);
                ChangeScreens.changeToStationMainPage();
            }
        }
    }
}
