package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeeMainPage {

    @FXML
    private Button CallNextPatientBtn;

    @FXML
    private TableColumn<Appointment, LocalDate> DateCol;

    @FXML
    private TableColumn<Appointment, LocalTime> TimeCol;

    @FXML
    private Button arrivalSubmit;

    @FXML
    private TextField currPatientTF;

    @FXML
    private Pane menubar;

    @FXML
    private CheckBox arrivedCheck;

    @FXML
    private TableView<Appointment> nearestAppTable;

    @FXML
    private TableColumn<Appointment, String> patientName;

    @FXML
    private TextField welcomeTF;

    @FXML
    void pressOnArrivalSubmit(ActionEvent event) {
        try {
            Message msg= new Message();
            msg.setUserCardNumber(currPatientTF.getText());
            msg.setArrived(arrivedCheck.isSelected());
            msg.setAction("updateArrivedTime");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pressOnCallNextPatient(ActionEvent event) {

    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        welcomeTF.setText("Welcome " + App.getFirst_name());
        try {
            Message msg= new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("GetPatientsList");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Subscribe
    public void onNearestAppsUpdateEvent(nearestAppsEvent event) {
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        patientName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPatient().getFullName()));
        nearestAppTable.setItems(FXCollections.observableList(event.getAppsList()));
    }
}
