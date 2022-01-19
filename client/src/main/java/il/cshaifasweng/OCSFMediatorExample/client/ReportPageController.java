package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import il.cshaifasweng.OCSFMediatorExample.entities.AwaitingTimeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.MissedAppRep;
import il.cshaifasweng.OCSFMediatorExample.entities.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.client.events.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.hibernate.type.descriptor.sql.NVarcharTypeDescriptor;

/**
 * ReportPageController
 * Contains combobox for choosing clinic from a list of clinics that the logged in manager is managing.
 * Also contains switch tabs with tables displaying the reports of chosen clinic.
 *
 */

public class ReportPageController {
    Message clientMsg = new Message();
    String chosen_clinic;

    @FXML
    Pane menubar;
    @FXML
    private Label ChooseMessage;

    @FXML
    private MenuItem ChangeAppBtn;

    @FXML
    private Button ChangeHoursBtn;

    @FXML
    private Menu ClinicsBtn;

    @FXML
    private ComboBox<String> ClinicsList;

    @FXML
    private Menu MainPageBtn;

    @FXML
    private Button MissedAppBtn;

    @FXML
    private MenuItem OpenningHoursBtn;

    @FXML
    private MenuItem contactInfoBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private MenuItem newAppBtn;

    @FXML
    private MenuItem scheduledAppBtn;

    @FXML
    private Tab UnclaimedAppointment;

    @FXML
    private TableView<MissedAppRep> UnclaimedAppointmentRep;

    @FXML
    private TableColumn<MissedAppRep, Integer> UnclaimedAppointmentColumnB;

    @FXML
    private TableColumn<MissedAppRep, Integer> UnclaimedAppointmentColumnC;

    @FXML
    private TableColumn<MissedAppRep, Integer> UnclaimedAppointmentColumnD;

    @FXML
    private TableColumn<MissedAppRep, Integer> UnclaimedAppointmentColumnE;

    @FXML
    private TableColumn<MissedAppRep, Integer> UnclaimedAppointmentColumnF;

    @FXML
    private TableColumn<MissedAppRep, Integer> UnclaimedAppointmentColumnG;
    @FXML
    private TableColumn<MissedAppRep, Integer> UnclaimedAppointmentColumnH;
    @FXML
    private Tab ServicesTypeBtn;

    @FXML
    private TableView<ServicesTypeRep> ServicesTypeRepTable;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnA;

    @FXML
    private TableColumn<ServicesTypeRep, Integer> ServicesTypeColumnB;

    @FXML
    private TableColumn<ServicesTypeRep, Integer> ServicesTypeColumnC;

    @FXML
    private TableColumn<ServicesTypeRep, Integer> ServicesTypeColumnD;

    @FXML
    private TableColumn<ServicesTypeRep, Integer> ServicesTypeColumnE;

    @FXML
    private TableColumn<ServicesTypeRep, Integer> ServicesTypeColumnF;

    @FXML
    private TableColumn<ServicesTypeRep, Integer> ServicesTypeColumnG;
    @FXML
    private TableColumn<ServicesTypeRep, Integer> ServicesTypeColumnH;
    @FXML
    private Tab WaitingTimeReport;

    @FXML
    private TableView<AwaitingTimeRep> AwaitingTimeRepTable;

    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnA;

    @FXML
    private TableColumn<AwaitingTimeRep, Double> AwaitingTimeRepColumnB;

    @FXML
    private TableColumn<AwaitingTimeRep, Double> AwaitingTimeRepColumnC;

    @FXML
    private TableColumn<AwaitingTimeRep, Double> AwaitingTimeRepColumnD;

    @FXML
    private TableColumn<AwaitingTimeRep, Double> AwaitingTimeRepColumnE;

    @FXML
    private TableColumn<AwaitingTimeRep, Double> AwaitingTimeRepColumnF;

    @FXML
    private TableColumn<AwaitingTimeRep, Double> AwaitingTimeRepColumnG;

    @FXML
    void initialize() throws IOException  {

        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        String cssPath = getClass().getResource("/style.css").toString();
        menuBarParent.getStylesheets().add(cssPath);
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        menubar.resize(750,58);
        EventBus.getDefault().register(this);
        Message msg = new Message();
        try {
            if (App.getUserType().equals("HMO_Manager")) {
                msg.setAction("GetAllClinics");
            } else if (App.getUserType().equals("Manager")) {
                msg.setAction("getClinicNameFromUserName");
                msg.setUsername(App.getUsername());
            }
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onClinicNameUpdateEvent(ClinicNameUpdateEvent event) {
        clientMsg.setClinicName(event.getClinicName());
        ClinicsList.getItems().add(clientMsg.getClinicName());
    }

    @Subscribe
    public void onClinicListUpdateEvent(ClinicListUpdateEvent event) {
        for (String clinic : event.getClinicNames()) {
            ClinicsList.getItems().add(clinic);
        }
    }
    @FXML
    void chooseClinic() {
        chosen_clinic = ClinicsList.getSelectionModel().getSelectedItem();
        clientMsg.setClinicName(chosen_clinic);
        clientMsg.setAction("getAllRep");
        try {
            SimpleClient.getClient().sendToServer(clientMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Subscribe
    public void onAllTypeRepEvent(AllTypeRepEvent event) {
        AwaitingTimeRepColumnA.setCellValueFactory(new PropertyValueFactory<>("DoctorName"));
        AwaitingTimeRepColumnB.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_A"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_B"));
        AwaitingTimeRepColumnD.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_C"));
        AwaitingTimeRepColumnE.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_D"));
        AwaitingTimeRepColumnF.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_E"));
        AwaitingTimeRepColumnG.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_F"));
        AwaitingTimeRepTable.setItems(FXCollections.observableList(event.getAwaitingTimeRep()));

        ServicesTypeColumnA.setCellValueFactory(new PropertyValueFactory<>("DayOfTheWeek"));
        ServicesTypeColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctorPatientNumber"));
        ServicesTypeColumnC.setCellValueFactory(new PropertyValueFactory<>("PediatricianPatientNumber"));
        ServicesTypeColumnD.setCellValueFactory(new PropertyValueFactory<>("VaccineAppointment"));
        ServicesTypeColumnE.setCellValueFactory(new PropertyValueFactory<>("LabResults"));
        ServicesTypeColumnF.setCellValueFactory(new PropertyValueFactory<>("CovidTest"));
        ServicesTypeColumnG.setCellValueFactory(new PropertyValueFactory<>("NurseCare"));
        ServicesTypeColumnH.setCellValueFactory(new PropertyValueFactory<>("Special_Doctor"));
        ServicesTypeRepTable.setItems(FXCollections.observableList(event.getServicesTypeRep()));



        UnclaimedAppointmentColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctorPatientNumber"));
        UnclaimedAppointmentColumnC.setCellValueFactory(new PropertyValueFactory<>("PediatricianPatientNumber"));
        UnclaimedAppointmentColumnD.setCellValueFactory(new PropertyValueFactory<>("VaccineAppointment"));
        UnclaimedAppointmentColumnE.setCellValueFactory(new PropertyValueFactory<>("LabResults"));
        UnclaimedAppointmentColumnF.setCellValueFactory(new PropertyValueFactory<>("CovidTest"));
        UnclaimedAppointmentColumnG.setCellValueFactory(new PropertyValueFactory<>("NurseCare"));
        UnclaimedAppointmentColumnH.setCellValueFactory(new PropertyValueFactory<>("Special_Doctor"));
        UnclaimedAppointmentRep.setItems(FXCollections.observableList(event.getMissedAppRep()));
    }





}
