package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;


public class openningHoursScreenController {
    Message clientMsg = new Message();
    boolean manager;
    /*
    @FXML
    private MenuItem ChangeAppBtn;
*/
    @FXML
    private TableColumn<Time, Time> OpenningHourColumn;
    @FXML
    private TableColumn<Time, Time> ClosingHourColumn;
    @FXML
    private Button ChangeHoursBtn;

    @FXML
    private Menu ClinicsBtn;

    @FXML
    private ComboBox<String> ClinicsList;

    @FXML
    private TextField closeHourTF;
    /*
        @FXML
        private Menu MainPageBtn;
    */
    @FXML
    private MenuItem OpenningHoursBtn;

    @FXML
    private Button submitChangeHoursBtn;
    /*
        @FXML
        private MenuItem contactInfoBtn;

        @FXML
        private MenuItem newAppBtn;
    */
    @FXML
    private TextField openHourTF;
    /* @FXML
        private MenuItem scheduledAppBtn;
     */
    @FXML
    void pressChangeAppBtn(ActionEvent event) {}

    @FXML
    void pressContactInfoBtn(ActionEvent event) {}

    @FXML
    void pressMainPageBtn(ActionEvent event) {}

    @FXML
    void pressNewAppBtn(ActionEvent event) {}
    @FXML
    void pressOpenningHoursBtn(ActionEvent event) {}

    @FXML
    void pressChangeHoursBtn(ActionEvent event) {
        openHourTF.setVisible(true);
        closeHourTF.setVisible(true);
    }

    @FXML
    void pressSubmitChangeHoursBtn(ActionEvent event){
        clientMsg.setAction("change hours");
        try{
            if(!openHourTF.getText().equals("")) {
                clientMsg.setOpenningHour(LocalTime.parse(openHourTF.getText()));
            }
            if(!closeHourTF.getText().equals("")) {
                clientMsg.setClosingHour(LocalTime.parse(closeHourTF.getText()));
            }
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onChangeHoursEvent(ChangeHoursEvent event){
        OpenningHourColumn.setText(String.valueOf(clientMsg.getClinic().getOpenningHour()));
        ClosingHourColumn.setText(String.valueOf(clientMsg.getClinic().getClosingHour()));
    }

    @FXML
    void pressScheduledAppBtn(ActionEvent event) {}

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
       // manager = App.getType().equals("Manager");
        manager = true;
        try {
            Message msg= new Message();
            msg.setAction("GetAllClinics");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Subscribe
    public void onClinicListUpdateEvent(ClinicListUpdateEvent event) {
        System.out.println("Got event");
        System.out.println("clinic list in client is empty: "+event.getClinicNames().isEmpty());
        for (String clinic : event.getClinicNames()) {
            System.out.println(clinic);
            ClinicsList.getItems().add(clinic);
        }
    }

    @Subscribe
    public void onChosenClinicEvent(ChosenClinicEvent event) {
        System.out.println("Got chosen clinic");
        OpenningHourColumn.setText(String.valueOf(clientMsg.getClinic().getOpenningHour()));
        ClosingHourColumn.setText(String.valueOf(clientMsg.getClinic().getClosingHour()));
        if (manager) {
            ChangeHoursBtn.setVisible(true);
        }
        clientMsg.setOpenningHour(null);
        clientMsg.setClosingHour(null);
    }

    @FXML
    void chooseClinic() {
        System.out.println("choose clinic");
        String chosenClinic = ClinicsList.getSelectionModel().getSelectedItem();
        clientMsg.setClinicName(chosenClinic);
        clientMsg.setAction("GetClinicFromName");
        try {
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

