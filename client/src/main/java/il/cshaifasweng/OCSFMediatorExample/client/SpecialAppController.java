package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SpecialAppController {


    @FXML
    private TableColumn<AvailableApp,String> clinicCol;

    @FXML
    private TableColumn<AvailableApp,String> employeeName;

    @FXML
    private Pane Menubar;

    @FXML
    private ChoiceBox<String> SpecialityType;

    @FXML
    private TableView<AvailableApp> Table;

    @FXML
    private TableColumn<AvailableApp,LocalTime> timeCol;

    @FXML
    private Label chooseLabel;

    @FXML
    private TableColumn<AvailableApp,LocalDate> dateCol;

    @FXML
    private Label doctorLabel;

    @FXML
    private ChoiceBox<String> doctorName;

    @FXML
    private Button ok;

    @FXML
    private Button save;

    @FXML
    private Label specialityLabel;

    @FXML
    private Label warningtext;

    @FXML
    private Label warningtext1;

    private int phase = 0;
    private String doctor_name;
    private SpecialDoctor specialDoctor;
    private String role;
    private List<SpecialDoctor> specialDoctorList;

    @FXML
    void SaveNewApp(ActionEvent event) {
        if (Table.getSelectionModel().getSelectedItems() != null && Table.getSelectionModel().getSelectedIndex() != -1) {
            warningtext.setVisible(false);
            try {
                AvailableApp availableApp = Table.getSelectionModel().getSelectedItem();
                Message msg = new Message();
                msg.setClinicName(availableApp.getClinicCol());
                msg.setUsername(App.getUsername());
                msg.setAppDate(availableApp.getDateCol());
                msg.setEmployee_id(availableApp.getEmployee_id());
                msg.setAppTime(availableApp.getTimeCol());
                msg.setRole(availableApp.getRole());
                msg.setAction("Add new special doctor appointment");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else
            warningtext.setVisible(true);
    }

    @FXML
    void ViewTable(ActionEvent event) {
        if (phase == 0) {
            if (SpecialityType.getSelectionModel() == null || SpecialityType.getSelectionModel().getSelectedIndex() == -1) {
                warningtext1.setText("Please choose Speciality first!");
                warningtext1.setVisible(true);
            } else {
                warningtext.setVisible(false);
                warningtext1.setVisible(false);
                role = SpecialityType.getSelectionModel().getSelectedItem();
                warningtext.setVisible(false);
                SpecialityType.setVisible(false);
                specialityLabel.setVisible(false);
                phase++;
                try {
                    Message msg = new Message();
                    msg.setRole(role);
                    msg.setUsername(App.getUsername());
                    msg.setAction("Get special doctor");
                    SimpleClient.getClient().openConnection();
                    SimpleClient.getClient().sendToServer(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            if (doctorName.getSelectionModel() == null || doctorName.getSelectionModel().getSelectedIndex() == -1) {
                warningtext1.setText("Please choose a doctor first!");
                warningtext1.setVisible(true);
            } else {
                doctorLabel.setVisible(false);
                warningtext.setVisible(false);
                warningtext1.setVisible(false);
                doctor_name = doctorName.getSelectionModel().getSelectedItem();
                doctorName.setVisible(false);
                ok.setVisible(false);
                for (SpecialDoctor specialDoctor1 : specialDoctorList)
                    if (specialDoctor1.getFullName().equals(doctor_name)) {
                        specialDoctor = specialDoctor1;
                        break;
                    }
                try {
                    Message msg = new Message();
                    msg.setRole(role);
                    for (SpecialDoctor doctor : specialDoctorList)
                        if (doctor.getFullName().equals(doctorName.getSelectionModel().getSelectedItem())) {
                            specialDoctor = doctor;
                            break;
                        }
                    msg.setUsername(specialDoctor.getUsername());
                    msg.setAction("Get special doctor appointments and clinics");
                    SimpleClient.getClient().openConnection();
                    SimpleClient.getClient().sendToServer(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Subscribe
    public void OnSpecialDocEvent(GotSpecialDocEvent event) {
        specialityLabel.setVisible(false);
        SpecialityType.setVisible(false);
        warningtext1.setVisible(false);
        specialDoctorList = event.getSpecialDoctorList();
        doctorName.getItems().clear();
        for (SpecialDoctor specialDoctor : specialDoctorList) {
            doctorName.getItems().add(specialDoctor.getFullName());
        }
        doctorName.setVisible(true);
        doctorLabel.setVisible(true);
    }

    @Subscribe
    public void GotClinicsEvent(SpecialDocClinicsEvent event) {
        specialDoctor=event.getSpecialDoctor();
        List<Clinic> clinicList = event.getClinics();
        List<specialDoctorApp> appointmentList = event.getAppointments();
        LocalDate date = LocalDate.now();
        date = date.plusDays(1);
        LocalDate end_date = date.plusMonths(3);
        end_date = end_date.plusDays(1);
        LocalTime time = specialDoctor.getStart_working_hour();
            while (date.isBefore(end_date)) {
                while ((time.isAfter(specialDoctor.getStart_working_hour() )|| time.equals(specialDoctor.getStart_working_hour())) && (time.isBefore(specialDoctor.getFinish_working_hour())|| time.equals(specialDoctor.getFinish_working_hour()))) {
                    boolean available = true;
                    if (appointmentList != null && !appointmentList.isEmpty()) {
                        for (Appointment app : appointmentList) {
                            if (app.getTime().equals(time) && app.getDate().equals(date)) {
                                available = false;
                                break;
                            }
                        }
                    }
                    if (available) {
                        for (Clinic clinic1 : clinicList) {
                            if (time.isAfter(clinic1.getOpeningHour()) && time.isBefore(clinic1.getClosingHour())) {
                                dateCol.setCellValueFactory(new PropertyValueFactory<>("dateCol"));
                                timeCol.setCellValueFactory(new PropertyValueFactory<>("timeCol"));
                                employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                                clinicCol.setCellValueFactory(new PropertyValueFactory<>("clinicCol"));
                                Table.getItems().add(new AvailableApp(date, time, specialDoctor.getFullName(), clinic1.getName(), specialDoctor.getUserId(), specialDoctor.getRole()));
                            }
                        }
                    }
                    time = time.plusMinutes(20);
                }
                time = specialDoctor.getStart_working_hour();
                date = date.plusDays(1);
            }
        dateCol.setVisible(true);
        timeCol.setVisible(true);
        employeeName.setVisible(true);
        clinicCol.setVisible(true);
        Table.setVisible(true);
        save.setVisible(true);
    }

    @Subscribe
    public void onSavedApp(SavedAppEvent event) {
        if (event.isSaved()) {
            showAlert("Saved", "The appointment was saved successfully!");
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
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        warningtext.setVisible(false);
        warningtext.setVisible(false);
        save.setVisible(false);
        chooseLabel.setVisible(false);
        warningtext.setVisible(false);
        dateCol.setVisible(false);
        timeCol.setVisible(false);
        employeeName.setVisible(false);
        clinicCol.setVisible(false);
        doctorLabel.setVisible(false);
        Table.setVisible(false);
        specialityLabel.setVisible(true);
        SpecialityType.getItems().clear();
        SpecialityType.getItems().add("cardiologist");
        SpecialityType.getItems().add("ENT doctor");
        SpecialityType.getItems().add("gynecologist");
        SpecialityType.getItems().add("dermatologist");
        SpecialityType.getItems().add("ophthalmologist");
        SpecialityType.getItems().add("neurologist");
        SpecialityType.setVisible(true);
        ok.setVisible(true);
        doctorLabel.setVisible(false);
        doctorName.setVisible(false);
        warningtext1.setVisible(false);
    }
}
