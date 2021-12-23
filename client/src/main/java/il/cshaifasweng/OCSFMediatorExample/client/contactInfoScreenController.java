package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;



public class contactInfoScreenController {
    Message clientMsg = new Message();
    boolean manager;
    String chosen_clinic;


    @FXML
    private MenuItem ChangeAppBtn;
    @FXML
    private TextField phoneNumTF;
    @FXML
    private TextField addressTF;
    @FXML
    private Button ChangeAddressBtn;
    @FXML
    private Button ChangePhoneNumBtn;
    @FXML
    private Menu ClinicsBtn;

    @FXML
    private ComboBox<String> ClinicsList;

    @FXML
    private TextField ShowAddressTF;

    @FXML
    private Menu MainPageBtn;

    @FXML
    private MenuItem OpenningHoursBtn;

    @FXML
    private Button submitChangeAddressBtn;
    @FXML
    private Button submitChangePhoneBtn;
    @FXML
    private MenuItem contactInfoBtn;
    @FXML
    private MenuItem newAppBtn;
    @FXML
    private TextField ShowPhoneNumTF;
    @FXML
    private MenuItem scheduledAppBtn;

    @FXML
    void pressChangeAppBtn(ActionEvent event) {}
    @FXML
    void pressContactInfoBtn(ActionEvent event) throws IOException {
        ChangeScreens.changeToContactInfoScreen();
    }
    @FXML
    void pressMainPageBtn(ActionEvent event) {}
    @FXML
    void pressNewAppBtn(ActionEvent event) {}
    @FXML
    void pressOpenningHoursBtn(ActionEvent event) throws IOException {
        ChangeScreens.changeToOpenningHoursScreen();
    }

    @FXML
    void pressChangeAddressBtn(ActionEvent event) {
        addressTF.setVisible(true);
        submitChangeAddressBtn.setVisible(true);
    }

    @FXML
    void pressSubmitChangeAddressBtn(ActionEvent event){
        clientMsg.setAction("change address");
        clientMsg.setClinicName(chosen_clinic);
        try{
            if(!addressTF.getText().equals("")) {
                clientMsg.setAddress(addressTF.getText());
            }
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pressChangePhoneNumBtn(ActionEvent event) {
        phoneNumTF.setVisible(true);
        submitChangePhoneBtn.setVisible(true);
    }

    @FXML
    void pressSubmitChangePhoneNumBtn(ActionEvent event){
        clientMsg.setAction("change phone number");
        clientMsg.setClinicName(chosen_clinic);
        try{
            if(!phoneNumTF.getText().equals("")) {
                clientMsg.setPhoneNum(phoneNumTF.getText());
            }
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onChangeAddressEvent(ChangeAddressEvent event){
        ShowAddressTF.setText(clientMsg.getAddress());
    }

    @Subscribe
    public void onChangePhoneNumEvent(ChangePhoneNumEvent event){
        ShowPhoneNumTF.setText(clientMsg.getPhoneNum());
    }

    @FXML
    void pressScheduledAppBtn(ActionEvent event) {}

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        //manager = App.getUserType().equals("Manager");
        manager = true;
        try {
            ChangeAddressBtn.setVisible(false);
            addressTF.setVisible(false);
            ChangePhoneNumBtn.setVisible(false);
            phoneNumTF.setVisible(false);
            submitChangeAddressBtn.setVisible(false);
            submitChangePhoneBtn.setVisible(false);
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
        for (String clinic : event.getClinicNames()) {
            ClinicsList.getItems().add(clinic);
        }
    }


    @Subscribe
    public void onShowContactInfoEvent(showContactInfoEvent event){
        ShowAddressTF.setText(event.getAddress());
        ShowPhoneNumTF.setText(event.getPhoneNum());
        if (manager) {
            ChangeAddressBtn.setVisible(true);
            ChangePhoneNumBtn.setVisible(true);
        }
        addressTF.clear();
        phoneNumTF.clear();
        addressTF.setVisible(false);
        phoneNumTF.setVisible(false);
        submitChangeAddressBtn.setVisible(false);
        submitChangePhoneBtn.setVisible(false);
        clientMsg.setPhoneNum(null);
        clientMsg.setAddress(null);
    }

    @FXML
    void chooseClinic() {
        String chosenClinic = ClinicsList.getSelectionModel().getSelectedItem();
        chosen_clinic = chosenClinic;
        clientMsg.setClinicName(chosenClinic);
        clientMsg.setAction("pull contact info");
        try {
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pressOnLogout(ActionEvent event){
        App.logout(true);
    }
}

