package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.client.events.ViewAppsEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

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

}