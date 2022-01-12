package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.LabWorker;
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

public class CovidTestAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label chooseLabel;

    @FXML
    private URL location;

    @FXML
    private TableColumn<AvailableApp,String> ClinicCol1;

    @FXML
    private ChoiceBox<String > ClinicName;

    @FXML
    private TableColumn<AvailableApp,String> EmployeeName1;

    @FXML
    private Pane Menubar;

    @FXML
    private Label warningtext1;

    @FXML
    private TableView<AvailableApp> Table1;

    @FXML
    private TableColumn<AvailableApp, LocalTime> TimeCol1;

    @FXML
    private Label cliniclabel;

    @FXML
    private TableColumn<AvailableApp, LocalDate> dateCol1;

    @FXML
    private Button ok;

    @FXML
    private Button save;

    @FXML
    private Label warningtext;

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
                msg.setAction("Add covid test appointment");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            warningtext1.setVisible(true);
    }
    @Subscribe
    public void onGotLabWorkersVaccineEven(GotLabWorkersVaccineEven event) {
        List<LabWorker> labWorkers = event.getLabworkers();
        List<Appointment> appointmentList = event.getAppointmentList();
        Clinic clinic = event.getClinic();
        LocalDate date = LocalDate.now();
        date = date.plusDays(1);
        LocalTime time = clinic.getOpeningHour();
        LocalDate end_date = date.plusWeeks(3);
        end_date = end_date.plusDays(1);
        while (date.isBefore(end_date)) {
            while (time.isBefore(clinic.getClosingHour())) {
                for (LabWorker labWorker : labWorkers) {
                    boolean available = true;
                    for (Appointment app : appointmentList) {
                        if (app.getTime().equals(time) && app.getDate().equals(date) && app.getEmployee().getUsername().equals(labWorker.getUsername())) {
                            available = false;
                            break;
                        }
                    }

                    if (available) {
                        if (time.isAfter(labWorker.getStart_working_hour()) && time.isBefore(labWorker.getFinish_working_hour())) {
                            dateCol1.setCellValueFactory(new PropertyValueFactory<>("dateCol"));
                            TimeCol1.setCellValueFactory(new PropertyValueFactory<>("timeCol"));
                            EmployeeName1.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                            ClinicCol1.setCellValueFactory(new PropertyValueFactory<>("clinicCol"));
                            Table1.getItems().add(new AvailableApp(date, time, labWorker.getFullName(), clinic.getName(), labWorker.getUserId(), labWorker.getRole()));
                        }
                    }
                }

                time = time.plusMinutes(10);
            }
            time = clinic.getOpeningHour();
            date = date.plusDays(1);
        }
        dateCol1.setVisible(true);
        TimeCol1.setVisible(true);
        EmployeeName1.setVisible(true);
        ClinicCol1.setVisible(true);
        Table1.setVisible(true);
        save.setVisible(true);
    }
    @Subscribe
    public void onSavedApp(SavedAppEvent event) {
        if (event.isSaved()) {
            showAlert("Saved", "The appointment was saved successfully!");
            ChangeScreens.changeToMainPage();
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
    @FXML
    void ViewTable(ActionEvent event) {
        if (ClinicName.getSelectionModel() == null || ClinicName.getSelectionModel().getSelectedIndex() == -1) {
            warningtext.setText("Please choose a clinic first!");
            warningtext.setVisible(true);
        } else {
            String clinic_name = ClinicName.getSelectionModel().getSelectedItem();

            warningtext.setVisible(false);
            cliniclabel.setVisible(false);
            ClinicName.setVisible(false);
            ok.setVisible(false);
            try {
                Message msg = new Message();
                msg.setClinicName(clinic_name);
                msg.setRole("Lab worker");
                msg.setAction("Get LabWorkers and clinic apps");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        warningtext.setVisible(false);
        warningtext.setVisible(false);
        dateCol1.setVisible(false);
        TimeCol1.setVisible(false);
        EmployeeName1.setVisible(false);
        ClinicCol1.setVisible(false);
        Table1.setVisible(false);
        save.setVisible(false);
        chooseLabel.setVisible(false);
        warningtext.setVisible(false);
        dateCol1.setVisible(false);
        TimeCol1.setVisible(false);
        EmployeeName1.setVisible(false);
        ClinicCol1.setVisible(false);
        Table1.setVisible(false);
        ClinicName.setVisible(true);
        ClinicName.getItems().clear();
        ClinicName.getItems().add("Denia");
        ClinicName.getItems().add("Neve shaanan");
        ClinicName.getItems().add("Hadar");
        ClinicName.getItems().add("Nesher");
        ClinicName.getItems().add("Carmel");
        ClinicName.getItems().add("Tirat Carmel");
        cliniclabel.setVisible(true);
        ok.setVisible(true);
        warningtext1.setVisible(false);
    }

}
