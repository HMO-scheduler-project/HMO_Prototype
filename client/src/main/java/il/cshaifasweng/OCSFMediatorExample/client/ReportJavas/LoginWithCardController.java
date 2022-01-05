package il.cshaifasweng.OCSFMediatorExample.client.ReportJavas;

import il.cshaifasweng.OCSFMediatorExample.client.*;
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
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
public class LoginWithCardController  implements Initializable {
    private SimpleClient client;
    private static int sessionID = 0;
    private String sessionIDStr;
    @FXML
    private ResourceBundle resources;
    @FXML
    private TextField CardNumber;

    @FXML
    private Button loginBtn;

    @FXML
    void pressOnLogin(ActionEvent event) {

    }
    @FXML
    private Label loginFailedWarning;

    @FXML
    private Label Textbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventBus.getDefault().register(this);
        loginFailedWarning.setVisible(false);
    }
    @FXML
    void authorize(ActionEvent event)  {
        loginFailedWarning.setVisible(false);
        try {
            String cardnumber = CardNumber.getText();

            if (CardNumber.equals("")) {
                loginFailedWarning.setText("Please enter Card Number");
                loginFailedWarning.setVisible(true);
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
                    msg.setUserCardNumber(cardnumber);
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


    @FXML
    void initialize() {
        assert CardNumber != null : "fx:id=\"CardNumber\" was not injected: check your FXML file 'LoginWithCard.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'LoginWithCard.fxml'.";

    }
    @Subscribe
    public void onLoginWithCardEvent(stationLoginEvent event) {
        String status = event.getStatus();
        if (status != null) {
            if (status.equals("you are already logged in")) {
                loginFailedWarning.setText("Login Failed- user is already logged in");
                loginFailedWarning.setVisible(true);
            } else if (status.equals(("Wrong Card Number."))){
                loginFailedWarning.setText("Login Failed- incorrect Card Number.\nPlease try again or go to the main office");
                loginFailedWarning.setVisible(true);

            }else {
                App.setUsername(event.getUsername());
                App.setUserType(event.getUserType());
                EventBus.getDefault().unregister(this);
                ChangeScreens.changeToStationMainPage();
            }
        }
    }



}
