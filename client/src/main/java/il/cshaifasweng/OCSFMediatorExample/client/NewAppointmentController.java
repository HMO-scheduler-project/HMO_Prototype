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
        try {
            Message msg = new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("Get vaccine");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Subscribe
    public void OnGotVaccineEvent(GotVaccineEvent event) {
        App.setCovid_vaccine(event.isCovid_vaccine());
        App.setInfluenza_vaccine(event.isInfluenza_vaccine());
        if (event.isCovid_vaccine() && event.isInfluenza_vaccine()) {
            showAlert();
        } else
            ChangeScreens.changeToVaccineAppScreen();
        ;

    }

    public void showAlert() {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You're already vaccinated!");
                alert.showAndWait();
            }
        });
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);

        EventBus.getDefault().register(this);
    }


    @FXML
    void ViewQuestionnaire(ActionEvent event) {
        ChangeScreens.changeQuestionnaireScreen();
    }

}