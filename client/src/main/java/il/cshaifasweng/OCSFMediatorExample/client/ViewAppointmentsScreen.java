package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.client.events.CanceledAppEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.RemoveAppEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.ViewAppsEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
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
 * ViewAppointmentsScreen
 * Contains a table that displays future appointments of logged in patient.
 * Also contains buttons for canceling/changing appointment.
 * After selecting an appointment from the table and pressing cancel/change button - the program will attempt to cancel/change
 * the appointment and an alert will pop up with result (success or not).
 *
 */

public class ViewAppointmentsScreen {
    @FXML
    private TableColumn<Appointment, String> Clinic;

    @FXML
    private TableColumn<Appointment, LocalDate> Date;

    @FXML
    private TableColumn<Appointment, String> EmployeeName;

    @FXML
    private Pane Menubar;

    @FXML
    private TableColumn<Appointment, LocalTime> Time;

    @FXML
    private TableColumn<Appointment, String> Type;

    @FXML
    private TableView<Appointment> viewAppTab;

    @FXML
    private Button cancel;

    @FXML
    private Button change;

    @FXML
    public void initialize() {
        Parent menuBarParent = null;
        try {
            menuBarParent = App.loadFXML("menuBar.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        try {
            Message msg = new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("ViewApps");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onViewAppsUpdateEvent(ViewAppsEvent event) {
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        EmployeeName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmployee().getFullName()));
        Type.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
        Clinic.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClinic().getName()));
        viewAppTab.setItems(FXCollections.observableList(event.getAppsList()));
    }

    @FXML
    public void pressCancelAppButton(ActionEvent event) {
        if (viewAppTab.getSelectionModel().getSelectedItems() != null && viewAppTab.getSelectionModel().getSelectedIndex() != -1) {
            Message msg = new Message();
            msg.setAppointment(viewAppTab.getSelectionModel().getSelectedItem());
            msg.setUsername(App.getUsername());
            msg.setAction("remove app");
            try {
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            showAlert("Error", "Please choose appointment first");

    }


    @Subscribe
    public void onRemoveAppEvent(RemoveAppEvent event) {
        if (event.isRemoved()) {
            showAlert("Removed", "The appointment was removed successfully!");
        } else {
            showAlert("Error", "This appointment wasn't removed. Please try again!");
        }
        ChangeScreens.changeToViewAppsScreen();
    }

    @FXML
    public void pressOnChangeAppBtn(ActionEvent event) {
        if (viewAppTab.getSelectionModel().getSelectedItem() != null && viewAppTab.getSelectionModel().getSelectedIndex() != -1) {
           Message msg = new Message();
           msg.setAppointment(viewAppTab.getSelectionModel().getSelectedItem());
           msg.setUsername(App.getUsername());
           msg.setAction("cancel appointment");
            try {
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            showAlert("Error", "Please choose appointment first");
    }

    @Subscribe
    public void onCancelAppEvent(CanceledAppEvent event){
        ChangeScreens.changeNewAppScreen();
    }

    public void showAlert(String title, String head) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(head);
                alert.showAndWait();
            }
        });
    }


}