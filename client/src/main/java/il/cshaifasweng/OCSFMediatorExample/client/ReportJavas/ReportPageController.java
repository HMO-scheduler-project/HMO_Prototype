package il.cshaifasweng.OCSFMediatorExample.client.ReportJavas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Reports.WeeklyReport;
import il.cshaifasweng.OCSFMediatorExample.client.*;
import il.cshaifasweng.OCSFMediatorExample.client.events.*;
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
    void PressChooseClinicBtn(ActionEvent event) {

    }

    @FXML
    private TableView<WeeklyReport> ReportTable;
    @FXML
    private TableColumn<WeeklyReport, String> ColumnA;

    @FXML
    private TableColumn<WeeklyReport, String> ColumnB;

    @FXML
    private TableColumn<WeeklyReport, String> ColumnC;

    @FXML
    private TableColumn<WeeklyReport, String> ColumnD;

    @FXML
    private TableColumn<WeeklyReport, String> ColumnE;

    @FXML
    private TableColumn<WeeklyReport, String> ColumnF;

    @FXML
    private TableColumn<WeeklyReport, String> ColumnG;

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
//this is where you feel the info
    @FXML
    void pressServicesTypeBtn(ActionEvent event) {
       //first check if there is a clinic since HMOmanager starts without one
        if(clientMsg.getClinicName()!=null) {
            //place what ever is in here in the table
            clientMsg.getClinic().getServicesTypeRep();
            Message msg = new Message();
            msg.setClinicName(clientMsg.getClinicName());
            msg.setAction("getServicesTypeRep");
            try {
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void pressWaitingTimeReportBtn(ActionEvent event) {
        if(clientMsg.getClinicName()!=null) {
            //place what ever is in here in the table
            clientMsg.getClinic().getServicesTypeRep();
            Message msg = new Message();
            msg.setAction("getWaitingTimeRep");
            msg.setClinicName(clientMsg.getClinicName());
            try {
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void pressMissedAppBtn(ActionEvent event) {
        if(clientMsg.getClinicName()!=null) {
            //place what ever is in here in the table
            clientMsg.getClinic().getServicesTypeRep();
            Message msg = new Message();
            msg.setClinicName(clientMsg.getClinicName());
            msg.setAction("getMissedAppRep");
            try {
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//////////until here


    @FXML
    void initialize() throws IOException  {
        assert ChangeAppBtn != null : "fx:id=\"ChangeAppBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert ChangeHoursBtn != null : "fx:id=\"ChangeHoursBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert ClinicsBtn != null : "fx:id=\"ClinicsBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert ClinicsList != null : "fx:id=\"ClinicsList\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert MainPageBtn != null : "fx:id=\"MainPageBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert MissedAppBtn != null : "fx:id=\"MissedAppBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert OpenningHoursBtn != null : "fx:id=\"OpenningHoursBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert ServicesTypeBtn != null : "fx:id=\"ServicesTypeBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert contactInfoBtn != null : "fx:id=\"contactInfoBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert logoutBtn != null : "fx:id=\"logoutBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert newAppBtn != null : "fx:id=\"newAppBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";
        assert scheduledAppBtn != null : "fx:id=\"scheduledAppBtn\" was not injected: check your FXML file 'ReportPage.fxml'.";

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
                ChooseMessage.setVisible(true);
                ClinicsList.setVisible(true);
                Message msg = new Message();
                msg.setAction("GetAllClinics");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            }
            else {
                ChooseMessage.setVisible(false);
                ClinicsList.setVisible(false);
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
    }

    @Subscribe
    public void onClinicListUpdateEvent(ClinicListUpdateEvent event) {
        for (String clinic : event.getClinicNames()) {
            ClinicsList.getItems().add(clinic);
        }
    }
    @FXML
    void chooseClinic() {
        String chosenClinic = ClinicsList.getSelectionModel().getSelectedItem();
        chosen_clinic = chosenClinic;
        clientMsg.setClinicName(chosenClinic);
    }

    @Subscribe
    public void onGetAwaitinigTimeRepEvent(AwaitingTimeRepEvent event) {
            ColumnA.setCellValueFactory(new PropertyValueFactory<>("Day"));
            ColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctor"));
            ColumnC.setCellValueFactory(new PropertyValueFactory<>("Pediatrician"));
            ColumnD.setCellValueFactory(new PropertyValueFactory<>("Vaccine_Appointments"));
            ColumnE.setCellValueFactory(new PropertyValueFactory<>("Lab_Test_Appointments"));
            ColumnF.setCellValueFactory(new PropertyValueFactory<>("Covid_Test"));
            ColumnG.setCellValueFactory(new PropertyValueFactory<>("Nurse_Care"));
    }
    @Subscribe
    public void onGetMissedApRepEvent(MissedAppRepEvent event) {
        ColumnA.setCellValueFactory(new PropertyValueFactory<>(""));
        ColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctor"));
        ColumnC.setCellValueFactory(new PropertyValueFactory<>("Pediatrician"));
        ColumnD.setCellValueFactory(new PropertyValueFactory<>("Vaccine_Appointments"));
        ColumnE.setCellValueFactory(new PropertyValueFactory<>("Lab_Test_Appointments"));
        ColumnF.setCellValueFactory(new PropertyValueFactory<>("Covid_Test"));
        ColumnG.setCellValueFactory(new PropertyValueFactory<>("Nurse_Care"));
    }
    @Subscribe
    public void onGetServicesTypeRepEvent(ServicesTypeRepEvent event) {
        ColumnA.setCellValueFactory(new PropertyValueFactory<>("Day"));
        ColumnB.setCellValueFactory(new PropertyValueFactory<>("FamilyDoctor"));
        ColumnC.setCellValueFactory(new PropertyValueFactory<>("Pediatrician"));
        ColumnD.setCellValueFactory(new PropertyValueFactory<>("Vaccine_Appointments"));
        ColumnE.setCellValueFactory(new PropertyValueFactory<>("Lab_Test_Appointments"));
        ColumnF.setCellValueFactory(new PropertyValueFactory<>("Covid_Test"));
        ColumnG.setCellValueFactory(new PropertyValueFactory<>("Nurse_Care"));
    }
}
