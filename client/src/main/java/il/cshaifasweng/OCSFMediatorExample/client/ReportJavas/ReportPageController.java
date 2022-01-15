package il.cshaifasweng.OCSFMediatorExample.client.ReportJavas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import Reports.WeeklyReport;
import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.client.events.AllTypeRepEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.ClinicListUpdateEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.ClinicNameUpdateEvent;
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

public class ReportPageController {
    Message clientMsg = new Message();
    boolean Manager;
    boolean HMO_Manager;
    String chosen_clinic;

    @FXML
    Pane menubar;
    @FXML
    private Label ChooseMessage;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button ServicesTypeBtn;

    @FXML
    private MenuItem contactInfoBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private MenuItem newAppBtn;

    @FXML
    private MenuItem scheduledAppBtn;


    @FXML
    private Tab MissedAppRep;
    @FXML
    private TableView<MissedAppRep> UnclaimedAppointmentRep;
    @FXML
    private TableColumn<MissedAppRep, String> UnclaimedAppointmentColumnB;

    @FXML
    private TableColumn<MissedAppRep, String> UnclaimedAppointmentColumnC;

    @FXML
    private TableColumn<MissedAppRep, String> UnclaimedAppointmentColumnD;

    @FXML
    private TableColumn<MissedAppRep, String> UnclaimedAppointmentColumnE;

    @FXML
    private TableColumn<MissedAppRep, String> UnclaimedAppointmentColumnF;

    @FXML
    private TableColumn<MissedAppRep, String> UnclaimedAppointmentColumnG;

    @FXML
    private Tab WaitingTimeReport;
    @FXML
    private TableView<ServicesTypeRep> ServicesTypeRepTable;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnA;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnB;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnC;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnD;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnE;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnF;

    @FXML
    private TableColumn<ServicesTypeRep, String> ServicesTypeColumnG;


    @FXML
    private TableView<AwaitingTimeRep> AwaitingTimeRepTable;
    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnA;

    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnB;

    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnC;

    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnD;

    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnE;

    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnF;

    @FXML
    private TableColumn<AwaitingTimeRep, String> AwaitingTimeRepColumnG;



    @FXML
    void pressChangeAppBtn(ActionEvent event) {

    }

    @FXML
    void pressContactInfoBtn(ActionEvent event) {

    }

    @FXML
    void pressMainPageBtn(ActionEvent event) {

    }



    @FXML
    void pressNewAppBtn(ActionEvent event) {

    }

    @FXML
    void pressOnLogout(ActionEvent event) {

    }

    @FXML
    void pressOpenningHoursBtn(ActionEvent event) {

    }

    @FXML
    void pressScheduledAppBtn(ActionEvent event) {

    }








    @FXML
    void pressServicesTypeBtn(ActionEvent event) {
    }

    @FXML
    void pressWaitingTimeReportBtn(ActionEvent event) {
    }
    @FXML
    void pressMissedAppBtn(ActionEvent event) {
    }



    @FXML
    void initialize() throws IOException  {

        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        String cssPath = getClass().getResource("/style.css").toString();
        menuBarParent.getStylesheets().add(cssPath);
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        Manager = App.getUserType().equals("Manager");
        HMO_Manager=App.getUserType().equals("HMO_Manager");
        try {
            clientMsg.setClinicName("");
            if(HMO_Manager) {
                Message msg = new Message();
                msg.setAction("GetAllClinics");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            }
            else {
                clientMsg.setClinicName(App.getUsername());
                Message msg = new Message();
                msg.setAction("getClinicNameFromUserName");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    @Subscribe
    public void onClinicNameUpdateEvent(ClinicNameUpdateEvent event) {
        clientMsg.setClinicName(event.getClinicName());
        ClinicsList.getItems().add(event.getClinicName());
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
        AwaitingTimeRepColumnA.setCellValueFactory(new PropertyValueFactory<>("Doctor"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("Sunday"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("Monday"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("Tuesday"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("Wednesday"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("Thursday"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("Friday"));
        AwaitingTimeRepTable.setItems(FXCollections.observableList(event.getAwaitingTimeRep()));

        ServicesTypeColumnA.setCellValueFactory(new PropertyValueFactory<>("Day"));
        ServicesTypeColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctor"));
        ServicesTypeColumnC.setCellValueFactory(new PropertyValueFactory<>("Pediatrician"));
        ServicesTypeColumnD.setCellValueFactory(new PropertyValueFactory<>("Vaccine_Appointments"));
        ServicesTypeColumnE.setCellValueFactory(new PropertyValueFactory<>("Lab_Test_Appointments"));
        ServicesTypeColumnF.setCellValueFactory(new PropertyValueFactory<>("Covid_Test"));
        ServicesTypeColumnG.setCellValueFactory(new PropertyValueFactory<>("Nurse_Care"));
        ServicesTypeRepTable.setItems(FXCollections.observableList(event.getServicesTypeRep()));



        UnclaimedAppointmentColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctor"));
        UnclaimedAppointmentColumnC.setCellValueFactory(new PropertyValueFactory<>("Pediatrician"));
        UnclaimedAppointmentColumnD.setCellValueFactory(new PropertyValueFactory<>("Vaccine_Appointments"));
        UnclaimedAppointmentColumnE.setCellValueFactory(new PropertyValueFactory<>("Lab_Test_Appointments"));
        UnclaimedAppointmentColumnF.setCellValueFactory(new PropertyValueFactory<>("Covid_Test"));
        UnclaimedAppointmentColumnG.setCellValueFactory(new PropertyValueFactory<>("Nurse_Care"));
        UnclaimedAppointmentRep.setItems(FXCollections.observableList(event.getMissedAppRep()));
    }
}
