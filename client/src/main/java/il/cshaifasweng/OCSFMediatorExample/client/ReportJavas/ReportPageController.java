package il.cshaifasweng.OCSFMediatorExample.client.ReportJavas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import Reports.WeeklyReport;
import il.cshaifasweng.OCSFMediatorExample.client.*;
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
    private MenuItem contactInfoBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private MenuItem newAppBtn;

    @FXML
    private MenuItem scheduledAppBtn;

///////////






///////////

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
        clientMsg.setClinicName("");
        Message msg = new Message();
        msg.setUsername(clientMsg.getUsername());
        msg.setAction("Is_Hmo_Manager");
        System.out.println(msg.getAction());
        SimpleClient.getClient().openConnection();
        SimpleClient.getClient().sendToServer(msg);



    }
    @Subscribe
    public void onHMO_ManagerEvent(HMO_ManagerEvent event){
        try {
            System.out.println("get all clinics event");
            Message msg = new Message();
            msg.setAction("GetAllClinics");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void onManagerEvent(ManagerEvent event){
        try{
            Message msg = new Message();
            clientMsg.setUsername(App.getUsername());
            msg.setAction("getClinicNameFromUserName");
            msg.setUsername(App.getUsername());
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);

        } catch (IOException e) {
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
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_A"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_B"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_C"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_D"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_E"));
        AwaitingTimeRepColumnC.setCellValueFactory(new PropertyValueFactory<>("AverageWaitingTime_F"));
        AwaitingTimeRepTable.setItems(FXCollections.observableList(event.getAwaitingTimeRep()));

        ServicesTypeColumnA.setCellValueFactory(new PropertyValueFactory<>("DayOfTheWeek"));
        ServicesTypeColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctorPatientNumber"));
        ServicesTypeColumnC.setCellValueFactory(new PropertyValueFactory<>("PediatricianPatientNumber"));
        ServicesTypeColumnD.setCellValueFactory(new PropertyValueFactory<>("VaccineAppointment"));
        ServicesTypeColumnE.setCellValueFactory(new PropertyValueFactory<>("LabResults"));
        ServicesTypeColumnF.setCellValueFactory(new PropertyValueFactory<>("CovidTest"));
        ServicesTypeColumnG.setCellValueFactory(new PropertyValueFactory<>("NurseCare"));
        ServicesTypeRepTable.setItems(FXCollections.observableList(event.getServicesTypeRep()));



        UnclaimedAppointmentColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctorPatientNumber"));
        UnclaimedAppointmentColumnC.setCellValueFactory(new PropertyValueFactory<>("PediatricianPatientNumber"));
        UnclaimedAppointmentColumnD.setCellValueFactory(new PropertyValueFactory<>("VaccineAppointment"));
        UnclaimedAppointmentColumnE.setCellValueFactory(new PropertyValueFactory<>("LabResults"));
        UnclaimedAppointmentColumnF.setCellValueFactory(new PropertyValueFactory<>("CovidTest"));
        UnclaimedAppointmentColumnG.setCellValueFactory(new PropertyValueFactory<>("NurseCare"));
        UnclaimedAppointmentRep.setItems(FXCollections.observableList(event.getMissedAppRep()));
    }





}
