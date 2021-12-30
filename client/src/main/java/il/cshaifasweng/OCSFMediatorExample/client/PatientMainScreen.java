package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class PatientMainScreen {


    @FXML
    private Pane menubar;

    @FXML
    private TableView<Appointment> nearestAppTable;

    @FXML
    private Button newAppBtn;

    @FXML
    private TextField welcomeTF;

    @FXML
    void pressOnNewApp(ActionEvent event) {
        ChangeScreens.changeNewAppScreen();
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        welcomeTF.setText("Welcome" + App.getFirst_name());
        try {
            Message msg= new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("GetNearestApps");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onNearestAppsUpdateEvent(nearestAppsEvent event) {
        nearestAppTable.setItems(FXCollections.observableList(event.getAppsList()));
    }
}
