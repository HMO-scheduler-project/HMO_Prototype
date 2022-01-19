package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.ClinicListStationUpdateEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

/**
 * ChooseClinic
 * Controller for ChooseClinic screen.
 * Display list of clinics in order to associate the station to a specific clinic.
 *
 */

public class ChooseClinic {
    Message clientMsg = new Message();
    @FXML
    private ComboBox<String> ClinicsList;

    @FXML
    void initialize() throws IOException {
        EventBus.getDefault().register(this);
        try {
            Message msg= new Message();
            msg.setAction("GetAllClinicsForStation");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
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
    void chooseClinic() {
        String chosenClinic = ClinicsList.getSelectionModel().getSelectedItem();
        App.setClinic_name(chosenClinic);
        clientMsg.setClinicName(chosenClinic);
        ChangeScreens.changeToStationLogin();
    }
}
