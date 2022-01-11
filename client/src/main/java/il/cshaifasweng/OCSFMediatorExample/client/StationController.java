package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.greenrobot.eventbus.Subscribe;

public class StationController {


        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button exAppButton;

        @FXML
        private Button labButton;

        @FXML
        private Button nurseButton;

        @FXML
        private TextArea appText;


        @FXML
        void pressOnExAppButton(ActionEvent event) {
            try {
                Message msg = new Message();
                msg.setUsername(App.getUsername());
                msg.setClinic(App.getClinic());
                msg.setAction("Provide Ticket");
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void pressOnLabButton(ActionEvent event) {
            try {
                Message msg = new Message();
                msg.setUsername(App.getUsername());
                msg.setClinic(App.getClinic());
                msg.setAction("newLabApp");
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void pressOnNurseButton(ActionEvent event) {
            try {
                Message msg = new Message();
                msg.setUsername(App.getUsername());
                msg.setClinic(App.getClinic());
                msg.setAction("newNurseApp");
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void initialize() {
            assert appText != null : "fx:id=\"appText\" was not injected: check your FXML file 'labAppScreen.fxml'.";
            assert exAppButton != null : "fx:id=\"exAppButton\" was not injected: check your FXML file 'labAppScreen.fxml'.";
            assert labButton != null : "fx:id=\"labButton\" was not injected: check your FXML file 'labAppScreen.fxml'.";
            assert nurseButton != null : "fx:id=\"nurseButton\" was not injected: check your FXML file 'labAppScreen.fxml'.";
        }

        @Subscribe
        void onAppointmentTicketEvent(appointmentTicketEvent event){
            if(event.app instanceof doctorApp){
                appText.setText("Welcome "+event.app.getPatient().getFullName()+"\nAppointment time: " +event.app.getDate()+ " " +event.app.getTime()+ "\nDoctor: " +event.app.getEmployee().getFullName());
            }
            if(event.app instanceof childrenDoctorApp){
                appText.setText("Welcome "+event.app.getPatient().getFullName()+"\nAppointment time: " +event.app.getDate()+ " " +event.app.getTime()+ "\nDoctor: " +event.app.getEmployee().getFullName());
            }
            if(event.app instanceof familyDoctorApp){
                appText.setText("Welcome "+event.app.getPatient().getFullName()+"\nAppointment time: " +event.app.getDate()+ " " +event.app.getTime()+ "\nDoctor: " +event.app.getEmployee().getFullName());
            }
            if(event.app instanceof Covid19VaccineApp){
                appText.setText("Welcome "+event.app.getPatient().getFullName()+"\nAppointment time: " +event.app.getDate()+ " " +event.app.getTime());
            }
            if(event.app instanceof InfluenzaVaccineApp){
                appText.setText("Welcome "+event.app.getPatient().getFullName()+"\nAppointment time: " +event.app.getDate()+ " " +event.app.getTime());
            }
            if(event.app instanceof LabApp){
                appText.setText("Welcome "+event.app.getPatient().getFullName()+"\nAppointment time: " +event.app.getDate()+ " " +event.app.getTime()+ "\nLab Worker: "+ ((LabApp) event.app).getLabworker().getFullName());
            }
            if(event.app instanceof NurseApp){
                appText.setText("Welcome "+event.app.getPatient().getFullName()+"\nAppointment time: " +event.app.getDate()+ " " +event.app.getTime()+ "\nNurse: "+ ((NurseApp) event.app).getEmployee().getFullName());
            }
        }

        @Subscribe
        void gotNurseApp(nurseAppEvent event){ //get patient name
            try {
                Message msg = new Message();
                msg.setAppointment(event.getApp());
                msg.setAction("nurseAppCounter");
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Subscribe
        void gotNurseAppCount(nurseAppCounterEvent event){ //get patient name
            appText.setText("Welcome "+event.getPatient().getFullName()+"\nAppointment number: "+event.getAppCount());
            // show room number
        }

        @Subscribe
        void gotLabApp(labAppEvent event){ //get patient name
            appText.setText("Welcome "+event.getPatient().getFullName()+"\nAppointment number: "+event.getCounter());
        }
}
