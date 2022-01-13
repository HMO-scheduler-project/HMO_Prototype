package il.cshaifasweng.OCSFMediatorExample.client;


import il.cshaifasweng.OCSFMediatorExample.client.events.RemoveAppEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.ViewAppsEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
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
    private Button cancel;

    @FXML
    private TextField date;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField employeeName;

    @FXML
    private Button submit;

    @FXML
    private TextField time;

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
        date.setVisible(false);
        time.setVisible(false);
        employeeName.setVisible(false);
        submit.setVisible(false);
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
    public void pressCancelAppButton(ActionEvent event){
        date.setVisible(true);
        time.setVisible(true);
        employeeName.setVisible(true);
        date.clear();
        time.clear();
        employeeName.clear();
        submit.setVisible(true);

    }

    @FXML
    public void pressSubmitAppButton(ActionEvent event){
        Message msg = new Message();
        msg.setAppDate(LocalDate.parse(date.getText()));
        msg.setAppTime(LocalTime.parse(time.getText()));
        msg.setEmployeeName(employeeName.getText());
        msg.setUsername(App.getUsername());
        msg.setAction("remove app");
        try{
            SimpleClient.getClient().sendToServer(msg);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onRemoveAppEvent(RemoveAppEvent event){
        if (event.isRemoved()) {
            showAlert("Removed", "The appointment was removed successfully!");
        } else {
            showAlert("Error", "This appointment wasn't removed. Please try again!");
        }
        ChangeScreens.changeToViewAppsScreen();
    }

    public void showAlert(String title, String head) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(head);
                alert.show();
            }
        });
    }


}