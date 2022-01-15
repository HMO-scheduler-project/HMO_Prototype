package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.GotVaccineEvent;
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
import java.time.Period;
import java.util.List;

public class NewAppointmentController {
    @FXML
    private Pane Menubar;

    @FXML
    private Button covidTestApp;

    @FXML
    private Button familyApp;

    @FXML
    private Button specialdoctor;

    @FXML
    private Label txtlabel;

    @FXML
    private Button vaccine;

    @FXML
    void FamilyAppView(ActionEvent event) {
        ChangeScreens.changeToDoctorAppScreen();
    }


    @FXML
    void SpecialApp(ActionEvent event) {
        ChangeScreens.changeSpecialAppScreen();
    }

    @FXML
    void VaccineApp(ActionEvent event) {
        ChangeScreens.changeToVaccineAppScreen();
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);
    }


    @FXML
    void ViewQuestionnaire(ActionEvent event) {
        ChangeScreens.changeQuestionnaireScreen();
    }

}