package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalTime;




public class UpdateHoursScreen {
    Message clientMsg = new Message();
    String chosen_clinic;
    String chosen_service;
    String chosen_doctor;

    @FXML
    Pane menubar;
    @FXML
    private Label clinicLabel;
    @FXML
    private ComboBox<String> ClinicsList;
    @FXML
    private Label doctorLabel;
    @FXML
    private ComboBox<String> doctorList;
    @FXML
    private Label serviceLabel;
    @FXML
    private ComboBox<String> serviceList;
    @FXML
    private TextField OpeningHourTF;
    @FXML
    private TextField ClosingHourTF;
    @FXML
    private Button ChangeHoursBtn;
    @FXML
    private TextField openHourTF;
    @FXML
    private TextField closeHourTF;
    @FXML
    private Button submitChangeHoursBtn;
    @FXML
    private Button ChangeRoomBtn;
    @FXML
    private Button submitChangeRoomBtn;
    @FXML
    private TextField RoomTF;
    @FXML
    private TextField updateRoomTF;

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        String cssPath = getClass().getResource("/style.css").toString();
        menuBarParent.getStylesheets().add(cssPath);
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        try {
            ChangeHoursBtn.setVisible(false);
            openHourTF.setVisible(false);
            closeHourTF.setVisible(false);
            submitChangeHoursBtn.setVisible(false);
            ChangeRoomBtn.setVisible(false);
            updateRoomTF.setVisible(false);
            submitChangeRoomBtn.setVisible(false);
            doctorList.setVisible(false);
            doctorLabel.setVisible(false);
            Message msg= new Message();
            msg.setAction("GetAllManagedClinics");
            msg.setUsername(App.getUsername());
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Subscribe
    public void onManagedClinicListUpdateEvent(ManagedClinicListUpdateEvent event) {
        for (String clinic : event.getClinicNames()) {
            if(!ClinicsList.getItems().contains(clinic)) {
                ClinicsList.getItems().add(clinic);
            }
        }
    }

    @FXML
    void chooseClinic() {
        doctorLabel.setVisible(false);
        doctorList.setVisible(false);
        ChangeRoomBtn.setVisible(false);
        ChangeHoursBtn.setVisible(false);
        openHourTF.clear();
        closeHourTF.clear();
        openHourTF.setVisible(false);
        closeHourTF.setVisible(false);
        submitChangeHoursBtn.setVisible(false);
        clientMsg.setOpeningHour(null);
        clientMsg.setClosingHour(null);
        updateRoomTF.clear();
        updateRoomTF.setVisible(false);
        submitChangeRoomBtn.setVisible(false);
        clientMsg.setRoom(0);

        chosen_clinic = ClinicsList.getSelectionModel().getSelectedItem();;
        clientMsg.setClinicName(chosen_clinic);
        clientMsg.setAction("pull services");
        try {
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onServiceListUpdateEvent(ServiceListUpdateEvent event) {
        for (String service : event.getServiceNames()) {
            if(!serviceList.getItems().contains(service)) {
                serviceList.getItems().add(service);
            }
        }
    }

    @FXML
    void chooseService(ActionEvent event) {
        ChangeRoomBtn.setVisible(false);
        ChangeHoursBtn.setVisible(false);
        openHourTF.clear();
        closeHourTF.clear();
        openHourTF.setVisible(false);
        closeHourTF.setVisible(false);
        submitChangeHoursBtn.setVisible(false);
        clientMsg.setOpeningHour(null);
        clientMsg.setClosingHour(null);
        updateRoomTF.clear();
        updateRoomTF.setVisible(false);
        submitChangeRoomBtn.setVisible(false);
        clientMsg.setRoom(0);

        chosen_service = serviceList.getSelectionModel().getSelectedItem();
        clientMsg.setClinicName(chosen_clinic);
       if(chosen_service.equals("doctors")){
           doctorLabel.setVisible(true);
           doctorList.setVisible(true);
           clientMsg.setAction("pull doctors");
        }else if(chosen_service.equals("specialists")) {
            doctorLabel.setVisible(true);
            doctorList.setVisible(true);
            clientMsg.setAction("pull specialists");
        }else{
            doctorLabel.setVisible(false);
            doctorList.setVisible(false);
            clientMsg.setService_name(chosen_service);
            clientMsg.setAction("pull service hours");
        }
        try {
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onDoctorListUpdateEvent(DoctorListUpdateEvent event) {
        doctorList.getItems().clear();
        for (String doctor : event.getDoctorsList()) {
            doctorList.getItems().add(doctor);
        }
    }

    @FXML
    void chooseDoctor(ActionEvent event) {
        chosen_doctor =  doctorList.getSelectionModel().getSelectedItem();
        clientMsg.setDoctor(chosen_doctor);
        clientMsg.setAction("pull doctor hours");
        try {
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Subscribe
    public void onShowServiceHoursEvent(showServiceHoursEvent event){
        OpeningHourTF.setText(String.valueOf(event.getStart()));
        ClosingHourTF.setText(String.valueOf(event.getFinish()));
        if(event.getRoom()>0){
            RoomTF.setText(Integer.toString(event.getRoom()));
        }else{
            RoomTF.clear();
        }
        ChangeHoursBtn.setVisible(true);
        openHourTF.clear();
        closeHourTF.clear();
        openHourTF.setVisible(false);
        closeHourTF.setVisible(false);
        submitChangeHoursBtn.setVisible(false);
        clientMsg.setOpeningHour(null);
        clientMsg.setClosingHour(null);
        ChangeRoomBtn.setVisible(true);
        updateRoomTF.clear();
        updateRoomTF.setVisible(false);
        submitChangeRoomBtn.setVisible(false);
        clientMsg.setRoom(0);
    }

    @FXML
    void pressChangeHoursBtn(ActionEvent event) {
        openHourTF.setVisible(true);
        closeHourTF.setVisible(true);
        submitChangeHoursBtn.setVisible(true);
    }

    @FXML
    void pressSubmitChangeHoursBtn(ActionEvent event){
        clientMsg.setAction("change hours");
        clientMsg.setService_name(chosen_service);
        clientMsg.setClinicName(chosen_clinic);
        if(chosen_service.equals("doctors") ||chosen_service.equals("specialists")){
            clientMsg.setDoctor(chosen_doctor);
        }
        try{
            if(!openHourTF.getText().equals("")) {
                clientMsg.setOpeningHour(LocalTime.parse(openHourTF.getText()));
            }
            if(!closeHourTF.getText().equals("")) {
                clientMsg.setClosingHour(LocalTime.parse(closeHourTF.getText()));
            }
            System.out.println("before update");
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onChangeHoursEvent(ChangeHoursEvent event){
        System.out.println("after update");
        OpeningHourTF.setText(String.valueOf(event.getOpening_hour()));
        ClosingHourTF.setText(String.valueOf(event.getClosing_hour()));
    }

    @FXML
    public void pressChangeRoomBtn(){
        updateRoomTF.setVisible(true);
        submitChangeRoomBtn.setVisible(true);
    }

    @FXML
    public void pressSubmitChangeRoomBtn(){
        if(Integer.parseInt(updateRoomTF.getText())<1){
            updateRoomTF.setPromptText("Illegal room number");
        }else {
            clientMsg.setAction("change room");
            clientMsg.setService_name(chosen_service);
            clientMsg.setClinicName(chosen_clinic);
            if (chosen_service.equals("doctors") || chosen_service.equals("specialists")) {
                clientMsg.setDoctor(chosen_doctor);
            }
            try {
                if (!updateRoomTF.getText().equals("")) {
                    clientMsg.setRoom(Integer.parseInt(updateRoomTF.getText()));
                }
                SimpleClient.getClient().sendToServer(clientMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe
    public void onChangeRoomEvent(ChangeRoomEvent event){
        RoomTF.setText(String.valueOf(event.getRoom()));
    }

}

