package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.EmployeeEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.nearestAppsEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.updateArrivalTimeEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Employee;
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

/**
 * EmployeeMainPage
 * Contains a table that display the appointment of the employee scheduled for today.
 * Contains a textField that receives a card number of patient and a button in order to update when a patient arrived.
 * Contains a button to call next patient - when clicking on it a message will appear in all the waiting room screens
 * associated with the relevant appointment (which means all the screens associated to specific clinic where the
 * appointment is scheduled.
 */

public class EmployeeMainPage {
    private Employee employee ;

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
    private Label successfulUpdateLabel;

    @FXML
    private Pane menubar;

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
            msg.setAction("updateArrivedTime");
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pressOnCallNextPatient(ActionEvent event) {
        try {
            Message msg = new Message();
            msg.setAction("call next patient");
            msg.setAppointment(nearestAppTable.getSelectionModel().getSelectedItem());
            msg.setEmployee(this.employee);
            SimpleClient.getClient().sendToServer(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void getEmployee() throws IOException {
        Message msg = new Message();
        msg.setAction("get employee from username");
        msg.setUsername(App.getUsername());
        SimpleClient.getClient().sendToServer(msg);
    }

    @Subscribe
    public void employeeEvent (EmployeeEvent event){
        System.out.println("in employeeEvent"+event.getEmployee().getFullName());
        employee = event.getEmployee();
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        successfulUpdateLabel.setVisible(false);
        welcomeTF.setText("Welcome " + App.getFirst_name());
        getEmployee();
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

    @Subscribe
    public void onUpdateArrivalTimeEvent(updateArrivalTimeEvent event){
        successfulUpdateLabel.setVisible(true);
    }
}
