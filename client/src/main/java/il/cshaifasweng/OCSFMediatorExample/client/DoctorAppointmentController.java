package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.BirthDateClinicEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.GotDoctorsEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.SavedAppEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorAppointmentController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<AvailableApp,String> ClinicCol1;

    @FXML
    private TableColumn<AvailableApp, String> EmployeeName1;

    @FXML
    private Pane Menubar;

    @FXML
    private TableView<AvailableApp> Table1;

    @FXML
    private TableColumn<AvailableApp, LocalTime> TimeCol1;

    @FXML
    private TableColumn<AvailableApp, LocalDate> dateCol1;

    @FXML
    private Button save;

    @FXML
    private Label warningtext;

    private Clinic clinic;

    @FXML
    void SaveNewApp(ActionEvent event) {
        if (Table1.getSelectionModel().getSelectedItems() != null && Table1.getSelectionModel().getSelectedIndex() != -1) {
            warningtext.setVisible(false);
            try {
                AvailableApp availableApp = Table1.getSelectionModel().getSelectedItem();
                Message msg = new Message();
                msg.setClinicName(availableApp.getClinicCol());
                msg.setUsername(App.getUsername());
                msg.setAppDate(availableApp.getDateCol());
                msg.setEmployee_id(availableApp.getEmployee_id());
                msg.setAppTime(availableApp.getTimeCol());
                msg.setRole(availableApp.getRole());
                msg.setAction("Add new doctor appointment");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else
            warningtext.setVisible(true);
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        warningtext.setVisible(false);
        try {
            Message msg = new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("Get birthdate and clinic");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void OnBirthdateClinicEvent(BirthDateClinicEvent event)
    {
        clinic=event.getClinic();
        try {
            Message msg = new Message();
            if (event.getAge() >= 18)
                msg.setRole("family_doctor");
            else
                msg.setRole("pediatrician");
            msg.setClinicName(clinic.getName());
            msg.setUsername(App.getUsername());
            msg.setAction("Get employees");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void onSavedApp(SavedAppEvent event) {
        if (event.isSaved()) {
            showAlert("Saved", "The appointment was saved successfully!");
            ChangeScreens.changeToViewAppsScreen();
        } else {
            showAlert("Error", "This appointment wasn't saved please try again!");
        }
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
    @Subscribe
    public void onGotDoctorsEvent(GotDoctorsEvent event) {
        List<Doctor> doctorList = event.getDoctorList();
        List<Appointment> appointmentList = event.getClinicAppointments();
        LocalDate date = LocalDate.now();
        date = date.plusDays(1);
        LocalTime time = clinic.getOpeningHour();
        LocalDate end_date = date.plusWeeks(4);
        end_date = end_date.plusDays(1);
        while (date.isBefore(end_date)) {
            while (time.isBefore(clinic.getClosingHour())) {
                for (Doctor doctor : doctorList) {
                    boolean available = true;
                    for (Appointment app : appointmentList) {
                        if (app.getTime().equals(time) && app.getDate().equals(date) && app.getEmployee().getUsername().equals(doctor.getUsername())) {
                            available = false;
                            break;
                        }
                    }
                    if (available) {
                        if (time.isAfter(doctor.getStart_working_hour()) && time.isBefore(doctor.getFinish_working_hour())) {

                            dateCol1.setCellValueFactory(new PropertyValueFactory<>("dateCol"));
                            TimeCol1.setCellValueFactory(new PropertyValueFactory<>("timeCol"));
                            ClinicCol1.setCellValueFactory(new PropertyValueFactory<>("clinicCol"));
                            EmployeeName1.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                            Table1.getItems().add(new AvailableApp(date, time, doctor.getFullName(), clinic.getName(), doctor.getUserId(), doctor.getRole()));
                        }
                    }
                }
                time = time.plusMinutes(15);
            }
            time = clinic.getOpeningHour();
            date = date.plusDays(1);
        }
    }
}
