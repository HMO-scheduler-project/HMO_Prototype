package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.io.IOException;
import java.time.LocalTime;

public class waitingRoomScreenController {
    private String clinic; // string
                        //maybe its better to use chooseclinic class
    @FXML
    private TextField timeTF;
    @FXML
    private Label nextPatientLabel;
    @FXML
    private TextField nextPatientTF;
    @FXML
    private Label roomLabel;
    @FXML
    private TextField roomTF;

    @FXML
    private ComboBox<String> ClinicsList;

    @Subscribe
    private void clinicClientListed (clinicClientEvent event){
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("waiting Room Listed");
                alert.setHeaderText(null);
                alert.setContentText("waiting room listed successfully");
                alert.show();
            }
        });
    }
    @Subscribe
    public void printMessageToScreen(printMessageToScreenEvent event) {
        nextPatientTF.setText(event.getPatient_name());
        roomTF.setText(Integer.toString(event.getRoom()));
        nextPatientTF.setVisible(true);
        roomTF.setVisible(true);
    }
    @FXML
    void chooseClinic() {
        clinic = ClinicsList.getSelectionModel().getSelectedItem();
        App.setClinic_name(clinic);
        Message Msg = new Message();
        Msg.setClinicName(clinic);
        Msg.setAction("add waiting room screen");
        try{
            SimpleClient.getClient().sendToServer(Msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void onClinicListUpdateEvent(ClinicListStationUpdateEvent event) {
        for (String clinic : event.getClinicNames()) {
            ClinicsList.getItems().add(clinic);
        }
    }

    @FXML
    void initialize() throws IOException {

        EventBus.getDefault().register(this);
        nextPatientLabel.setVisible(false);
        roomLabel.setVisible(false);
        nextPatientTF.setVisible(false);
        roomTF.setVisible(false);


        try {
            Message msg= new Message();
            msg.setAction("GetAllClinicsForStation");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeTF.setText(currentTime.getHour() + ":" + currentTime.getMinute());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }
}
